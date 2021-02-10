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
public class NoItemInventoryException extends Exception {
    
    public NoItemInventoryException(String msg) {
        super(msg);
    }
    
    public NoItemInventoryException(String msg, Throwable cause) {
        super(msg,cause);
    }
    
}
