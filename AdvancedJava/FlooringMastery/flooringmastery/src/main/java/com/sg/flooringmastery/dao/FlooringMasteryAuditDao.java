/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

/**
 *
 * @author raulalvarado
 */
public interface FlooringMasteryAuditDao {
    
    public void writeAuditEntry(String entry) throws FlooringMasteryPersistenceException;
}
