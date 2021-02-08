/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.ui;

import com.sg.flooringmastery.dto.OrderData;
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
        io.print("*                    <<Floorinng Program>>                          *");
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
        io.print("=== Display Orders ===");
    }
    
    public void displayDateBanner(LocalDate date) {
        System.out.printf("=== Orders %s === \n" , date);
    }
    
    public void displayOrdersByDate(List<OrderData>ordersByDate) {
        for (OrderData order : ordersByDate) {
            io.print(order.getOrderNum() + " | " + order.getCustomerName() + " | "
                    + io.readMoney(order.getTotal()));
        }
    }
    
    public LocalDate dateInput() {
        return io.readDate("Enter a Date. ex.(MM/DD/YYYY)", LocalDate.of(2000,1,1), LocalDate.of(2022,1,31));
    }
    
    public int enterOrderNum() {
        return io.readInt("Enter an Order number");
    }
    
    public String enterCustomerName() {
        return io.readString("Enter a Name");
    }
    
    public void displayEditOrderBanner() {
        io.print("=== Edit Order ===");
    }
    
    public void displayRemoveOrderBanner() {
        io.print("=== Remove Order ===");
    }
    
    public void displayExitBanner() {
        io.print("GOOD BYE!!");
    }
    
    public void displayUnknownCommandBanner() {
        io.print("Unknown Command!!");
    }
    
    public void displayErrorMessage(String errorMsg) {
        io.print("=== ERROR ===");
        io.print(errorMsg);
    }
    
    public void displayDisplayContinueBanner() {
        io.readString("Hit Enter to Contine");
    }
    
    public String askRemoveOrder() {
        return io.readString("Are you sure you want to remove this order? y/n",1);
    }
    
    public String askStateAbbr() {
        return io.readString("Enter your states abbreviation. (ex. OH,IN,PA,MI)", 2);
    }
    
    public String askForProductType() {
        return io.readString("Enter the Product type. (ex. laminate,carpet,wood,tile,cork)", 15);
    }
    
    public BigDecimal askForArea() {
        return io.readBigDecimal("Enter the Area num: ", 2, BigDecimal.ZERO);
        
    }
    
    public String askToSave() {
        return io.readString("Would you like to save this Order? Y/N", 1);
    }
    
    public void displayNewOrder(OrderData order) {
        io.print("Date: " + order.getDate());
        io.print("Customer: " + order.getCustomerName());
        io.print("State: " + order.getStateAbbr());
        io.print("TaxRate: " + order.getTaxRate() + "%");
        io.print("Product: " + order.getProductType());
        io.print("Material Cost perSqFt: " + io.readMoney(order.getProductCostPerSqFt()));
        io.print("Labor Cost perSqFt: " + io.readMoney(order.getLaborCostPerSqFt()));
        io.print("Area: " + order.getArea());
        io.print("Material Cost: " + io.readMoney(order.getProductCost()));
        io.print("Labor Cost: " + io.readMoney(order.getLaborCost()));
        io.print("Tax: " + io.readMoney(order.getTax()));
        io.print("TOTAL: " + io.readMoney(order.getTotal()));
    }
    
    public void displayAddOrderSuccess(boolean success, OrderData order) {
        if (success == true) {
            io.print("Order Num: " + order.getOrderNum() + " was successfully added");
        } else {
            io.print("Order was not able to save");
            displayDisplayContinueBanner();
        }
    }
    
    public void displayRemoveOrderSuccess(boolean success, OrderData order) {
        if (success == true) {
            io.print("Order Num:" + order.getOrderNum() + " was successfully removed!");
        } else {
            io.print("Order was not able to be removed");
            displayDisplayContinueBanner();
        }
    }
    
    public void displayEditOrderSuccess(boolean success, OrderData order) {
        if (success == true) {
            io.print("Order Num:" + order.getOrderNum() + " was successfully edited");
        } else {
            io.print("Order was not able to be edited");
            displayDisplayContinueBanner();
        }
    }
    
    public OrderData editOrder(OrderData order) {
        OrderData editedOrder = new OrderData();
        
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
    
    public OrderData getOrder() {
        OrderData order = new OrderData();
        order.setDate(dateInput());
        order.setCustomerName(enterCustomerName());
        order.setStateAbbr(askStateAbbr());
        order.setProductType(askForProductType());
        order.setArea(askForArea());
        
        return order;
    }
}
