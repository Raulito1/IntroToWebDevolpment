/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Order;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author raulalvarado
 */
public interface FlooringMasteryOrderDao {
    
    List<Order> getOrders(LocalDate date) throws FlooringMasteryPersistenceException;
    
    Order addOrder(Order order)  throws FlooringMasteryPersistenceException;
    
    Order editOrder(Order editedOrder) throws FlooringMasteryPersistenceException;
    
    Order removeOrder(Order orderChosen) throws FlooringMasteryPersistenceException;
}


