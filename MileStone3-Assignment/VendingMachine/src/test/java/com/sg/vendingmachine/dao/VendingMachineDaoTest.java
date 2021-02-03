/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.dto.Item;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 *
 * @author raulalvarado
 */
public class VendingMachineDaoTest {
    
    private VendingMachineDao dao = new VendingMachineDaoStubImpl();
    private Item item = new Item();
    
    public VendingMachineDaoTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        item.setItemId("A1");
        item.setItemName("snickers");
        item.setItemCost(new BigDecimal("1.00"));
        item.setItemQty(1);
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of getAllItems method, of class VendingMachineDao.
     */
    @Test
    public void testGetAllItems() throws Exception {
        try {
            List<Item>allItems = dao.getAllItems();
            assertEquals(1, allItems.size());
        } catch (VendingMachinePersistenceException ex) {
            fail("no items in Machine");
        }
    }

    /**
     * Test of purchaseItem method, of class VendingMachineDao.
     */
    @Test
    public void testPurchaseItem() throws Exception {
        item.setItemId("A1");
        item.setItemName("snickers");
        item.setItemCost(new BigDecimal("1.00"));
        item.setItemQty(1);
        
        item.purchaseItem();
        assertEquals(1, item.purchaseItem());
    }

    /**
     * Test of getItem method, of class VendingMachineDao.
     */
    @Test
    public void testGetItem() throws Exception {
        item.setItemId("A1");
        item.setItemName("snickers");
        item.setItemCost(new BigDecimal("1.00"));
        item.setItemQty(1);
    }
    /**
     * Test of makesSalesReduceInventory method, of class VendingMachineDao
     */
    @Test
    public void testMakeSalesReduceInventory() {
        item.setItemId("A1");
        item.setItemName("snickers");
        item.setItemCost(new BigDecimal("1.00"));
        item.setItemQty(1);
        
        assertEquals(0, item.getItemQty() -1);
    }
    
}
