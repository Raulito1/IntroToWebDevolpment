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
public class ProductValidationException extends Exception {
    
    public ProductValidationException(String message) {
        super(message);
    }
    
    public ProductValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}
