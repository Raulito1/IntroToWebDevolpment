/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.dto.Item;
import com.sg.vendingmachine.service.NoItemInventoryException;
import java.util.List;

/**
 *
 * @author raulalvarado
 */
public interface VendingMachineDao {
    
    List<Item> getAllItems() throws VendingMachinePersistenceException;
    
    public void purchaseItem(String itemId) throws VendingMachinePersistenceException;
    
    Item getItem(String itemId) throws VendingMachinePersistenceException;
    
    Item reduceInventory(String itemId) throws 
            VendingMachinePersistenceException,
            NoItemInventoryException;
}
