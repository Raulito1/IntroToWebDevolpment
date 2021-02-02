/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.ui;

import com.sg.vendingmachine.dto.Change;
import com.sg.vendingmachine.dto.Item;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author raulalvarado
 */
public class VendingMachineView {
    
    private UserIO io;
    
    public VendingMachineView(UserIO io) {
        this.io = io;
    }
    
    public void displayErrorMessage(String errorMsg) {
        io.print("==== ERROR ====");
        io.print(errorMsg);
    }
    
    public int printMenuAndGetSelection() {
        io.print("==== Main Menu ====");
        io.print("");//
        io.print("");// printed these out for spacing
        io.print("1. Deposit Money");
        io.print("2. Purchase Item");
        io.print("3. Return Change");
        io.print("4. Exit");
        io.print("");
        
        return io.readInt("Make a Selection", 1, 4);
        
    }
    
    public void displayVendingMachineList(List<Item> itemList) {
        io.print("==== Vending Machine Items ====");
        io.print("");// printed out for spacing 
        for (Item currentItem : itemList) {
            io.print(currentItem.getItemId() + "| "
                    + currentItem.getItemName() + "| $"
                    + currentItem.getItemCost() + "| "
                    + currentItem.getItemQty());
        }
    }
    
    public void displayUnknownCommand() {
        io.print("UNKOWN COMMAND");
    }
    
    public void displayExitBanner() {
        io.print("Thank You, Good Bye!!");
    }
    
    public String displayItemSelectionPrompt() {
        return io.readString("Please enter the Item ID to make selection. (ex. A1, A2, A3)");
    }
    
    public void displayItemRemoved(Item selectedItem) {
        io.print("====== Vending Item ======");
        io.print("1 " + selectedItem.getItemName() + " Vended");
    }
    
    public void displayChange(Change change) {
        io.print("Change Dispensed: ");
        io.print(change.getNumQuarters() + " Quarters");
        io.print(change.getNumDimes() + " Dimes");
        io.print(change.getNumNickels() + " Nickels");
        io.print(change.getNumPennies() + " Pennies");
    }
    
    public BigDecimal getMoneyEntry() {
        return io.readBigDecimal("Please Deposit Money");
    }
    
    public void displayCurrentMoney(BigDecimal currentMoney) {
        io.print("You have " + currentMoney + " in the machine");
    }
}
