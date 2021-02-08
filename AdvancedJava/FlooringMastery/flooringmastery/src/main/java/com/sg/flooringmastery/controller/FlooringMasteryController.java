/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.controller;

import com.sg.flooringmastery.dao.FlooringMasteryPersistenceException;
import com.sg.flooringmastery.dto.OrderData;
import com.sg.flooringmastery.service.FlooringMasteryInvalidOrderNumException;
import com.sg.flooringmastery.service.FlooringMasteryOrderValidationException;
import com.sg.flooringmastery.service.FlooringMasteryProductValidationException;
import com.sg.flooringmastery.service.FlooringMasteryServiceLayer;
import com.sg.flooringmastery.service.FlooringMasteryStateValidationException;
import com.sg.flooringmastery.ui.FlooringMasteryView;
import java.time.LocalDate;

/**
 *
 * @author raulalvarado
 */
public class FlooringMasteryController {
    
    FlooringMasteryServiceLayer service;
    FlooringMasteryView view;
    
    public FlooringMasteryController(FlooringMasteryServiceLayer service, FlooringMasteryView view) {
        this.service = service;
        this.view = view;
    }
    
    public void run() throws FlooringMasteryOrderValidationException, FlooringMasteryStateValidationException, FlooringMasteryProductValidationException {
        
        boolean keepGoing = true;
        int menuSelection = 0;
        try {
            while (keepGoing) {
                
            
            menuSelection = getMenuSelection();
            
            switch (menuSelection) {
                case 1:
                    getOrdersByDate();
                    break;
                case 2:
                    addOrder();
                    break;
                case 3:
                    editOrder();
                    break;
                case 4:
                    removeOrder();
                    break;
                case 5:
                    keepGoing = false;
                    break;
                default:
                    unknownCommand();
            }
        }  
        exitMessage();
    } catch (FlooringMasteryPersistenceException e) {
       view.displayErrorMessage(e.getMessage());
    }
}
    
    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }
    
    private void getOrdersByDate() throws FlooringMasteryPersistenceException {
        LocalDate date = view.dateInput();
        view.displayDateBanner(date);
        try {
            view.displayOrdersByDate(service.getOrders(date));
            view.displayDisplayContinueBanner();
        } catch (FlooringMasteryInvalidOrderNumException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }
    
    private void addOrder() throws FlooringMasteryPersistenceException, FlooringMasteryOrderValidationException, FlooringMasteryStateValidationException, FlooringMasteryProductValidationException {
        OrderData order = service.addUpOrderData(view.getOrder());
        view.displayNewOrder(order);
        String userInput = view.askToSave();
        if (userInput.equalsIgnoreCase("Y")) {
            service.addOrder(order);
            view.displayAddOrderSuccess(true, order);
        } else if (userInput.equalsIgnoreCase("N")) {
            view.displayAddOrderSuccess(false, order);
        } else {
            unknownCommand();
        }
    }
    
    private void editOrder() throws FlooringMasteryPersistenceException {
        view.displayEditOrderBanner();
        try {
            LocalDate date = view.dateInput();
            int orderNum = view.enterOrderNum();
            OrderData orderSaved = service.getSingleOrder(date, orderNum);
            OrderData orderToBeEdited = view.editOrder(orderSaved);
            OrderData orderUpdated = service.compareOrder(orderSaved, orderToBeEdited);
            view.displayEditOrderBanner();
            String userInput = view.askToSave();
            if (userInput.equalsIgnoreCase("Y")) {
                service.editOrder(orderUpdated);
                view.displayEditOrderSuccess(true, orderUpdated);
            } else if (userInput.equalsIgnoreCase("N")) {
                view.displayEditOrderSuccess(false, orderUpdated);
            } else {
                unknownCommand();
            }   
        } catch (FlooringMasteryInvalidOrderNumException | FlooringMasteryProductValidationException | FlooringMasteryStateValidationException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }
    
    private void removeOrder() throws FlooringMasteryPersistenceException {
        view.displayRemoveOrderBanner();
        LocalDate date = view.dateInput();
        view.displayDateBanner(date);
        try {
            view.displayOrdersByDate(service.getOrders(date));
            int orderNum = view.enterOrderNum();
            OrderData order = service.getSingleOrder(date, orderNum);
            view.displayRemoveOrderBanner();
            view.displayNewOrder(order);
            String userInput = view.askRemoveOrder();
            
            if (userInput.equalsIgnoreCase("Y")) {
                service.removeOrder(order);
                view.displayRemoveOrderSuccess(true, order);
            } else if (userInput.equalsIgnoreCase("N")) {
                view.displayRemoveOrderSuccess(false, order);
            } else {
                unknownCommand();
            }
        } catch (FlooringMasteryInvalidOrderNumException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }
    
    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }
    
    private void exitMessage() {
        view.displayExitBanner();
    }
}