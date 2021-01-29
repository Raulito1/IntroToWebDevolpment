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
     * @return
     * @throws DvdLibraryDaoException 
     */
    List<Dvd> getAllDvds() throws DvdLibraryDaoException;
    /**
     * 
     * @param dvdId
     * @return
     * @throws DvdLibraryDaoException 
     */
    Dvd deleteDvd(String dvdId) throws DvdLibraryDaoException;
    /**
     * 
     * @param dvdTitle
     * @return
     * @throws DvdLibraryDaoException 
     */
    Dvd searchDvd(String dvdTitle) throws DvdLibraryDaoException;
    
}
