/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.ui;

import com.sg.dvdlibrary.dto.Dvd;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 *
 * @author raulalvarado
 */
public class DvdLibraryView {
    
    private UserIO io;
    
    public DvdLibraryView(UserIO io) {
        this.io = io;
    }
    
    public void displayWelcomeBanner() {
        io.print("===================================");
        io.print("=                                 =");
        io.print("=  WELCOME TO DVD LIBRARY PROJECT =");
        io.print("=                                 =");
        io.print("===================================");
    }
    
    public int printMenuAndGetSelection() {
        io.print("==== Main Menu ====");
        io.print("1. Add a Dvd");
        io.print("2. Remove a Dvd");
        io.print("3. List All Dvds");
        io.print("4. Edit a Dvd");
        io.print("5. Search Dvd by title");
        io.print("6. Exit");
        
        return io.readInt("Please select from the above choices", 1, 6);
    }
    
    public Dvd getNewDvdInfo() {
        int dvdId = io.readInt("Give Your dvd an Id # (eg. 001, 002, etc.)");
        String dvdTitle = io.readString("Enter dvd title");
        var date = newReleaseDate("Enter dvd release date");
        String mpaaRating = io.readString("Enter dvd MPAA Rating ex(R, PG-13, etc.)");
        String directorName = io.readString("Enter dvd director name");
        String studio = io.readString("Enter dvd studio");
        String userRating = io.readString("Enter user rating or any notes");
        Dvd newDvd = new Dvd(dvdId);
        newDvd.setTitle(dvdTitle);
        newDvd.setReleaseDate(date);
        newDvd.setMpaaRating(mpaaRating);
        newDvd.setDirectorName(directorName);
        newDvd.setStudio(studio);
        newDvd.setUserRating(userRating);
        return newDvd;
    }
    
    public void displayCreateDvdBanner() {
        io.print("==== Add dvd ====");
    }
    
    public void displayCreateSuccessbanner() {
        io.readString("Dvd successfully created! hit ENTER to continue");
    }
    
    public void displayRemoveDvdBanner() {
        io.print("==== Remove a dvd ====");
    }
    
    public void displaySuccessRemoveDvdBanner() {
        io.readString("Dvd successfully remove! hit ENTER to continue");
    }
    
    public void displaySearchDvdBanner() {
        io.print("==== Search a dvd ====");
    }
    
    public String getDvdTitle() {
        return io.readString("Enter a dvd title");
    }
    
    public void editDvdBanner() {
        io.print("==== Edit a dvd ====");
    }
    
    public void editDvd(Dvd dvd) {
        if (dvd != null) {
            String dvdTitle = io.readString("re-enter dvd title");
            var date = newReleaseDate("re-enter release date");
            String mpaaRating = io.readString("re-enter Mpaa Rating");
            String directorName = io.readString("re-enter director name");
            String studio = io.readString("re-enter name of studio");
            String userRating = io.readString("re-enter dvd user rating or comments");
            dvd.setTitle(dvdTitle);
            dvd.setReleaseDate(date);
            dvd.setMpaaRating(mpaaRating);
            dvd.setDirectorName(directorName);
            dvd.setStudio(studio);
            dvd.setUserRating(userRating);
        } else {
            io.print(" Dvd does not exisit");
        }
        io.readString("Successfully edited dvd. hit ENTER to continue");
    }
    
    public void unknownCommand() {
        io.print("Unknown Command");
    }
    
    public void exitMessage() {
        io.print("Good Bye!!");
    }
    
    public void searchDvd(Dvd dvd) {
        if (dvd != null) {
            io.print(dvd.getTitle());
            io.print(dvd.getMpaaRating());
            io.print(dvd.getDirectorName());
            io.print(dvd.getStudio());
            io.print(dvd.getUserRating());
        } else {
            io.print("Dvd doesnt exist");
        }
        io.readString("Hit ENTER to continue");
    }
   
    
    public LocalDate newReleaseDate(String userInput) {
        Dvd dvd = new Dvd();
        userInput = io.readString("Enter release date in format of MM-dd-yyyy");
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        LocalDate date = LocalDate.parse(userInput, dateFormat);
        dvd.setReleaseDate(date);
        return date;
     }
    
     public void displayErrorMessage(String errorMsg) {
        io.print("=== ERROR ===-");
        io.print(errorMsg);
    }
     
    public void displayDvdList(List<Dvd> dvdList) {
        for (Dvd currentDvd : dvdList) {
            io.print(currentDvd.getDvdId() + "| " + currentDvd.getTitle() + "| " 
            + currentDvd.getReleaseDate() + "| " + currentDvd.getMpaaRating() + "| "
            + currentDvd.getDirectorName() + "| " + currentDvd.getStudio());
            
        }
        io.readString("hit ENTER to continue");
    }
    
    public void displayDvdListBanner() {
        io.print("==== All dvds ====");
    }
}
