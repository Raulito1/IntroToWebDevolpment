/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.service;

import com.sg.vendingmachine.dao.PersistenceException;
import com.sg.vendingmachine.dao.VendingMachineDAO;
import com.sg.vendingmachine.model.Item;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author raulalvarado
 */
public class VendingMachineServiceLayerImpl implements VendingMachineServiceLayer {
    
    VendingMachineDAO dao;
    
    @Inject
    public VendingMachineServiceLayerImpl(VendingMachineDAO dao) {
        this.dao = dao;
    }
    
    // Var set with values used in methods below
    private BigDecimal currentMoney = new BigDecimal("0");
    private String message = "Vending Machine";
    private String changeMessage = "";
    private int selection = 0;
    

    @Override
    public List<Item> getAllItems() throws PersistenceException {
        return dao.getAllItems();
    }

    @Override
    public Item getItem(int itemId) throws PersistenceException {
        return dao.getItem(itemId);
    }

    @Override
    public Change purchaseItem(int itemId) throws InsufficientFundsException, NoItemInventoryException,PersistenceException {
        Item itemToPurchase = dao.getItem(itemId);
        BigDecimal oneHundred = new BigDecimal("100");
        
        if (validateItem(itemId)) {
            if (currentMoney.compareTo(itemToPurchase.getItemPrice()) >= 0) {
                int remainingCash = currentMoney.subtract(itemToPurchase.getItemPrice()).multiply(oneHundred).intValueExact();
                // reduce inventory by 1
                reduceInventory(itemId);
                // return thank you message 
                message = "Thank You!";
                // recieve chnage
                 return returnChange(remainingCash);       
            } else {
                throw new InsufficientFundsException (
                            "Please insert $" + itemToPurchase.getItemPrice().subtract(currentMoney));
            }
        } else {
            throw new NoItemInventoryException(
                        itemToPurchase.getItemName() + " Item Sold Out!!");
        }
        
    }
    
    // created a method to validate item
    private boolean validateItem(int itemId) throws PersistenceException, NoItemInventoryException {
        Item item = dao.getItem(itemId);
        if (item != null) {
            if (item.getItemQty() <= 0) {
                throw new NoItemInventoryException(
                        item.getItemName() + " Item Sold Out!!" );
            } else {
                return true;
            }
        } else {
            throw new NoItemInventoryException(
                    "You must make an item selection");
        }
    }

    @Override
    public Item reduceInventory(int itemId) throws PersistenceException, NoItemInventoryException {
        Item removeItem = dao.reduceInventory(itemId);
        return removeItem;
    }

    @Override
    public void setCurrentMoney(BigDecimal userDeposit) throws NumberFormatException {
        if (userDeposit.compareTo(new BigDecimal("0")) > 0) {
            this.currentMoney = this.currentMoney.add(userDeposit, MathContext.UNLIMITED);
            changeMessage = "";
        } else {
            throw new NumberFormatException("Money deposited must be greater than 0");
        }
    }

    @Override
    public BigDecimal getCurrentMoney() {
        return currentMoney;
    }

    @Override
    public Change returnChange(int remainingCash) throws PersistenceException {
       // update cash deposited
       this.currentMoney = new BigDecimal("0");
       // calculate change 
       Change returnChange = new Change(remainingCash);
       changeMessage(returnChange);
       return returnChange;
    }

    @Override
    public Change cancelAndReturnChange() throws PersistenceException,InsufficientFundsException {
        if (currentMoney.compareTo(new BigDecimal("0")) > 0) {
            BigDecimal oneHundred = new BigDecimal("100");
            int remainingCash = currentMoney.multiply(oneHundred).intValueExact();
            Change returnedChange = new Change(remainingCash);
            changeMessage(returnedChange);
            return returnChange(remainingCash);
        } else {
            throw new InsufficientFundsException("No money to return change on.");
        }
    }

    @Override
    public void setSelection(int selection) {
        this.selection = selection;
        message = "Vending Machine";
        changeMessage = "";
    }

    @Override
    public int getSelection() {
        return selection;
    }

    @Override
    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public String getMessageForChange() {
        return changeMessage;
    }
    
    private void changeMessage(Change changeToReturn) {
        changeMessage = ("Quarters:" + changeToReturn.getNumQuarters()
                + " Dimes:" + changeToReturn.getNumDimes()
                + "\nNickels:" + changeToReturn.getNumNickels()
                + " Pennies:" + changeToReturn.getNumPennies());
        this.selection = 0;
    }
    
}
