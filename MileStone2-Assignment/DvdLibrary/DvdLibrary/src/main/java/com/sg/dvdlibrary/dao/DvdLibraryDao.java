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
     * @throws DvdLibraryDaoException 
     */
    Dvd addDvd(String dvdId, Dvd dvd) throws DvdLibraryDaoException;
    /**
     * 
     * @returna list of dvd stored in txt file
     * @throws DvdLibraryDaoException 
     */
    List<Dvd> getAllDvds() throws DvdLibraryDaoException;
    /**
     * 
     * @param dvdId
     * @return dvd to remove
     * @throws DvdLibraryDaoException 
     */
    Dvd deleteDvd(String title) throws DvdLibraryDaoException;
    /**
     * 
     * @param title
     * @return a single Dvd
     * @throws DvdLibraryDaoException 
     */
    Dvd getDvd(String title) throws DvdLibraryDaoException; 
    /**
     * 
     * @param title
     * @param dvd
     * @return dvd selected to edit by dvd title
     * @throws DvdLibraryDaoException 
     */
    Dvd editDvd(String title, Dvd dvd) throws DvdLibraryDaoException;
    
}
