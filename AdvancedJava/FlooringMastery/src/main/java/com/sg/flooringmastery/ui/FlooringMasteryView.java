/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.ui;

import com.sg.flooringmastery.dto.Order;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author raulalvarado
 */
public class FlooringMasteryView {
    
    private UserIO io;
    
    public FlooringMasteryView(UserIO io) {
        this.io = io;
    }
    
    public int printMenuAndGetSelection() {
        io.print("* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");
        io.print("*                                                                   *");
        io.print("*                       <<Floorinng Program>>                                             *");
        io.print("* 1. Display An Order                                               *");
        io.print("* 2. Add an Order                                                   *");
        io.print("* 3. Edit an Order                                                  *");
        io.print("* 4. Remove an Order                                                *");
        io.print("* 5. Quit                                                           *");
        io.print("*                                                                   *");
        io.print("* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");
        
        return io.readInt("Select an Option", 1, 5);
    }

    public void displayDisplayOrdersBanner() {
        io.print("==== Display Orders ====");
    }

    public void displayDateBanner(LocalDate date) {
        System.out.printf("==== Order %s ====\n", date);
    }

    public void displayOrdersByDate(List<Order> ordersByDate) {
        for (Order order : ordersByDate) {
            io.print(order.getOrderNum() + "| " + order.getCustomerName() + "| "
                    + io.readMoney(order.getTotal()));
        }
    }
    
    public LocalDate dateInput() {
        return io.readDate("Enter a Date. ex(MM/dd/yyyy)", LocalDate.of(2000,1,1) ,LocalDate.of(2022,1,1));
    }
    
    public int enterOrderNum() {
        return io.readInt("Enter an Order number");
    }
    
    public String enterCustomerName() {
        return io.readString("Enter Customer Name");
    }
    
    public void displayEditOrderBanner() {
        io.print("==== Edit Order ====");
    }
    
    public void displayRemoveOrderBanner() {
        io.print("==== Remove Order ====");
    }
    
    public void displayExitBanner() {
        io.print("GOOD BYE!!!");
    }
    
    public void displayUnknownCommand() {
        io.print("Unknown Command");
    }
    
    public void displayErrorMessage(String errorMsg) {
        io.print("==== ERROR ====");
        io.print(errorMsg);
    }
    
    public void displayDisplayContinueBanner() {
        io.readString("ENTER to continue");
    }
    
    public String askRemoveOrder() {
        return io.readString("Are you sure you want to remove this order? y/n", 1);
    }
    
    public String askStateAbbr() {
        return io.readString("Enter the State abbreviation ex.(OH, IN, PA, MI)", 2);
    }

    public String askForProductType() {
        return io.readString("Enter Product type ex(Laminate, Carpet, Wood, Tile, etc.)", 15);
    }
    
    public BigDecimal askForArea() {
        return io.readBigDecimal("Enter a number for Area", 2, BigDecimal.ZERO);
    }
    
    public String askToSave() {
        return io.readString("Would you like to save this order? y/n", 1);
    }
    
    public void displayNewOrder(Order order) {
        io.print("Date: " + order.getDate());
        io.print("Customer" + order.getCustomerName());
        io.print("State: " + order.getStateAbbr());
        io.print("TaxRate: " + order.getTaxRate() + "%");
        io.print("Product: " + order.getProductType());
        io.print("Material cost per SqFt: " + io.readMoney(order.getProductCostPerSqFt()));
        io.print("Labor cost per SqFt: " + io.readMoney(order.getLaborCostPerSqFt()));
        io.print("Area: " + order.getArea());
        io.print("Material cost: " + io.readMoney(order.getProductCost()));
        io.print("Labor cost: " + io.readMoney(order.getLaborCost()));
        io.print("Tax: " + io.readMoney(order.getTax()));
        io.print("TOTAL: " + io.readMoney(order.getTotal()));
    }
    
    public void displayAddOrderSuccess(boolean success, Order order) {
        if (success == true) {
            io.print("Order number: " + order.getOrderNum() + " was successfully CREATED");
        } else {
            io.print("Order was not CREATED");
            displayDisplayContinueBanner();
        }
    }
    
    public void displayRemoveOrderSuccess(boolean success, Order order) {
        if (success == true) {
            io.print("Order number: " + order.getOrderNum() + " was successfully REMOVED");
        } else {
            io.print("Order was not REMOVED");
            displayDisplayContinueBanner();
        }
    }
    
    public void displayEditOrderSuccess(boolean success, Order order) {
        if (success == true) {
            io.print("Order number: " + order.getOrderNum() + " was successfully UPDATED");
        } else {
            io.print("Order was not UPDATED");
            displayDisplayContinueBanner();
        }
    }
    
    public Order editOrder(Order order) {
        Order editedOrder = new Order();
        
        io.print("current Customer: " + order.getCustomerName());
        editedOrder.setCustomerName(enterCustomerName());
        
        io.print("current State: " + order.getStateAbbr());
        editedOrder.setStateAbbr(askStateAbbr());
        
        io.print("current Product: " + order.getProductType());
        editedOrder.setProductType(askForProductType());
        
        io.print("current Area: " + order.getArea());
        editedOrder.setArea(askForArea());
        
        return editedOrder;
    }
    
    public Order getOrder() {
        Order order = new Order();
        order.setDate(dateInput());
        order.setCustomerName(enterCustomerName());
        order.setStateAbbr(askStateAbbr());
        order.setProductType(askForProductType());
        order.setArea(askForArea());
        return order;
    }
}
