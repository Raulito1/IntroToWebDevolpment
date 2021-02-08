/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.service;

/**
 *
 * @author raulalvarado
 */
public class FlooringMasteryInvalidOrderNumException extends Exception {
    
    public FlooringMasteryInvalidOrderNumException(String message) {
        super(message);
    }
    
    public FlooringMasteryInvalidOrderNumException(String message,Throwable cause) {
        super(message, cause);
    }
    
}
