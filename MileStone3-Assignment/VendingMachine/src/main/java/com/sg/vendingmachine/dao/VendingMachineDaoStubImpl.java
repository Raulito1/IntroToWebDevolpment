/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.dto.Item;
import com.sg.vendingmachine.service.NoItemInventoryException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author raulalvarado
 */
public class VendingMachineDaoStubImpl implements VendingMachineDao {
    
    private List<Item> itemList = new ArrayList<>();
    private Item onlyItem;
    
    public VendingMachineDaoStubImpl() {
        // create and item to use for test purposes
        onlyItem = new Item();
        onlyItem.setItemId("A1");
        onlyItem.setItemName("Snickers");
        onlyItem.setItemCost(new BigDecimal("1.99"));
        onlyItem.setItemQty(1);
        
        // add item to my List
        itemList.add(onlyItem);
    }

    @Override
    public List<Item> getAllItems() throws VendingMachinePersistenceException {
        return itemList;
    }

    @Override
    public void purchaseItem(String itemId) throws VendingMachinePersistenceException {
        onlyItem.purchaseItem();
    }

    @Override
    public Item getItem(String itemId) throws VendingMachinePersistenceException {
        if (itemId.equals(onlyItem.getItemId())) {
            return onlyItem;
        } else {
            return null;
        }
    }

    @Override
    public Item reduceInventory(String itemId) throws VendingMachinePersistenceException, NoItemInventoryException {
        Item removedItem = onlyItem;
        if (removedItem.getItemQty() > 0) {
            removedItem.setItemQty(removedItem.getItemQty() - 1);
        } else {
            throw new NoItemInventoryException(
                    "Item Sold out");
        } return removedItem;
    }
    
}
