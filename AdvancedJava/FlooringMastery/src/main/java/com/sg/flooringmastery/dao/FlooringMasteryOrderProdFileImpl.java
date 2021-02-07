/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Order;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author raulalvarado
 */
public class FlooringMasteryOrderProdFileImpl implements FlooringMasteryOrderDao {
    private static final String HEADER = "OrderNum:CustomerName:State:TaxRate"
        + "Product Type:Area:CostPerSqFt:LaborCostPerSqFt:"
        + "MaterialCost:LaborCost:Tax:Total";
    private static final String DELIMETER = ",";
    private int previousOrderNum;
    //private static final String ORDERNUM_FILE = "previousOrderNum.txt";
    private String orderFolder = "orders/";
    
    public FlooringMasteryOrderProdFileImpl() {
        
    }
    public FlooringMasteryOrderProdFileImpl(String orderFolder) {
        this.orderFolder = orderFolder;
    }

    @Override
    public List<Order> getOrders(LocalDate date) throws FlooringMasteryPersistenceException {
        return loadOrders(date);
    }

    @Override
    public Order addOrder(Order order) throws FlooringMasteryPersistenceException {
        order = cleanMyDelimeter(order);
        getPreviousOrderNum();
        previousOrderNum++;
        order.setOrderNum(previousOrderNum);
        writePreviousOrderNum(previousOrderNum);
        
        List<Order> orders = loadOrders(order.getDate());
        orders.add(order);
        writeOrders(orders, order.getDate());
        
        return  order;
    }

    @Override
    public Order editOrder(Order editedOrder) throws FlooringMasteryPersistenceException {
       editedOrder = cleanMyDelimeter(editedOrder);
       int orderNum = editedOrder.getOrderNum();
       
       List<Order> orders = loadOrders(editedOrder.getDate());
       Order orderChosen = orders.stream().filter(o -> o.getOrderNum() == orderNum).findFirst().orElse(null);
       
       if (orderChosen != null) {
           int index = orders.indexOf(orderChosen);
           orders.set(index, editedOrder);
           writeOrders(orders, editedOrder.getDate());
           return editedOrder;
       } else {
           return null;
       }
    }

    @Override
    public Order removeOrder(Order orderChosen) throws FlooringMasteryPersistenceException {
        int orderNum = orderChosen.getOrderNum();
        
        List<Order> orders = loadOrders(orderChosen.getDate());
        Order orderRemoved = orders.stream().filter(o -> o.getOrderNum() == orderNum).findFirst().orElse(null);
        
        if (orderRemoved != null) {
            orders.remove(orderRemoved);
            writeOrders(orders, orderChosen.getDate());
            return orderRemoved;
        } else {
            return null;
        }
    }
    
     private void getPreviousOrderNum() throws FlooringMasteryPersistenceException {
        Scanner scanner;
        
        try {
            scanner = new Scanner(
                        new BufferedReader(
                             new FileReader(orderFolder + "previousOrderNum.txt")));
        } catch (FileNotFoundException e) {
            throw new FlooringMasteryPersistenceException(
                    "-_- Could not load OrderNum into memory", e);
        }
        int savedOrderNum = Integer.parseInt(scanner.nextLine());
        
        this.previousOrderNum = savedOrderNum;
        
        scanner.close();
    }
     
    private void writePreviousOrderNum(int previousOrderNum) throws FlooringMasteryPersistenceException {
        PrintWriter out;
        
        try {
            out = new PrintWriter(new FileWriter(orderFolder + "previousOrderNum.txt"));
        } catch (IOException e) {
            throw new FlooringMasteryPersistenceException(
                        "Could not save order num",e);
        }
        out.println(previousOrderNum);
        out.flush();
        out.close();
        
    }
    
