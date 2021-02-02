/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.service;

import com.sg.vendingmachine.dao.VendingMachineAuditDao;
import com.sg.vendingmachine.dao.VendingMachineDao;
import com.sg.vendingmachine.dao.VendingMachinePersistenceException;
import com.sg.vendingmachine.dto.Change;
import com.sg.vendingmachine.dto.Item;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;

/**
 *
 * @author raulalvarado
 */
public class VendingMachineServiceLayerImpl implements VendingMachineServiceLayer {
    
    private VendingMachineDao dao;
    private VendingMachineAuditDao auditDao;
    private BigDecimal currentMoney = new BigDecimal("0");
    
    public VendingMachineServiceLayerImpl(VendingMachineDao dao, VendingMachineAuditDao auditDao) {
        this.dao = dao;
        this.auditDao = auditDao;
    }

    @Override
    public List<Item> getAllItems() throws VendingMachinePersistenceException {
        return dao.getAllItems();
    }

    @Override
    public Item getItem(String itemId) throws VendingMachinePersistenceException, NoItemInventoryException {
        if (dao.getItem(itemId) == null) {
            throw new NoItemInventoryException(
                    "Item " + itemId + " not in inventory");
        } return dao.getItem(itemId);
    }

    @Override
    public Change purchaseItem(String itemId) throws VendingMachinePersistenceException, NoItemInventoryException, InsufficientFundsException {
        Item item = dao.getItem(itemId);
        BigDecimal cien = new BigDecimal("100");
        
        if (validateItem(itemId)) {
            if (currentMoney.compareTo(item.getItemCost()) >= 0) {
                // if item is in stock
                int remainingMoney = currentMoney.subtract(item.getItemCost()).multiply(cien).intValueExact();
                // reduce item inventory by 1
                removeItem(itemId);
                // write auditEntry
                auditDao.itemAuditEntry(itemId + " : " + item.getItemName());
                // return change
                return giveChange(remainingMoney);
            } else {
                throw new InsufficientFundsException(
                        "Not Enough Funds");
            }
        } else {
            throw new NoItemInventoryException(
                    "Item Sold Out");
        }
    }

    @Override
    public Item removeItem(String itemId) throws VendingMachinePersistenceException, NoItemInventoryException {
        Item removeItem = dao.reduceInventory(itemId);
        return removeItem;
    }

    @Override
    public void setMachineMoney(BigDecimal currentMoney) {
        this.currentMoney = this.currentMoney.add(currentMoney, MathContext.UNLIMITED);
    }

    @Override
    public BigDecimal getMachineMoney() {
        return currentMoney;
    }

    @Override
    public Change giveChange(int remainingCash) throws VendingMachinePersistenceException {
        this.currentMoney = new BigDecimal("0");
        return new Change(remainingCash);
    }

    @Override
    public Change cancelAndGiveChange() throws VendingMachinePersistenceException {
        BigDecimal cien = new BigDecimal("100");
        int remainingCash = currentMoney.multiply(cien).intValueExact();
        return giveChange(remainingCash);
    }
    
    // helper method to valiudate Item Data
    private Boolean validateItem(String itemId) throws VendingMachinePersistenceException, NoItemInventoryException {
        Item item = dao.getItem(itemId);
        if (item == null) {
            throw new NoItemInventoryException(
                    "Item " + itemId + " not in inventory");
        }
        
        if (item.getItemQty() <= 0) {
            throw new VendingMachinePersistenceException(
                    "Item not availible cannot purchase");
        } else {
            return true;
        }
    } 
}
