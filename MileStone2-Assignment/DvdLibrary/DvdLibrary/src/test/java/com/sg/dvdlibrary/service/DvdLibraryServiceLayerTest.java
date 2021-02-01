/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.service;

import com.sg.dvdlibrary.dao.DvdLibraryAuditDao;
import com.sg.dvdlibrary.dao.DvdLibraryAuditDaoStubImpl;
import com.sg.dvdlibrary.dao.DvdLibraryDao;
import com.sg.dvdlibrary.dao.DvdLibraryDaoStubImpl;
import com.sg.dvdlibrary.dto.Dvd;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 *
 * @author raulalvarado
 */
public class DvdLibraryServiceLayerTest {
    
    private DvdLibraryServiceLayer service;
    
    public DvdLibraryServiceLayerTest() {
        DvdLibraryDao dao = new DvdLibraryDaoStubImpl();
        DvdLibraryAuditDao auditDao = new DvdLibraryAuditDaoStubImpl();
        
        service = new DvdLibraryServiceLayerImpl(dao, auditDao);
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of createDvd method, of class DvdLibraryServiceLayer.
     */
    @Test
    public void testCreateDvd() throws Exception {
        Dvd dvd = new Dvd("test dvd1");
        dvd.setReleaseDate("01-02-2020");
        dvd.setMpaaRating("R");
        dvd.setDirectorName("Jon Doe");
        dvd.setStudio("test studio");
        dvd.setUserRating("test rating");
        service.createDvd(dvd);
    }

    /**
     * Test of getAllDvds method, of class DvdLibraryServiceLayer.
     */
    @Test
    public void testGetAllDvds() throws Exception {
        assertEquals(1, service.getAllDvds().size());
    }

    /**
     * Test of getDvd method, of class DvdLibraryServiceLayer.
     */
    @Test
    public void testGetDvd() throws Exception {
        Dvd dvd = service.getDvd("Joe Dirt");
        assertNotNull(dvd);
        dvd = service.getDvd("");
        assertNull(dvd);
    }

    /**
     * Test of removeDvd method, of class DvdLibraryServiceLayer.
     */
    @Test
    public void testRemoveDvd() throws Exception {
        Dvd dvd = service.removeDvd("test dvd");
        assertNull(dvd);
        dvd = service.removeDvd("");
        assertNull(dvd);
    }
    
    @Test
    public void testCreateDvdDuplicateTitle() throws Exception {
        Dvd dvd = new Dvd("Joe Dirt");
        dvd.setReleaseDate("01-02-2020");
        dvd.setMpaaRating("R");
        dvd.setDirectorName("Jon Doe");
        dvd.setStudio("test studio");
        dvd.setUserRating("test rating");
        
        try {
            service.createDvd(dvd);
            fail("Expected DvdLibraryDuplicateTitleException was not thrown.");
        } catch (DvdLibraryDuplicateTitleException e) {
            return;
        }
    }
    
    @Test
    public void testCreateDvdInvalidData() throws Exception {
        Dvd dvd = new Dvd("test dvd2");
        dvd.setReleaseDate("");
        dvd.setMpaaRating("R");
        dvd.setDirectorName("Jon Doe");
        dvd.setStudio("test studio");
        dvd.setUserRating("test rating");
        
        try {
            service.createDvd(dvd);
            fail("Expected DvdLibraryDataValidationException was not thrown.");
        } catch (DvdLibraryDataValidationException e) {
            return;
        }
    }
}
