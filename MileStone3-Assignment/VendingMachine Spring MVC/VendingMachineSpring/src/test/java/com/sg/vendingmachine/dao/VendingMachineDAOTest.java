/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.model.Item;
import com.sg.vendingmachine.service.NoItemInventoryException;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author raulalvarado
 */
public class VendingMachineDAOTest {
    
    private VendingMachineDAO dao;
    
    
    public VendingMachineDAOTest() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");
        dao = ctx.getBean("VendingDAO", VendingMachineDAO.class);
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getAllItems method, of class VendingMachineDAO.
     */
    @Test
    public void testGetAllItems() throws Exception {
        assertEquals(9, dao.getAllItems().size());
    }

    /**
     * Test of getItem method, of class VendingMachineDAO.
     */
    @Test
    public void testGetItem() throws Exception {
        assertEquals("Purple Urkel",dao.getItem(1).getItemName());
    }

    
}
