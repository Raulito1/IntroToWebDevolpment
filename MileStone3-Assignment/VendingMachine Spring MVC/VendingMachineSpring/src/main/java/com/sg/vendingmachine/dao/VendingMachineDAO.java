/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.model.Item;
import com.sg.vendingmachine.service.NoItemInventoryException;
import java.util.List;

/**
 *
 * @author raulalvarado
 */
public interface VendingMachineDAO {
    public List<Item> getAllItems();
    public Item getItem(int itemId);
    public Item reduceInventory(int itemId) throws PersistenceException,NoItemInventoryException;
}
