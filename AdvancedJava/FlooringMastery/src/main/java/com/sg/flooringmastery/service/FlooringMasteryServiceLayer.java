/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.service;

import com.sg.flooringmastery.dao.FlooringMasteryPersistenceException;
import com.sg.flooringmastery.dto.Order;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author raulalvarado
 */
public interface FlooringMasteryServiceLayer {
    List<Order>getOrders(LocalDate date) throws FlooringMasteryPersistenceException, InvalidOrderNumberException;
    
    Order getSingleOrder(LocalDate date, int orderNum) throws FlooringMasteryPersistenceException, InvalidOrderNumberException;
    
    Order addOrder(Order order)throws FlooringMasteryPersistenceException;
    
    Order editOrder(Order editOrder) throws FlooringMasteryPersistenceException,InvalidOrderNumberException;
    
    Order removeOrder(Order removeOrder) throws FlooringMasteryPersistenceException, InvalidOrderNumberException;
    
    Order addUpOrderData(Order order) throws FlooringMasteryPersistenceException, OrderValidationException, StateValidationException,ProductValidationException;
    
    Order compareOrder(Order saveOrder, Order editedOrder) throws FlooringMasteryPersistenceException,StateValidationException, ProductValidationException;
            
}
