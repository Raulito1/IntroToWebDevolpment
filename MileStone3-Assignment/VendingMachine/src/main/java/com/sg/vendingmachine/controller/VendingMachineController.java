/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.controller;

import com.sg.vendingmachine.dao.VendingMachinePersistenceException;
import com.sg.vendingmachine.dto.Change;
import com.sg.vendingmachine.dto.Item;
import com.sg.vendingmachine.service.InsufficientFundsException;
import com.sg.vendingmachine.service.NoItemInventoryException;
import com.sg.vendingmachine.service.VendingMachineServiceLayer;
import com.sg.vendingmachine.ui.VendingMachineView;
import java.util.List;

/**
 *
 * @author raulalvarado
 */
public class VendingMachineController {
    
      // (DI) Dependency Injection for my view and service 
    private VendingMachineView view;
    private VendingMachineServiceLayer service;
    
    public VendingMachineController(VendingMachineView view, VendingMachineServiceLayer service) {
        
        this.view = view;
        this.service = service;
    }
    
    public void run() {
        
        boolean keepGoing = true;
        int menuSelection = 0;
        String userSelection = null;
        
        // try catch block for persistence exception
        try {
            // list Vending machine items with you load app
            listItems();
            // while loop ,for making a user selection while true, choices
            // will be availible, when false it will exit
            while (keepGoing) {
                // create method to get menuSelction
                menuSelection = getMenuSelection();
                // created a switch statement to show choices availible 
                // once choice is made go into that case
                // which holds the method
                switch (menuSelection) {
                    case 1:
                        depositMoney();
                        break;
                    case 2:
                        purchaseItem();
                        break;
                    case 3:
                        returnChange();
                        break;
                    case 4:
                        // case 4 is my exit method once you choose to exit 
                        // the app itll run this case in switch statemnt 
                        // causing it to be false and end the while loop
                        keepGoing = false;
                        break;
                     // also creating a default case if no proper case was inputted 
                     // in by user
                    default :
                        unknownCommand();
                }
            }
            returnChange();
            exitMessage();
        } catch (VendingMachinePersistenceException e) {
            view.displayErrorMessage(e.getMessage());
        }
       
    }
    
    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }
    
    private void listItems() throws VendingMachinePersistenceException {
        List<Item> itemList = service.getAllItems();
        view.displayVendingMachineList(itemList);
    }
    
    private void depositMoney() {
        service.setMachineMoney(view.getMoneyEntry());
        view.displayCurrentMoney(service.getMachineMoney());
    }
    
    private void purchaseItem() throws VendingMachinePersistenceException {
        boolean hasErrors = false;
        do {
            try {
                Change change = service.purchaseItem(view.displayItemSelectionPrompt());
                view.displayChange(change);
                hasErrors = true;
            } catch (VendingMachinePersistenceException | InsufficientFundsException | NoItemInventoryException e) {
                hasErrors = true;
                view.displayErrorMessage(e.getMessage());
                continue;
            }
        } while (false);
    }
    
    private void unknownCommand() {
        view.displayUnknownCommand();
    }
    
    private void exitMessage() {
        view.displayExitBanner();
    }
    
    public void returnChange() throws VendingMachinePersistenceException {
        view.displayChange(service.cancelAndGiveChange());
    }
}
