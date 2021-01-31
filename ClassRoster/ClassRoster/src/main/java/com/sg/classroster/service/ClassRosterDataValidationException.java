/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.classroster.service;

/**
 *
 * @author raulalvarado
 */
public class ClassRosterDataValidationException extends Exception {
    
    public ClassRosterDataValidationException(String message) {
        super(message);
    }
    
    public ClassRosterDataValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}
