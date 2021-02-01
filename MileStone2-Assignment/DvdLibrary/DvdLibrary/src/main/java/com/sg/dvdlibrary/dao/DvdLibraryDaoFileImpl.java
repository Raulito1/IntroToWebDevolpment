/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.dao;

import com.sg.dvdlibrary.dto.Dvd;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author raulalvarado
 */
public class DvdLibraryDaoFileImpl implements DvdLibraryDao {
    
    private Map<String, Dvd> dvds = new HashMap<>();
    public static final String DVD_LIBRARY = "dvdfile.txt";
    public static final String DELIMITER = "::";
    

    @Override
    public Dvd addDvd(String title, Dvd dvd) throws DvdLibraryPersistenceException {
        Dvd newDvd = dvds.put(title, dvd);
        try {
        writeLibrary();
        } catch (DvdLibraryPersistenceException e) {
            e.printStackTrace();
        }
        return newDvd;
    }

    @Override
    public List<Dvd> getAllDvds() throws DvdLibraryPersistenceException {
        loadLibrary();
        return new ArrayList<>(dvds.values());
    }

    @Override
    public Dvd deleteDvd(String dvdId) throws DvdLibraryPersistenceException {
        Dvd removedDvd = dvds.remove(dvdId);
        try {
            writeLibrary();
        } catch (DvdLibraryPersistenceException e) {
            e.printStackTrace();
        } 
        return removedDvd;
    } 
    
     @Override
    public Dvd getDvd(String title) throws DvdLibraryPersistenceException {
        return dvds.get(title);
    }

    @Override
    public Dvd editDvd(String title, Dvd dvd) throws DvdLibraryPersistenceException {
        dvds.put(title, dvd);
        return dvd;
    }
    

    
    private void loadLibrary() throws DvdLibraryPersistenceException {
        Scanner scanner;
        
        try {
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(DVD_LIBRARY)));
        } catch (FileNotFoundException e) {
            throw new DvdLibraryPersistenceException(
                    "-_- Could not load roster data into memory", e);
        }
        String currentLine;
        String[]currentTokens;
        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentTokens = currentLine.split(DELIMITER);
            Dvd currentDvd = new Dvd(currentTokens[0]);
            // currentDvd.setTitle(currentTokens[1]);
            currentDvd.setReleaseDate(currentTokens[1]);
            currentDvd.setMpaaRating(currentTokens[2]);
            currentDvd.setDirectorName(currentTokens[3]);
            currentDvd.setStudio(currentTokens[4]);
            currentDvd.setUserRating(currentTokens[5]);
            
            dvds.put(currentDvd.getTitle(), currentDvd);
        }
        scanner.close();
    }
    
    private void writeLibrary() throws DvdLibraryPersistenceException {
        PrintWriter out;
        
        try {
            out = new PrintWriter(new FileWriter(DVD_LIBRARY));
        } catch (IOException e) {
            throw new DvdLibraryPersistenceException(
                    "Could not save dvd data", e);
        }
        
        List<Dvd> dvdList = this.getAllDvds();
        for (Dvd currentDvd : dvdList) {
            out.println(currentDvd.getTitle() + DELIMITER
                    + currentDvd.getReleaseDate() + DELIMITER
                    + currentDvd.getMpaaRating() + DELIMITER
                    + currentDvd.getDirectorName() + DELIMITER
                    + currentDvd.getStudio() + DELIMITER
                    + currentDvd.getUserRating());
            
            out.flush();
        }
        out.close();
    }
    
}
