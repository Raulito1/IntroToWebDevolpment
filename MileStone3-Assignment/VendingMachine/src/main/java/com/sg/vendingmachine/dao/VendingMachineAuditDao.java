/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.dao;

/**
 *
 * @author raulalvarado
 */
public interface VendingMachineAuditDao {
    
    public void itemAuditEntry(String entry) throws VendingMachinePersistenceException;
}
