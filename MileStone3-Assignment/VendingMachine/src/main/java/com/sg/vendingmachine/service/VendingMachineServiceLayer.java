/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.service;

import com.sg.vendingmachine.dao.VendingMachinePersistenceException;
import com.sg.vendingmachine.dto.Change;
import com.sg.vendingmachine.dto.Item;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author raulalvarado
 */
public interface VendingMachineServiceLayer {
    List<Item> getAllItems() throws VendingMachinePersistenceException;
    
    Item getItem(String itemId) throws VendingMachinePersistenceException, NoItemInventoryException;
    
    Change purchaseItem(String itemId) throws VendingMachinePersistenceException, NoItemInventoryException, InsufficientFundsException;
    
    Item removeItem(String itemId) throws VendingMachinePersistenceException, NoItemInventoryException;
    
    public void setMachineMoney(BigDecimal currentMoney);
    
    public BigDecimal getMachineMoney();
    
    Change giveChange(int remainingCash) throws VendingMachinePersistenceException;
    
    Change cancelAndGiveChange() throws VendingMachinePersistenceException;
}
