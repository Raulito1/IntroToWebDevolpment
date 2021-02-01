/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.dao;

/**
 *
 * @author raulalvarado
 */
public interface DvdLibraryAuditDao {
    
    public void writeAuditEntry(String entry) throws DvdLibraryPersistenceException;
}
