/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.service;

import com.sg.vendingmachine.dao.PersistenceException;
import com.sg.vendingmachine.model.Item;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author raulalvarado
 */
public interface VendingMachineServiceLayer {
    
    List<Item> getAllItems() throws PersistenceException;
    
    Item getItem(int itemId) throws PersistenceException;
    
    Change purchaseItem(int itemId) throws InsufficientFundsException, PersistenceException, NoItemInventoryException;
    
    Item reduceInventory(int itemId) throws PersistenceException, NoItemInventoryException;
    
    public void setCurrentMoney(BigDecimal userDeposit);
    
    public BigDecimal getCurrentMoney();
    
    public Change returnChange(int remainingCash)throws PersistenceException;
    
    public Change cancelAndReturnChange()throws PersistenceException,InsufficientFundsException;
    
    public void setSelection(int selection);
    
    public int getSelection();
    
    public void setMessage(String message);
    
    public String getMessage();
    
    public String getMessageForChange();
}
