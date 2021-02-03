/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

/**
 *
 * @author raulalvarado
 */
public class FlooringMasteryAuditDaoImpl implements FlooringMasteryAuditDao {
    
    public static final String AUDIT_FILE = "audit.txt";

    @Override
    public void writeAuditEntry(String entry) throws FlooringMasteryPersistenceException {
        PrintWriter out;
        
        try {
            out = new PrintWriter(new FileWriter(AUDIT_FILE, true));
        } catch (IOException e) {
            throw new FlooringMasteryPersistenceException(
                    "Could not persist audit information", e);
        }
        
        LocalDateTime timeStamp = LocalDateTime.now();
        out.println(timeStamp.toString() + " : " + entry);
        out.flush();
    }  
    
}
