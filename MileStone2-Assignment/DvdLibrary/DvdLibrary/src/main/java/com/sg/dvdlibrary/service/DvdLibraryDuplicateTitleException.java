/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.service;

/**
 *
 * @author raulalvarado
 */
public class DvdLibraryDuplicateTitleException extends Exception{
    
    public DvdLibraryDuplicateTitleException(String message) {
        super(message);
    }
    
    public DvdLibraryDuplicateTitleException(String message, Throwable cause) {
        super(message, cause);
    }
}
