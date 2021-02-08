/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.OrderData;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author raulalvarado
 */
public interface FlooringMasteryOrdersDao {
    
    List<OrderData> getOrders(LocalDate date) throws FlooringMasteryPersistenceException;
    
    OrderData addOrder(OrderData order)  throws FlooringMasteryPersistenceException;
    
    OrderData editOrder(OrderData editedOrder) throws FlooringMasteryPersistenceException;
    
    OrderData removeOrder(OrderData orderChosen) throws FlooringMasteryPersistenceException;
}
