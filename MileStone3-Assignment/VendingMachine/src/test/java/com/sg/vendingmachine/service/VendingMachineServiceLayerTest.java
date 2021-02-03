/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.service;

import com.sg.vendingmachine.dao.VendingMachinePersistenceException;
import com.sg.vendingmachine.dto.Item;
import java.math.BigDecimal;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author raulalvarado
 */
public class VendingMachineServiceLayerTest {
    
    private VendingMachineServiceLayer service;
    
    public VendingMachineServiceLayerTest() {
        
        ApplicationContext ctx =
                new ClassPathXmlApplicationContext("applicationContext.xml");
        service = ctx.getBean("serviceLayer", VendingMachineServiceLayer.class);
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
     * Test of getAllItems method, of class VendingMachineServiceLayer.
     */
    @Test
    public void testGetAllItemsGoldenPath() throws Exception {
        try {
            assertEquals(1, service.getAllItems().size());
        } catch (VendingMachinePersistenceException ex) {
            fail("did not get all items");
        }
    }

    /**
     * Test of getItem method, of class VendingMachineServiceLayer.
     */
    @Test
    public void testGetItemGoldenPath() throws Exception {
        try {
            Item item = service.getItem("A1");
            assertEquals(1, item.getItemQty());
        } catch (VendingMachinePersistenceException | NoItemInventoryException ex) {
            fail("could not get item");
        }
    }

    /**
     * Test of purchaseItem method, of class VendingMachineServiceLayer.
     */
    @Test
    public void testPurchaseItem() throws Exception {
        try {
            // using the 3As
            // fitst A is for Arrange
            Item item = service.getItem("A1");
            BigDecimal itemCost = item.getItemCost();
            int itemQty = item.getItemQty();
            
            // second A is Act
            service.setMachineMoney(new BigDecimal("2.00"));
            service.purchaseItem("A1");
            
            // third A is Assert where the actul testing is done
            assertEquals(0, item.getItemQty());
        } catch (VendingMachinePersistenceException | NoItemInventoryException | InsufficientFundsException ex) {
            fail("purchase failed");
        }
    }

    /**
     * Test of removeItem method, of class VendingMachineServiceLayer.
     */
    @Test
    public void testRemoveItem() throws Exception {
        
    }

    /**
     * Test of setMachineMoney method, of class VendingMachineServiceLayer.
     */
    @Test
    public void testSetMachineMoney() {
        service.setMachineMoney(new BigDecimal("1.00"));
        assertEquals(new BigDecimal("1.00"), service.getMachineMoney());
    }

    /**
     * Test of getMachineMoney method, of class VendingMachineServiceLayer.
     */
    @Test
    public void testGetMachineMoney() {
    }

    /**
     * Test of giveChange method, of class VendingMachineServiceLayer.
     */
    @Test
    public void testGiveChange() throws Exception {
        try {
            Item onlyItem = service.getItem("A1");
            BigDecimal oneHundo = new BigDecimal("100");
            BigDecimal currentMoney = new BigDecimal ("2.00");
            
            int remainingCash = currentMoney.subtract(onlyItem.getItemCost()).multiply(oneHundo).intValueExact();
            service.giveChange(remainingCash);
            
            assertEquals(1, remainingCash);
        } catch (VendingMachinePersistenceException | NoItemInventoryException ex) {
            fail("cannot give change");
        }
    }

    /**
     * Test of cancelAndGiveChange method, of class VendingMachineServiceLayer.
     */
    @Test
    public void testCancelAndGiveChange() throws Exception {
    }
    
    /**
     * Test of getItemInInventory, of class VendingMachineServiceLayer
     */
    @Test
    public void testGetItemInInventory() {
        try {
            Item item = service.getItem("B99");
            fail("item not in inventory exeption not thrown");
        } catch (VendingMachinePersistenceException | NoItemInventoryException ex) {
            
        }
    }
    
    /**
     * Test insufficientFunds, of class VendingMachineServiceLayer
     */
     @Test
    public void testInsufficientFunds() {
        try {
            Item item = service.getItem("A1");
            BigDecimal itemCost = item.getItemCost();
            int itemQty = item.getItemQty();
            BigDecimal deposit = new BigDecimal(".25");
            
            service.setMachineMoney(new BigDecimal(".25"));
            service.purchaseItem("A1");
            
            fail("purchase failed InsufficientFunds");
        } catch (VendingMachinePersistenceException | NoItemInventoryException | InsufficientFundsException ex) {
            assertTrue(true);
        }
    }
    
    /**
     * Test balanceGoesBackToZero, of class VendingMachineServiceLayer
     */
    @Test
    public void testBalanceGoesBackToZero() {
        service.setMachineMoney(new BigDecimal("2.00"));
        try {
            service.cancelAndGiveChange();
        } catch (VendingMachinePersistenceException ex) {
            fail("Could not return change back to Zero");
        }
        service.getMachineMoney();
        assertEquals(new BigDecimal("0"), service.getMachineMoney());
    }
    
    /**
     * Test makeQuartesChange, of class Change
     */
     @Test
    public void testMakeQuartersChange() {
        
        try {
            //Arrange my test 
            Item onlyItem = service.getItem("A1");
            BigDecimal oneHundo = new BigDecimal("100");
            BigDecimal deposit = new BigDecimal("2.24");
            
            //Act my test
            int remainingCash = deposit.subtract(onlyItem.getItemCost()).multiply(oneHundo).intValueExact();
            service.giveChange(remainingCash);
            
            //Assert my test my actual testing done
            assertEquals(25, remainingCash);
        } catch (VendingMachinePersistenceException | NoItemInventoryException ex) {
            fail("Could not return quarters");
        }
    }
    
    /**
     * Test of makeNickelChange, of class Change
     */
    @Test 
    public void testMakeNickelChange() {
        try {
            Item onlyItem = service.getItem("A1");
            BigDecimal oneHundo = new BigDecimal("100");
            BigDecimal deposit = new BigDecimal("2.09");

            int remainingCash = deposit.subtract(onlyItem.getItemCost()).multiply(oneHundo).intValueExact();
            service.giveChange(remainingCash);

            assertEquals(10, remainingCash);
        } catch (VendingMachinePersistenceException | NoItemInventoryException ex) {
            fail("Could Not return Nickels");
        }
    }
    
    /**
     * Test of makeDimesChange, of class Change
     */
    @Test
    public void testMakeDimesChange() {
        try {
            Item onlyItem = service.getItem("A1");
            BigDecimal oneHundo = new BigDecimal("100");
            BigDecimal deposit = new BigDecimal("2.00");
            
            int remainingCash = deposit.subtract(onlyItem.getItemCost()).multiply(oneHundo).intValueExact();
            service.giveChange(remainingCash);
            
            assertEquals(1, remainingCash);
        } catch (VendingMachinePersistenceException | NoItemInventoryException ex) {
            fail("Could not return dimes");
        }
    }
    
    /**
     * Test of makePenniesChange, of class Change
     */
    @Test
    public void testMakePenniesChange() {
        try{
            Item onlyItem = service.getItem("A1");
            BigDecimal oneHundo = new BigDecimal("100");
            BigDecimal deposit = new BigDecimal("2.04");
            
            int remainingCash = deposit.subtract(onlyItem.getItemCost()).multiply(oneHundo).intValueExact();
            service.giveChange(remainingCash);
            
            assertEquals(5, remainingCash);
        } catch (VendingMachinePersistenceException | NoItemInventoryException ex) {
            fail("Could not return Pennies");
        }
    }
    
    
}
