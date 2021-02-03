/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.dto.Item;
import com.sg.vendingmachine.service.NoItemInventoryException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author raulalvarado
 */
public class VendingMachineDaoFileImpl implements VendingMachineDao {
    
    private static final String MACHINE_FILE = "machineitem.txt";
    private static final String DELIMITER = "::";
    private Map<String, Item> items = new HashMap<>();

    @Override
    public List<Item> getAllItems() throws VendingMachinePersistenceException {
        loadMachine();
        return new ArrayList<>(items.values());
    }

    @Override
    public void purchaseItem(String itemId) throws VendingMachinePersistenceException {
        Item item = getItem(itemId);
        item.purchaseItem();
    }

    @Override
    public Item getItem(String itemId) throws VendingMachinePersistenceException {
        loadMachine();
        return items.get(itemId);
    }

    @Override
    public Item reduceInventory(String itemId) throws VendingMachinePersistenceException, NoItemInventoryException {
        Item removedItem = items.get(itemId);
        if (removedItem.getItemQty() > 0) {
            removedItem.setItemQty(removedItem.getItemQty() - 1);
        } else {
            throw new NoItemInventoryException(removedItem.getItemId() + " item is not availible");
        }
        return removedItem;
    }
    
    private void loadMachine() throws VendingMachinePersistenceException {
        Scanner scanner;
        
        try {
            // create a scanner for reading of the file
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(MACHINE_FILE)));
        } catch (FileNotFoundException e) {
            throw new VendingMachinePersistenceException(
                    "-_- Could not load data into memory", e);
        }
        // currentLine holds the most recent line read from the file
        String currentLine;
        String[] currentTokens;
        while (scanner.hasNextLine()) {
            try {
                // get the next line in the file
                currentLine = scanner.nextLine();
                // break up the line into tokens
                currentTokens = currentLine.split(DELIMITER);
                
                Item currentItem = new Item();
                // set the delimiter manually 
                currentItem.setItemId(currentTokens[0]);
                currentItem.setItemName(currentTokens[1]);
                currentItem.setItemCost(new BigDecimal(currentTokens[2]));
                currentItem.setItemQty(Integer.parseInt(currentTokens[3]));
                
                // put currentItem into the map using the itemId as the key
                items.put(currentItem.getItemId(), currentItem);
            } catch (InputMismatchException e) {
                throw new VendingMachinePersistenceException(
                                "-_- Data mismatch - unable to scan in load machine", e);
            }
        }
    }
    
    
}
