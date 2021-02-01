/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.service;

import com.sg.dvdlibrary.dao.DvdLibraryAuditDao;
import com.sg.dvdlibrary.dao.DvdLibraryDao;
import com.sg.dvdlibrary.dao.DvdLibraryPersistenceException;
import com.sg.dvdlibrary.dto.Dvd;
import java.util.List;

/**
 *
 * @author raulalvarado
 */
public class DvdLibraryServiceLayerImpl implements DvdLibraryServiceLayer {
    
    private DvdLibraryAuditDao auditDao;
    // DAO member
    DvdLibraryDao dao;
    // Constructor
    public DvdLibraryServiceLayerImpl(DvdLibraryDao dao, DvdLibraryAuditDao auditDao) {
        this.dao = dao;
        this.auditDao = auditDao;
    }

    @Override
    public void createDvd(Dvd dvd) throws DvdLibraryDuplicateTitleException, DvdLibraryDataValidationException, DvdLibraryPersistenceException {
        // Frist check to see if there is already a Dvd
        // associated with the given dvd title
        // If so, we're all done here -
        // throw a DvdLibraryDUolicateTitleException
        if (dao.getDvd(dvd.getTitle()) != null) {
            throw new DvdLibraryDuplicateTitleException(
                    "ERROR: Could not create dvd. dvd title "
                    + dvd.getTitle() 
                    + " already exists");
        }
        // Now validate all the fields on the given Dvd object
        // This method will throw an
        // exception if any of the validation rules are violated
        validateDvdData(dvd);
        
        // We passed all our business rukes checks so go ahead
        // and persist the Dvd object
        dao.addDvd(dvd.getTitle(), dvd);
        
        // The dvd was successfully createsd, now write to the audit log
        auditDao.writeAuditEntry(
                "Dvd " + dvd.getTitle() + " CREATED.");
    }

    @Override
    public List<Dvd> getAllDvds() throws DvdLibraryPersistenceException {
            return dao.getAllDvds();
    }

    @Override
    public Dvd getDvd(String title) throws DvdLibraryPersistenceException {
        return dao.getDvd(title);
    }

    @Override
    public Dvd removeDvd(String title) throws DvdLibraryPersistenceException {
        Dvd removedDvd = dao.deleteDvd(title);
        // Write to audit log
        auditDao.writeAuditEntry("Dvd " + title + " REMOVED.");
        return removedDvd;
    }
    
     @Override
    public Dvd editDvd(String title, Dvd dvd) throws DvdLibraryPersistenceException {
        return dao.editDvd(title, dvd);
    }
    
    // helper method to validate the dvd data being entered 
    private void validateDvdData(Dvd dvd) throws
            DvdLibraryDataValidationException {
        
        if (dvd.getTitle() == null
                || dvd.getTitle().trim().length() == 0
                || dvd.getReleaseDate() == null
                || dvd.getReleaseDate().trim().length() == 0
                || dvd.getMpaaRating() == null
                || dvd.getMpaaRating().trim().length() == 0
                || dvd.getDirectorName() == null
                || dvd.getDirectorName().trim().length() == 0
                || dvd.getStudio() == null
                || dvd.getStudio().trim().length() == 0
                || dvd.getUserRating() == null
                || dvd.getUserRating().trim().length() == 0) {
            
            throw new DvdLibraryDataValidationException(
                    "ERROR: All fields [Title, Release Date, Mpaa Rating, Director Name, Studio, User Rating] are required.");
        }
    }
   
}
