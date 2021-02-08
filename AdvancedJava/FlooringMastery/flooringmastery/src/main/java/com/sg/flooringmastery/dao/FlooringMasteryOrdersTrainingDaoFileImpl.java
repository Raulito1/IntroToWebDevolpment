/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.OrderData;
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
public class FlooringMasteryOrdersTrainingDaoFileImpl implements FlooringMasteryOrdersDao {
    
     private static final String HEADER = "OrderNum:CustomerName:State:TaxRate"
        + "Product Type:Area:CostPerSqFt:LaborCostPerSqFt:"
        + "MaterialCost:LaborCost:Tax:Total";
    private static final String DELIMETER = ",";
    private int previousOrderNum;
    private String orderFolder = "orders/";
    
    public FlooringMasteryOrdersTrainingDaoFileImpl() {
        
    }
    
    public FlooringMasteryOrdersTrainingDaoFileImpl(String ordersFolder) {
        this.orderFolder = orderFolder;
    }

    @Override
    public List<OrderData> getOrders(LocalDate date) throws FlooringMasteryPersistenceException {
        return loadOrders(date);
    }

    @Override
    public OrderData addOrder(OrderData order) throws FlooringMasteryPersistenceException {
        order = cleanMyDelimeter(order);
       if (previousOrderNum == 0) {
           getPreviousOrderNum();
       }
       this.previousOrderNum++;
       order.setOrderNum(previousOrderNum);
       writePreviousOrderNum(previousOrderNum);
        
        List<OrderData> orders = loadOrders(order.getDate());
        orders.add(order);
        writeOrders(orders, order.getDate());
        
        return  order;
    }

    @Override
    public OrderData editOrder(OrderData editedOrder) throws FlooringMasteryPersistenceException {
        //checking for commas
       editedOrder = cleanMyDelimeter(editedOrder);
       int orderNum = editedOrder.getOrderNum();
       
       List<OrderData> orders = loadOrders(editedOrder.getDate());
       OrderData orderChosen = orders.stream().filter(o -> o.getOrderNum() == orderNum).findFirst().orElse(null);
       
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
    public OrderData removeOrder(OrderData orderChosen) throws FlooringMasteryPersistenceException {
        int orderNum = orderChosen.getOrderNum();
        
        List<OrderData> orders = loadOrders(orderChosen.getDate());
        OrderData orderRemoved = orders.stream().filter(o -> o.getOrderNum() == orderNum).findFirst().orElse(null);
        
        if (orderRemoved != null) {
            orders.remove(orderRemoved);
            writeOrders(orders, orderChosen.getDate());
            return orderRemoved;
        } else {
            return null;
        }
    }
    
     private void getPreviousOrderNum() throws FlooringMasteryPersistenceException {
         //still want to be able to get order num so still using this method from 
         //previous DAO fileImpl
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
        
       //does nothing since i dont want training file to write to file
    }
    
     private void writeOrders(List<OrderData> orders, LocalDate orderDate) throws FlooringMasteryPersistenceException {
       // does nothing since i dont want training DAO fileImpl to write to file
    }
     
    private List<OrderData> loadOrders(LocalDate date) throws FlooringMasteryPersistenceException {
        // i still want to be able to read file so still using method from previous
        // Dao file Impl
        Scanner scanner;
        String dateFile = date.format(DateTimeFormatter.ofPattern("MMddyyyy"));
        
        File file = new File(String.format(orderFolder + "Orders_%s.txt", dateFile));
        
        List<OrderData> orders = new ArrayList<>();
        
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
                    OrderData currentOrder = new OrderData();
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
     
    private OrderData cleanMyDelimeter(OrderData order) {
        String cleanCustomerName = order.getCustomerName().replace(DELIMETER, "");
        String cleanStateAbbr = order.getStateAbbr().replace(DELIMETER, "");
        String cleanProductType = order.getProductType().replace(DELIMETER, "");
        
        order.setCustomerName(cleanCustomerName);
        order.setStateAbbr(cleanStateAbbr);
        order.setProductType(cleanProductType);
        
        return order;
    }   
    
}
