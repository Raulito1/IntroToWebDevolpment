/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.model.Item;
import com.sg.vendingmachine.service.NoItemInventoryException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author raulalvarado
 */
public class VendingMachineDAOStubImpl implements VendingMachineDAO {
    
    private Map<Integer, Item> items = new HashMap<>();
    
    public VendingMachineDAOStubImpl() {
         Item item = new Item(1, "Purple Urkel", new BigDecimal(2.00), 8);
        items.put(item.getItemId(), item);
        
        item = new Item(2, "Sour Diesel", new BigDecimal(1.00), 8);
        items.put(item.getItemId(), item);
        
        item = new Item(3, "Jack Herer", new BigDecimal(1.50), 5);
        items.put(item.getItemId(), item);
        
        item = new Item(4, "Gorilla Glue", new BigDecimal(5.75), 1);
        items.put(item.getItemId(), item);
        
        item = new Item(5, "Skywalker OG", new BigDecimal(3.00), 2);
        items.put(item.getItemId(), item);
        
        item = new Item(6, "Hindu Kush", new BigDecimal(2.25), 5);
        items.put(item.getItemId(), item);
        
        item = new Item(7, "Jet Fuel", new BigDecimal(5.00), 3);
        items.put(item.getItemId(), item);
        
        item = new Item(8, "Krunk Skunk", new BigDecimal(8.00), 5);
        items.put(item.getItemId(), item);
        
        item = new Item(9, "White Widow", new BigDecimal(10.00), 4);
        items.put(item.getItemId(), item);
    }

    @Override
    public List<Item> getAllItems() {
        return new ArrayList<>(items.values());
    }

    @Override
    public Item getItem(int itemId) {
        return items.get(itemId);
    }

    @Override
    public Item reduceInventory(int itemId) throws PersistenceException, NoItemInventoryException {
        Item removeItem = items.get(itemId);
        if (removeItem.getItemQty() > 0) {
            removeItem.setItemQty(removeItem.getItemQty() - 1);
        } else {
            throw new NoItemInventoryException("SoldOut");
        } return removeItem;
    }
    
}
