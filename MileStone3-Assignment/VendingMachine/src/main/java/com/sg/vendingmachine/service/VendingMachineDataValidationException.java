/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.service;

/**
 *
 * @author raulalvarado
 */
public class VendingMachineDataValidationException extends Exception {
    
    public VendingMachineDataValidationException(String message) {
        super(message);
    }
    
    public VendingMachineDataValidationException(String message, Throwable cause) {
        super(message,cause);
    }
}
