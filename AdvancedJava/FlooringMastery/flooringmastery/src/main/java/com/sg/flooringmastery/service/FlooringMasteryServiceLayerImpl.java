/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.service;

import com.sg.flooringmastery.dao.FlooringMasteryAuditDao;
import com.sg.flooringmastery.dao.FlooringMasteryOrdersDao;
import com.sg.flooringmastery.dao.FlooringMasteryPersistenceException;
import com.sg.flooringmastery.dao.FlooringMasteryProductsDao;
import com.sg.flooringmastery.dao.FlooringMasteryStatesDao;
import com.sg.flooringmastery.dto.OrderData;
import com.sg.flooringmastery.dto.ProductData;
import com.sg.flooringmastery.dto.StateData;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author raulalvarado
 */
public class FlooringMasteryServiceLayerImpl implements FlooringMasteryServiceLayer {
    
    private FlooringMasteryOrdersDao orderDao;
    private FlooringMasteryProductsDao productDao;
    private FlooringMasteryStatesDao stateDao;
    private FlooringMasteryAuditDao auditDao;

    public FlooringMasteryServiceLayerImpl(FlooringMasteryOrdersDao orderDao, FlooringMasteryProductsDao productDao, FlooringMasteryStatesDao stateDao, FlooringMasteryAuditDao auditDao) {
        this.orderDao = orderDao;
        this.productDao = productDao;
        this.stateDao = stateDao;
        this.auditDao = auditDao;
    }

    
   

    @Override
    public List<OrderData> getOrders(LocalDate date) throws FlooringMasteryPersistenceException, FlooringMasteryInvalidOrderNumException {
        List<OrderData> ordersByDate = orderDao.getOrders(date);
        if (!ordersByDate.isEmpty()) {
            return ordersByDate;
        } else {
            throw new FlooringMasteryInvalidOrderNumException ("ERROR: no orders exist with that date");
        }
    }

    @Override
    public OrderData getSingleOrder(LocalDate date, int orderNum) throws FlooringMasteryPersistenceException, FlooringMasteryInvalidOrderNumException {
        List<OrderData> orders = getOrders(date);
        OrderData orderChosen = orders.stream().filter(o -> o.getOrderNum() == orderNum).findFirst().orElse(null);
        if (orderChosen != null) {
            return orderChosen;
        } else  {
            throw new FlooringMasteryInvalidOrderNumException("ERROR: no orders with that num exist with that date");
        }
    }

    @Override
    public OrderData addOrder(OrderData order) throws FlooringMasteryPersistenceException {
        orderDao.addOrder(order);
        //auditDao.writeAuditEntry("OrderNum: " + order.getOrderNum()
        //                            + order.getDate() + " Added");
        return order;
    }

    @Override
    public OrderData editOrder(OrderData editOrder) throws FlooringMasteryPersistenceException, FlooringMasteryInvalidOrderNumException {
        editOrder = orderDao.editOrder(editOrder);
        if (editOrder != null) {
            //auditDao.writeAuditEntry("Order Num: " + editOrder.getOrderNum() + " for date " + editOrder.getDate() + " Edited");
            return editOrder;
        } else {
            throw new FlooringMasteryInvalidOrderNumException("ERROR: no orders with that order num ");
        }
    }

    @Override
    public OrderData removeOrder(OrderData removeOrder) throws FlooringMasteryPersistenceException, FlooringMasteryInvalidOrderNumException {
        removeOrder = orderDao.removeOrder(removeOrder);
        if (removeOrder != null) {
            //auditDao.writeAuditEntry("OrderNum: " + removeOrder.getOrderNum() + " for date " + removeOrder.getDate() + " Removed");
            return removeOrder;
        } else {
            throw new FlooringMasteryInvalidOrderNumException("ERROR: no orders with that num exist with date");
        }
    }

