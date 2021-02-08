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
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author raulalvarado
 */
public class FlooringMasteryOrdersProdFileImplTest {
    
    private FlooringMasteryOrdersDao orderDao = new FlooringMasteryOrdersProdFileImpl(ordersFolder);
    
    private static String ordersFolder = "src/test/resources/";
     
    public FlooringMasteryOrdersProdFileImplTest() {
        
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of getOrders method, of class FlooringMasteryOrdersProdFileImpl.
     */
    @Test
    public void testAddandGetOrders() throws Exception {
        
        LocalDate date = LocalDate.parse("01012020", DateTimeFormatter.ofPattern("MMddyyyy"));
        List<OrderData> newOrder = orderDao.getOrders(date);
        
        OrderData order = new OrderData();
        order.setDate(date);
        order.setCustomerName("Jhon Doe");
        order.setStateAbbr("IN");
        order.setTaxRate(new BigDecimal("6.00"));
        order.setProductType("Laminate");
        order.setArea(new BigDecimal("100"));
        order.setProductCostPerSqFt(new BigDecimal("1.75"));
        order.setLaborCostPerSqFt(new BigDecimal("2.10"));
        order.setProductCost(order.getProductCostPerSqFt().multiply(order.getArea()).setScale(2, RoundingMode.HALF_UP));
        order.setLaborCost(order.getLaborCostPerSqFt().multiply(order.getArea()).setScale(2, RoundingMode.HALF_UP));
        order.setTax(order.getTaxRate().divide(new BigDecimal("100.00")).multiply((order.getProductCost().add(order.getLaborCost()))).setScale(2, RoundingMode.HALF_UP));
        order.setTotal(order.getProductCost().add(order.getLaborCost()).add(order.getTax()));
        
        order = orderDao.addOrder(order);
        List<OrderData> fromDao = orderDao.getOrders(order.getDate());
        
        //Testing to see if a row was created in my test file
        assertEquals(1, (fromDao.size() - newOrder.size()));
    }

   
    /**
     * Test of editOrder method, of class FlooringMasteryOrdersProdFileImpl.
     */
    @Test
    public void testEditOrderGolenPath() throws Exception {
         LocalDate date = LocalDate.parse("09301999",
                DateTimeFormatter.ofPattern("MMddyyyy"));

        OrderData order = new OrderData();
        order.setDate(date);
        order.setCustomerName("Jhon Doe");
        order.setStateAbbr("IN");
        order.setTaxRate(new BigDecimal("6.00"));
        order.setProductType("Laminate");
        order.setArea(new BigDecimal("100"));
        order.setProductCostPerSqFt(new BigDecimal("1.75"));
        order.setLaborCostPerSqFt(new BigDecimal("2.10"));
        order.setProductCost(order.getProductCostPerSqFt().multiply(order.getArea()).setScale(2, RoundingMode.HALF_UP));
        order.setLaborCost(order.getLaborCostPerSqFt().multiply(order.getArea()).setScale(2, RoundingMode.HALF_UP));
        order.setTax(order.getTaxRate().divide(new BigDecimal("100.00")).multiply((order.getProductCost().add(order.getLaborCost()))).setScale(2, RoundingMode.HALF_UP));
        order.setTotal(order.getProductCost().add(order.getLaborCost()).add(order.getTax()));
                

        order = orderDao.addOrder(order);

        OrderData editedOrder = order;
        editedOrder.setCustomerName("jhon doe jr");

        editedOrder = orderDao.editOrder(editedOrder);

        List<OrderData> orders = orderDao.getOrders(date);
        int orderNumber = editedOrder.getOrderNum();

        OrderData chosenOrder = orders.stream()
                .filter(o -> o.getOrderNum() == orderNumber)
                .findFirst().orElse(null);

        assertEquals(editedOrder, chosenOrder);

    }
    
    @Test
    public void testEditOrderFailed() throws Exception {        
        LocalDate date = LocalDate.parse("10101999", DateTimeFormatter.ofPattern("MMddyyyy"));
        OrderData order = new OrderData();
        order.setDate(date);
        order.setCustomerName("Jhonny");
        order.setStateAbbr("IN");
        order.setTaxRate(new BigDecimal("6.00"));
        order.setProductType("laminate");
        order.setArea(new BigDecimal("100"));
        order.setProductCostPerSqFt(new BigDecimal("1.75"));
        order.setLaborCostPerSqFt(new BigDecimal("2.10"));
        order.setProductCost(order.getProductCostPerSqFt().multiply(order.getArea()).setScale(2, RoundingMode.HALF_UP));
        order.setLaborCost(order.getLaborCostPerSqFt().multiply(order.getArea()).setScale(2, RoundingMode.HALF_UP));
        order.setTax(order.getTaxRate().divide(new BigDecimal("100.00")).multiply((order.getProductCost().add(order.getLaborCost()))).setScale(2, RoundingMode.HALF_UP));
        order.setTotal(order.getProductCost().add(order.getLaborCost()).add(order.getTax()));
        
        order = orderDao.addOrder(order);
        
        OrderData editedOrder = order;
        // setting order num to force null
        editedOrder.setOrderNum(0);
        editedOrder.setCustomerName("BLANK");
        
        editedOrder = orderDao.editOrder(editedOrder);
        
        //testing that null should come back if unable to edit the order
        assertNull(editedOrder);
    }
    /**
     * Test of removeOrder method, of class FlooringMasteryOrdersProdFileImpl.
     */
    @Test
    public void testRemoveOrder() throws Exception {
        LocalDate date = LocalDate.parse("01311999", DateTimeFormatter.ofPattern("MMddyyyy"));
        
        OrderData order1 = new OrderData();
        order1.setDate(date);
        order1.setCustomerName("jhon doe1");
        order1.setStateAbbr("IN");
        order1.setTaxRate(new BigDecimal("6.00"));
        order1.setProductType("laminate");
        order1.setArea(new BigDecimal("100"));
        order1.setProductCostPerSqFt(new BigDecimal("1.75"));
        order1.setLaborCostPerSqFt(new BigDecimal("2.10"));
        order1.setProductCost(order1.getProductCostPerSqFt().multiply(order1.getArea()).setScale(2, RoundingMode.HALF_UP));
        order1.setLaborCost(order1.getLaborCostPerSqFt().multiply(order1.getArea()).setScale(2, RoundingMode.HALF_UP));
        order1.setTax(order1.getTaxRate().divide(new BigDecimal("100.00")).multiply((order1.getProductCost().add(order1.getLaborCost()))).setScale(2, RoundingMode.HALF_UP));
        order1.setTotal(order1.getProductCost().add(order1.getLaborCost()).add(order1.getTax()));
        
        orderDao.addOrder(order1);
        
        OrderData order2 = new OrderData();
        order2.setDate(date);
        order2.setCustomerName("Jhon doe2");
        order2.setStateAbbr("IN");
        order2.setTaxRate(new BigDecimal("6.00"));
        order2.setProductType("wood");
        order2.setArea(new BigDecimal("100"));
        order2.setProductCostPerSqFt(new BigDecimal("1.75"));
        order2.setLaborCostPerSqFt(new BigDecimal("2.10"));
        order2.setProductCost(order2.getProductCostPerSqFt().multiply(order2.getArea()).setScale(2, RoundingMode.HALF_UP));
        order2.setLaborCost(order2.getLaborCostPerSqFt().multiply(order2.getArea()).setScale(2, RoundingMode.HALF_UP));
        order2.setTax(order2.getTaxRate().divide(new BigDecimal("100.00")).multiply((order2.getProductCost().add(order2.getLaborCost()))).setScale(2, RoundingMode.HALF_UP));
        order2.setTotal(order2.getProductCost().add(order2.getLaborCost()).add(order2.getTax()));
        
        order2 = orderDao.addOrder(order2);
        
        List<OrderData> newOrder = orderDao.getOrders(date);
        
        orderDao.removeOrder(order2);
        
        List<OrderData> fromDao = orderDao.getOrders(date);
        
        // Testing to see if row was removed from test file
        assertEquals(-1, (fromDao.size() - newOrder.size()));
    }
    
}
