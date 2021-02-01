/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.dao;

import com.sg.dvdlibrary.dto.Dvd;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author raulalvarado
 */
public class DvdLibraryDaoStubImpl implements DvdLibraryDao {
    
    // A member field of type Dvd - this represents the one and only Dvd in the DAO
    private Dvd onlyDvd;
    // A memeber field of type List<Dvd> - this is a List containing the one and only Dvd in the DAO
    private List<Dvd> dvdList = new ArrayList<>();
    
    // Constructor - here we create and initialize the one and only Dvd and add the Dvd to our List
    public DvdLibraryDaoStubImpl() {
        onlyDvd = new Dvd("Joe Dirt");
        onlyDvd.setReleaseDate("04-11-2001");
        onlyDvd.setMpaaRating("PG-13");
        onlyDvd.setDirectorName("Dennie Gordon");
        onlyDvd.setStudio("Happy Madison Productions");
        onlyDvd.setUserRating("Adventure and Comedy");
        
        dvdList.add(onlyDvd);
    }

    @Override
    public Dvd addDvd(String dvdId, Dvd dvd) throws DvdLibraryPersistenceException {
        if (dvdId.equals(onlyDvd.getTitle())) {
            return onlyDvd;
        } else {
            return null;
        }
    }

    @Override
    public List<Dvd> getAllDvds() throws DvdLibraryPersistenceException {
        return dvdList;
    }

    @Override
    public Dvd deleteDvd(String title) throws DvdLibraryPersistenceException {
        if (title.equals(onlyDvd.getTitle())) {
            return onlyDvd;
        } else {
            return null;
        }
    }

    @Override
    public Dvd getDvd(String title) throws DvdLibraryPersistenceException {
        if (title.equals(onlyDvd.getTitle())) {
            return onlyDvd;
        } else {
            return null;
        }
    }

    @Override
    public Dvd editDvd(String title, Dvd dvd) throws DvdLibraryPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
