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
    public Dvd addDvd(String dvdId, Dvd dvd) throws DvdLibraryDaoException {
        Dvd newDvd = dvds.put(dvdId, dvd);
        writeLibrary();
        return newDvd;
    }

    @Override
    public List<Dvd> getAllDvds() throws DvdLibraryDaoException {
        loadLibrary();
        return new ArrayList<>(dvds.values());
    }

    @Override
    public Dvd deleteDvd(String dvdId) throws DvdLibraryDaoException {
        Dvd removedDvd = dvds.remove(dvdId);
        writeLibrary();
        return removedDvd;
    }

    @Override
    public Dvd searchDvd(String dvdTitle) throws DvdLibraryDaoException {
        return dvds.get(dvdTitle);
    }
    
    private void loadLibrary() throws DvdLibraryDaoException {
        Scanner scanner;
        
        try {
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(DVD_LIBRARY)));
        } catch (FileNotFoundException e) {
            throw new DvdLibraryDaoException(
                    "-_- Could not load roster data into memory", e);
        }
        String currentLine;
        String[]currentTokens;
        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentTokens = currentLine.split(DELIMITER);
            Dvd currentDvd = new Dvd();
            currentDvd.setTitle(currentTokens[1]);
            currentDvd.setMpaaRating(currentTokens[2]);
            currentDvd.setDirectorName(currentTokens[3]);
            currentDvd.setStudio(currentTokens[4]);
            currentDvd.setUserRating(currentTokens[5]);
            
            dvds.put(currentDvd.getTitle(), currentDvd);
        }
        scanner.close();
    }
    
    private void writeLibrary() throws DvdLibraryDaoException {
        PrintWriter out;
        
        try {
            out = new PrintWriter(new FileWriter(DVD_LIBRARY));
        } catch (IOException e) {
            throw new DvdLibraryDaoException(
                    "Could not save dvd data", e);
        }
        
        List<Dvd> dvdList = this.getAllDvds();
        for (Dvd currentDvd : dvdList) {
            out.println(currentDvd.getDvdId() + DELIMITER
                    + currentDvd.getTitle() + DELIMITER
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