    @Override
    public OrderData addUpOrderData(OrderData order) throws FlooringMasteryPersistenceException,FlooringMasteryStateValidationException,FlooringMasteryProductValidationException,FlooringMasteryOrderValidationException {
        
            orderValidation(order);
            calculateTax(order);
            addUpMaterial(order);
            addUpTotal(order);
            return order;
        
    }

    @Override
    public OrderData compareOrder(OrderData saveOrder, OrderData editedOrder) throws FlooringMasteryPersistenceException, FlooringMasteryStateValidationException, FlooringMasteryProductValidationException {
        if (editedOrder.getCustomerName() == null || editedOrder.getCustomerName().trim().equals("")) {
            
        } else {
            saveOrder.setCustomerName(editedOrder.getCustomerName());
        }
        
        if (editedOrder.getStateAbbr() == null || editedOrder.getStateAbbr().trim().equals("")) {
            
        } else {
            saveOrder.setStateAbbr(editedOrder.getStateAbbr());
            calculateTax(saveOrder);
        }
        
        if (editedOrder.getProductType() == null || editedOrder.getProductType().equals("")) {
            
        } else {
            saveOrder.setProductType(editedOrder.getProductType());
            addUpMaterial(saveOrder);
        }
        
        if (editedOrder.getArea() == null || (editedOrder.getArea().compareTo(BigDecimal.ZERO))== 0) {
            
        } else {
            saveOrder.setArea(editedOrder.getArea());
        }
        addUpTotal(saveOrder);
        return saveOrder;
    }
    
    private void calculateTax(OrderData order) throws FlooringMasteryPersistenceException, FlooringMasteryStateValidationException {
        StateData stateChosen = stateDao.getState(order.getStateAbbr());
        if (stateChosen == null) {
            throw new FlooringMasteryStateValidationException("ERROR: Invalid state option");
        }
        order.setStateAbbr(stateChosen.getStateAbbr());
        order.setTaxRate(stateChosen.getTaxRate());
    }
    
    private void addUpMaterial(OrderData order) throws FlooringMasteryPersistenceException, FlooringMasteryProductValidationException {
        ProductData productChosen = productDao.getProduct(order.getProductType());
        if (productChosen == null) {
            throw new FlooringMasteryProductValidationException ("ERROR: Invalid product type");
        }
        order.setProductType(productChosen.getProductType());
        order.setProductCostPerSqFt(productChosen.getProductCostPerSqFt());
        order.setLaborCostPerSqFt(productChosen.getLaborCostPerSqFt());
    }
    
    private void addUpTotal(OrderData order) {
        order.setProductCost(order.getProductCostPerSqFt().multiply(order.getArea())
        .setScale(2, RoundingMode.HALF_UP));
        
        order.setLaborCost(order.getLaborCostPerSqFt().multiply(order.getArea())
        .setScale(2, RoundingMode.HALF_UP));
        
        order.setTax(order.getTaxRate().divide(new BigDecimal("100.00"))
        .multiply((order.getProductCost().add(order.getLaborCost()))).setScale(2, RoundingMode.HALF_UP));
        
        order.setTotal(order.getProductCost().add(order.getLaborCost()).add(order.getTax()));
    }
    
    private void orderValidation(OrderData order) throws FlooringMasteryOrderValidationException {
        String blankMessage = "";
        if (order.getCustomerName().trim().isEmpty() || order.getCustomerName() == null) {
            blankMessage += "Customer name is required.\n";
        }
        
        if (order.getStateAbbr().trim().isEmpty() || order.getStateAbbr() == null) {
            blankMessage += "State is required.\n";
        }
        
        if (order.getProductType().trim().isEmpty() || order.getProductType() == null) {
            blankMessage += "Product type is required.\n";
        }
        
        if (order.getArea().compareTo(BigDecimal.ZERO) == 0 || order.getArea() == null) {
            blankMessage += "Area sq.Ft is required";
        }
        if (!blankMessage.isEmpty()) {
            throw new FlooringMasteryOrderValidationException(blankMessage);
        }
    }
    
}
