/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.classroster.dao;

/**
 *
 * @author raulalvarado
 */
public class ClassRosterDaoException extends Exception {
    
    public ClassRosterDaoException (String message) {
        super(message);
    }
    
    public ClassRosterDaoException(String message, Throwable cause) {
        super(message, cause);
    }
}
