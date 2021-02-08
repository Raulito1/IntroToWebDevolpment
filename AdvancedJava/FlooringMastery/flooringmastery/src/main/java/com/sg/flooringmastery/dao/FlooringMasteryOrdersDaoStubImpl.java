/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.OrderData;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author raulalvarado
 */
public class FlooringMasteryOrdersDaoStubImpl implements FlooringMasteryOrdersDao {
    
    private OrderData onlyOrder;
    private List<OrderData> orderList = new ArrayList<>();
    
    public FlooringMasteryOrdersDaoStubImpl() {
        // creaating onlyOrder for Dao stub to use for testing purposes
        onlyOrder = new OrderData();
        onlyOrder.setDate(LocalDate.parse("01012001", DateTimeFormatter.ofPattern("MMddyyyy")));
        onlyOrder.setOrderNum(1);
        onlyOrder.setCustomerName("Jhon doe");
        onlyOrder.setStateAbbr("IN");
        onlyOrder.setTaxRate(new BigDecimal("6.00"));
        onlyOrder.setProductType("laminate");
        onlyOrder.setArea(new BigDecimal("100"));
        onlyOrder.setProductCostPerSqFt(new BigDecimal("1.75"));
        onlyOrder.setLaborCostPerSqFt(new BigDecimal("2.10"));
        onlyOrder.setProductCost(onlyOrder.getProductCostPerSqFt().multiply(onlyOrder.getArea()).setScale(2, RoundingMode.HALF_UP));
        onlyOrder.setLaborCost(onlyOrder.getLaborCostPerSqFt().multiply(onlyOrder.getArea()).setScale(2, RoundingMode.HALF_UP));
        onlyOrder.setTax(onlyOrder.getTaxRate().divide(new BigDecimal("100.00")).multiply((onlyOrder.getLaborCost().add(onlyOrder.getLaborCost()))).setScale(2, RoundingMode.HALF_UP));
        onlyOrder.setTotal(onlyOrder.getProductCost().add(onlyOrder.getLaborCost()).add(onlyOrder.getTax()));
        
        orderList.add(onlyOrder);
        
    } 

    @Override
    public List<OrderData> getOrders(LocalDate date) throws FlooringMasteryPersistenceException {
        if (date.equals(onlyOrder.getDate())) {
            return orderList;
        } else {
            //sholud return empty
            return new ArrayList<>();
        }
    }

    @Override
    public OrderData addOrder(OrderData order) throws FlooringMasteryPersistenceException {
        orderList.add(order);
        return order;
    }

    @Override
    public OrderData editOrder(OrderData editedOrder) throws FlooringMasteryPersistenceException {
        if (editedOrder.getOrderNum() == onlyOrder.getOrderNum()) {
            return onlyOrder;
        } else {
            return null;
        }
    }

    @Override
    public OrderData removeOrder(OrderData orderChosen) throws FlooringMasteryPersistenceException {
        if (orderChosen.equals(onlyOrder)) {
            return onlyOrder;
        } else {
            return null;
        }
    }
    
}
