/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.controller;

import com.sg.dvdlibrary.dao.DvdLibraryPersistenceException;
import com.sg.dvdlibrary.dto.Dvd;
import com.sg.dvdlibrary.service.DvdLibraryDataValidationException;
import com.sg.dvdlibrary.service.DvdLibraryDuplicateTitleException;
import com.sg.dvdlibrary.service.DvdLibraryServiceLayer;
import com.sg.dvdlibrary.ui.DvdLibraryView;
import java.util.List;

/**
 *
 * @author raulalvarado
 */
public class DvdLibraryController {
    
    private DvdLibraryView view;
    private DvdLibraryServiceLayer service;
    
    public DvdLibraryController(DvdLibraryServiceLayer service, DvdLibraryView view) {
        this.service = service;
        this.view = view;
    }
    
    public void run() {
        boolean keepGoing = true;
        int menuSelection = 0;
        try {
            
            view.displayWelcomeBanner();
            System.out.println("");
            
            while (keepGoing) {
                menuSelection = getMenuSelection();
                
                switch (menuSelection) {
                    case 1:
                        listOfDvds();
                        break;
                    case 2:
                        createDvd();
                        break;
                    case 3:
                        viewDvd();
                        break;
                    case 4:
                        removeDvd();
                        break;
                    case 5:
                        editDvd();
                        break;
                    case 6:
                        keepGoing = false;
                        break;
                    default:
                        unknownCommand();
                        break;
                }
            }
            exitMessage();
        } catch (DvdLibraryPersistenceException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }
    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }
    
    private void createDvd() throws DvdLibraryPersistenceException {
        view.displayCreateDvdBanner();
        boolean hasErrors = false;
        do {
            Dvd currenDvd = view.getNewDvdInfo();
            try {
                service.createDvd(currenDvd);
                view.displayCreateSuccessbanner();
                hasErrors = false;
            } catch (DvdLibraryDuplicateTitleException | DvdLibraryDataValidationException e) {
                hasErrors = true;
                view.displayErrorMessage(e.getMessage());
            }
        } while (hasErrors);
    }
    
    private void listOfDvds() throws DvdLibraryPersistenceException {
        view.displayDvdListBanner();
        List<Dvd> dvdList = service.getAllDvds();
        view.displayDvdList(dvdList);
    }
    
    private void editDvd() throws DvdLibraryPersistenceException {
        view.editDvdBanner();
        String dvdTitle = view.getDvdTitleChoice();
        Dvd firstDvd = service.getDvd(dvdTitle);
        Dvd editedDvd = view.editDvd(firstDvd);
        service.editDvd(editedDvd.getTitle(), editedDvd);
        view.editSuccessBanner();
    }
    
    private void removeDvd() throws DvdLibraryPersistenceException {
        view.displayRemoveDvdBanner();
        String dvdId = view.getDvdTitle();
        service.removeDvd(dvdId);
        view.displaySuccessRemoveDvdBanner();
    }
    
    public void viewDvd() throws DvdLibraryPersistenceException {
        view.displayDvdBanner();
        String title = view.getDvdTitleChoice();
        Dvd dvd = service.getDvd(title);
        view.displayDvd(dvd);
    }
    
    private void exitMessage() {
        view.exitMessage();
    }
    
    private void unknownCommand() {
        view.unknownCommand();
    }
}
