/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.service;

import com.sg.flooringmastery.dao.FlooringMasteryAuditDao;
import com.sg.flooringmastery.dao.FlooringMasteryAuditDaoStubImpl;
import com.sg.flooringmastery.dao.FlooringMasteryOrdersDao;
import com.sg.flooringmastery.dao.FlooringMasteryOrdersDaoStubImpl;
import com.sg.flooringmastery.dao.FlooringMasteryPersistenceException;
import com.sg.flooringmastery.dao.FlooringMasteryProductsDao;
import com.sg.flooringmastery.dao.FlooringMasteryProductsDaoFileImpl;
import com.sg.flooringmastery.dao.FlooringMasteryStatesDao;
import com.sg.flooringmastery.dao.FlooringMasteryStatesDaoFileImpl;
import com.sg.flooringmastery.dto.OrderData;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author raulalvarado
 */
public class FlooringMasteryServiceLayerTest {
    
    FlooringMasteryServiceLayer service;
    
    
    public FlooringMasteryServiceLayerTest() {
        //FlooringMasteryOrdersDao orderDao =  new FlooringMasteryOrdersDaoStubImpl();
        //FlooringMasteryProductsDao productDao = new FlooringMasteryProductsDaoFileImpl();
        //FlooringMasteryStatesDao stateDao = new FlooringMasteryStatesDaoFileImpl();
        //FlooringMasteryAuditDao auditDao = new FlooringMasteryAuditDaoStubImpl();
        //
        //service = new FlooringMasteryServiceLayerImpl (orderDao, productDao, stateDao, auditDao);
        
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        service = ctx.getBean("serviceLayer", FlooringMasteryServiceLayer.class);
    }
    
    
    /**
     * Test of getOrders method, of class FlooringMasteryServiceLayer.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetOrders() throws Exception {
        assertEquals(1,service.getOrders(LocalDate.of(2001, 01, 01)).size());
        
        try {
            List<OrderData> orders = service.getOrders(LocalDate.of(2001,01,01));
        } catch (FlooringMasteryInvalidOrderNumException e) {
             fail("FlooringMasteryInvalidOrderNumException wasnt thrown");
           
        }
    }

    /**
     * Test of getSingleOrder method, of class FlooringMasteryServiceLayer.
     */
    @Test
    public void testGetSingleOrder() throws Exception {
       
        try {
            OrderData order = service.getSingleOrder(LocalDate.of(2001, 01, 01), 1);
            assertNotNull(order);
        } catch (FlooringMasteryInvalidOrderNumException e) {
            fail("Expected InvalidOrderNumException was not thrown", e);
        }
        
    }

    /**
     * Test of addOrder method, of class FlooringMasteryServiceLayer.
     */
    @Test
    public void testAddOrder() throws Exception {
        
        OrderData order = new OrderData();
        order.setCustomerName("betty sue");
        order.setStateAbbr("MI");
        order.setProductType("wood");
        order.setArea(new BigDecimal("100"));
        service.addOrder(order);
        
        assertEquals(order, service.addOrder(order));
    }

    /**
     * Test of editOrder method, of class FlooringMasteryServiceLayer.
     * @throws java.lang.Exception
     */
    @Test
    public void testServiceSingleOrderNotNull() throws Exception {        
        try {
            OrderData savedOrder = service.getSingleOrder(LocalDate.of(2001,01,01), 1);
            assertNotNull(savedOrder);
        } catch (FlooringMasteryInvalidOrderNumException e) {  
            fail("Expected InvalidOrderNumException wasnt thrown", e);
        }
    }

    /**
     * Test of removeOrder method, of class FlooringMasteryServiceLayer.
     * @throws java.lang.Exception
     */
    @Test
    public void testRemoveOrder() throws Exception {
        
        OrderData removedOrder = service.getSingleOrder(LocalDate.of(2001,01,01),1);
        assertNotNull(removedOrder);
        
        try {
            removedOrder = service.getSingleOrder(LocalDate.of(2001,01,01), 0);
        } catch (FlooringMasteryInvalidOrderNumException e) {
            fail("Expected InvalidOrderNumException not thrown");
        }
    }

    /**
     * Test of addUpOrderData method, of class FlooringMasteryServiceLayer.
     */
    @Test
    public void testAddUpOrderData() throws Exception {
        
        OrderData order1 = new OrderData();
        order1.setCustomerName("jhon");
        order1.setStateAbbr("MI");
        order1.setProductType("Wood");
        order1.setArea(new BigDecimal("100"));
        
        OrderData order2 = new OrderData();
        order2.setCustomerName("jhon");
        order2.setStateAbbr("MI");
        order2.setProductType("Wood");
        order2.setArea(new BigDecimal("100"));
        
        assertEquals(service.addUpOrderData(order1), service.addUpOrderData(order2));
        
        order1.setCustomerName("jhon");
        
        try {
            service.addUpOrderData(order1);
            assertNotNull(order1);
        } catch (FlooringMasteryOrderValidationException e) {
            fail("Expected OrderValidationException wasnt thrown", e);
        }
        
        order1.setCustomerName("jhon");
        order1.setStateAbbr("MI");
        
        try {
            service.addUpOrderData(order1);
            assertNotNull(order1);
        } catch (FlooringMasteryOrderValidationException e) {
            fail("Expected OrderValidationException wasnt thrown");
        }
        order1.setStateAbbr("MI");
        order1.setProductType("Wood");
        
        try {
            service.addUpOrderData(order1);
            assertNotNull(order1);
        } catch (FlooringMasteryOrderValidationException e) {
            fail("Expected OrderValidationException wasnt thrown");
        }
        
        order1.setProductType("Wood");
        order1.setArea(new BigDecimal("100"));
        
        try {
            service.addUpOrderData(order1);
            assertNotNull(order1);
        } catch (FlooringMasteryOrderValidationException e) {
            fail("Expected OrderValidationException was not",e);
            
        }
        
        order1.setArea(new BigDecimal("100"));
        order1.setStateAbbr("MN");
        
       
        
    }

    /**
     * Test of compareOrder method, of class FlooringMasteryServiceLayer.
     */
    @Test
    public void testCompareOrder() throws Exception {
        
        OrderData savedOrder = service.getSingleOrder(LocalDate.of(2001,01,01), 1);
        
        OrderData editedOrder = new OrderData();
        editedOrder.setCustomerName("betty");
        
        OrderData updatedOrder = service.compareOrder(savedOrder, editedOrder);
        
        assertEquals(updatedOrder, savedOrder);
    }

    
}
