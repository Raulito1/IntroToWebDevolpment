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
public class StateValidationException extends Exception {
    
    public StateValidationException(String message) {
        super(message);
    }
    
    public StateValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}
