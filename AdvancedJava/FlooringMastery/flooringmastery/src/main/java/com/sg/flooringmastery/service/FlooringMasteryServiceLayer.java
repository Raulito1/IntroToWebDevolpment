/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.service;

import com.sg.flooringmastery.dao.FlooringMasteryPersistenceException;
import com.sg.flooringmastery.dto.OrderData;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author raulalvarado
 */
public interface FlooringMasteryServiceLayer {
    
    List<OrderData>getOrders(LocalDate date) throws FlooringMasteryPersistenceException,FlooringMasteryInvalidOrderNumException;
    
    OrderData getSingleOrder(LocalDate date, int orderNum) throws FlooringMasteryPersistenceException,FlooringMasteryInvalidOrderNumException;
    
    OrderData addOrder(OrderData order)throws FlooringMasteryPersistenceException;
    
    OrderData editOrder(OrderData editOrder) throws FlooringMasteryPersistenceException,FlooringMasteryInvalidOrderNumException;
    
    OrderData removeOrder(OrderData removeOrder) throws FlooringMasteryPersistenceException, FlooringMasteryInvalidOrderNumException;
    
    OrderData addUpOrderData(OrderData order) throws FlooringMasteryPersistenceException,FlooringMasteryOrderValidationException,FlooringMasteryStateValidationException,FlooringMasteryProductValidationException;
    
    OrderData compareOrder(OrderData saveOrder, OrderData editedOrder) throws FlooringMasteryPersistenceException,FlooringMasteryStateValidationException, FlooringMasteryProductValidationException;
            
}