     private void writeOrders(List<Order> orders, LocalDate orderDate) throws FlooringMasteryPersistenceException {
        PrintWriter out;
        
        String dateFile = orderDate.format(DateTimeFormatter.ofPattern("MMddyyyy"));
        
        File file = new File(String.format(orderFolder + "Orders_%s.txt", dateFile));
        try {
            out = new PrintWriter(new FileWriter(file));
        } catch (IOException e) {
            throw new FlooringMasteryPersistenceException(
                    "Could not save order data.", e);
        }
        out.println(HEADER);
        for (Order currentOrder : orders) {
            out.println(currentOrder.getOrderNum() + DELIMETER
                    + currentOrder.getCustomerName() + DELIMETER
                    + currentOrder.getStateAbbr() + DELIMETER
                    + currentOrder.getTaxRate() + DELIMETER
                    + currentOrder.getProductType() + DELIMETER
                    + currentOrder.getArea() + DELIMETER
                    + currentOrder.getProductCostPerSqFt() + DELIMETER
                    + currentOrder.getLaborCostPerSqFt() + DELIMETER
                    + currentOrder.getProductCost() + DELIMETER
                    + currentOrder.getLaborCost() + DELIMETER
                    + currentOrder.getTax() + DELIMETER
                    + currentOrder.getTotal());
            
            out.flush();
        }
        out.close();
    }
     
    private List<Order> loadOrders(LocalDate date) throws FlooringMasteryPersistenceException {
        Scanner scanner;
        String dateFile = date.format(DateTimeFormatter.ofPattern("MMddyyyy"));
        
        File file = new File(String.format(orderFolder + "Orders_%s.txt", dateFile));
        
        List<Order> orders = new ArrayList<>();
        
        if (file.isFile()) {
            try {
                scanner = new Scanner(
                            new BufferedReader(
                                 new FileReader(file)));
            } catch (FileNotFoundException e) {
                throw new FlooringMasteryPersistenceException(
                        "-_- Could not load order data into memory.", e);
            }
            String currentLine;
            String[] currentTokens;
            scanner.nextLine();
            while (scanner.hasNextLine()) {
                currentLine = scanner.nextLine();
                currentTokens = currentLine.split(DELIMETER);
                if (currentTokens.length == 12) {
                    Order currentOrder = new Order();
                    currentOrder.setDate(LocalDate.parse(dateFile, DateTimeFormatter.ofPattern("MMddyyyy")));
                    currentOrder.setOrderNum(Integer.parseInt(currentTokens[0]));
                    currentOrder.setCustomerName(currentTokens[1]);
                    currentOrder.setStateAbbr(currentTokens[2]);
                    currentOrder.setTaxRate(new BigDecimal(currentTokens[3]));
                    currentOrder.setProductType(currentTokens[4]);
                    currentOrder.setArea(new BigDecimal(currentTokens[5]));
                    currentOrder.setProductCostPerSqFt(new BigDecimal(currentTokens[6]));
                    currentOrder.setLaborCostPerSqFt(new BigDecimal(currentTokens[7]));
                    currentOrder.setProductCost(new BigDecimal(currentTokens[8]));
                    currentOrder.setLaborCost(new BigDecimal(currentTokens[9]));
                    currentOrder.setTax(new BigDecimal(currentTokens[10]));
                    currentOrder.setTotal(new BigDecimal(currentTokens[11]));
                    orders.add(currentOrder);
                } else {
                    
                }
            } 
            scanner.close();
            return orders;
        } else {
            return orders;
        }
    }
     
    private Order cleanMyDelimeter(Order order) {
        String cleanCustomerName = order.getCustomerName().replace(DELIMETER, "");
        String cleanStateAbbr = order.getStateAbbr().replace(DELIMETER, "");
        String cleanProductType = order.getProductType().replace(DELIMETER, "");
        
        order.setCustomerName(cleanCustomerName);
        order.setStateAbbr(cleanStateAbbr);
        order.setProductType(cleanProductType);
        
        return order;
    }   
}
