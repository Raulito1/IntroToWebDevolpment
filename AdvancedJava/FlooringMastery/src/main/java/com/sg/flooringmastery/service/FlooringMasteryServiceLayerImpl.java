/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.service;

import com.sg.flooringmastery.dao.FlooringMasteryAuditDao;
import com.sg.flooringmastery.dao.FlooringMasteryOrderDao;
import com.sg.flooringmastery.dao.FlooringMasteryPersistenceException;
import com.sg.flooringmastery.dao.FlooringMasteryProductDao;
import com.sg.flooringmastery.dao.FlooringMasteryStateDao;
import com.sg.flooringmastery.dto.Order;
import com.sg.flooringmastery.dto.Product;
import com.sg.flooringmastery.dto.State;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author raulalvarado
 */
public class FlooringMasteryServiceLayerImpl implements FlooringMasteryServiceLayer {
    
    private FlooringMasteryOrderDao orderDao;
    private FlooringMasteryProductDao productDao;
    private FlooringMasteryStateDao stateDao;
    private FlooringMasteryAuditDao auditDao;

    public FlooringMasteryServiceLayerImpl(FlooringMasteryOrderDao orderDao, FlooringMasteryProductDao productDao, FlooringMasteryStateDao stateDao, FlooringMasteryAuditDao auditDao) {
        this.orderDao = orderDao;
        this.productDao = productDao;
        this.stateDao = stateDao;
        this.auditDao = auditDao;
    }

    
   

    @Override
    public List<Order> getOrders(LocalDate date) throws FlooringMasteryPersistenceException, InvalidOrderNumberException {
        List<Order> ordersByDate = orderDao.getOrders(date);
        if (!ordersByDate.isEmpty()) {
            return ordersByDate;
        } else {
            throw new InvalidOrderNumberException ("ERROR: no orders exist with that date");
        }
    }

    @Override
    public Order getSingleOrder(LocalDate date, int orderNum) throws FlooringMasteryPersistenceException, InvalidOrderNumberException {
        List<Order> orders = getOrders(date);
        Order orderChosen = orders.stream().filter(o -> o.getOrderNum() == orderNum).findFirst().orElse(null);
        if (orderChosen != null) {
            return orderChosen;
        } else  {
            throw new InvalidOrderNumberException("ERROR: no orders with that num exist with that date");
        }
    }

    @Override
    public Order addOrder(Order order) throws FlooringMasteryPersistenceException {
        orderDao.addOrder(order);
        //auditDao.writeAuditEntry("OrderNum: " + order.getOrderNum()
        //                            + order.getDate() + " Added");
        return order;
    }

    @Override
    public Order editOrder(Order editOrder) throws FlooringMasteryPersistenceException, InvalidOrderNumberException {
        editOrder = orderDao.editOrder(editOrder);
        if (editOrder != null) {
            //auditDao.writeAuditEntry("Order Num: " + editOrder.getOrderNum() + " for date " + editOrder.getDate() + " Edited");
            return editOrder;
        } else {
            throw new InvalidOrderNumberException("ERROR: no orders with that order num ");
        }
    }

    @Override
    public Order removeOrder(Order removeOrder) throws FlooringMasteryPersistenceException, InvalidOrderNumberException {
        removeOrder = orderDao.removeOrder(removeOrder);
        if (removeOrder != null) {
            //auditDao.writeAuditEntry("OrderNum: " + removeOrder.getOrderNum() + " for date " + removeOrder.getDate() + " Removed");
            return removeOrder;
        } else {
            throw new InvalidOrderNumberException("ERROR: no orders with that num exist with date");
        }
    }

    @Override
    public Order addUpOrderData(Order order) throws FlooringMasteryPersistenceException, StateValidationException, ProductValidationException, OrderValidationException {
        
            orderValidation(order);
            calculateTax(order);
            addUpMaterial(order);
            addUpTotal(order);
            return order;
        
    }

    @Override
    public Order compareOrder(Order saveOrder, Order editedOrder) throws FlooringMasteryPersistenceException, StateValidationException, ProductValidationException {
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
    
    private void calculateTax(Order order) throws FlooringMasteryPersistenceException, StateValidationException {
        State stateChosen = stateDao.getState(order.getStateAbbr());
        if (stateChosen == null) {
            throw new StateValidationException("ERROR: Invalid state option");
        }
        order.setStateAbbr(stateChosen.getStateAbbr());
        order.setTaxRate(stateChosen.getTaxRate());
    }
    
    private void addUpMaterial(Order order) throws FlooringMasteryPersistenceException, ProductValidationException {
        Product productChosen = productDao.getProduct(order.getProductType());
        if (productChosen == null) {
            throw new ProductValidationException ("ERROR: Invalid product type");
        }
        order.setProductType(productChosen.getProductType());
        order.setProductCostPerSqFt(productChosen.getProductCostPerSqFt());
        order.setLaborCostPerSqFt(productChosen.getLaborCostPerSqFt());
    }
    
    private void addUpTotal(Order order) {
        order.setProductCost(order.getProductCostPerSqFt().multiply(order.getArea())
        .setScale(2, RoundingMode.HALF_UP));
        
        order.setLaborCost(order.getLaborCostPerSqFt().multiply(order.getArea())
        .setScale(2, RoundingMode.HALF_UP));
        
        order.setTax(order.getTaxRate().divide(new BigDecimal("100.00"))
        .multiply((order.getProductCost().add(order.getLaborCost()))).setScale(2, RoundingMode.HALF_UP));
        
        order.setTotal(order.getProductCost().add(order.getLaborCost()).add(order.getTax()));
    }
    
    private void orderValidation(Order order) throws OrderValidationException {
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
            throw new OrderValidationException(blankMessage);
        }
    }
}
