/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.dao;

import com.sg.dvdlibrary.dto.Dvd;
import java.util.List;

/**
 *
 * @author raulalvarado
 */
public interface DvdLibraryDao {
    /**
     * 
     * @param dvdId
     * @param dvd
     * @return
     * @throws DvdLibraryPersistenceException 
     */
    Dvd addDvd(String dvdId, Dvd dvd) throws DvdLibraryPersistenceException;
    /**
     * 
     * @returna list of dvd stored in txt file
     * @throws DvdLibraryPersistenceException 
     */
    List<Dvd> getAllDvds() throws DvdLibraryPersistenceException;
    /**
     * 
     * @param dvdId
     * @return dvd to remove
     * @throws DvdLibraryPersistenceException 
     */
    Dvd deleteDvd(String title) throws DvdLibraryPersistenceException;
    /**
     * 
     * @param title
     * @return a single Dvd
     * @throws DvdLibraryPersistenceException 
     */
    Dvd getDvd(String title) throws DvdLibraryPersistenceException; 
    /**
     * 
     * @param title
     * @param dvd
     * @return dvd selected to edit by dvd title
     * @throws DvdLibraryPersistenceException 
     */
    Dvd editDvd(String title, Dvd dvd) throws DvdLibraryPersistenceException;
    
}
