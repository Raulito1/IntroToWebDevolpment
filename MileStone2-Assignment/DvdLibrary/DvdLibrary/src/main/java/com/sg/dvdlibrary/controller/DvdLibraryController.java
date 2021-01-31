/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.controller;

import com.sg.dvdlibrary.dao.DvdLibraryDao;
import com.sg.dvdlibrary.dao.DvdLibraryDaoException;
import com.sg.dvdlibrary.dto.Dvd;
import com.sg.dvdlibrary.ui.DvdLibraryView;
import java.util.List;

/**
 *
 * @author raulalvarado
 */
public class DvdLibraryController {
    
    private DvdLibraryView view;
    private DvdLibraryDao dao;
    
    public DvdLibraryController(DvdLibraryDao dao, DvdLibraryView view) {
        this.dao = dao;
        this.view = view;
    }
    
    public void run() {
        boolean keepGoing = true;
        int menuSelection = 0;
        try {
            
            view.displayWelcomeBanner();
            System.out.println("");
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
                        editDvd();
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
        } catch (DvdLibraryDaoException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }
    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }
    
    private void createDvd() throws DvdLibraryDaoException {
        view.displayCreateDvdBanner();
        Dvd newDvd = view.getNewDvdInfo();
        dao.addDvd(newDvd.getTitle(), newDvd);
        view.displayCreateSuccessbanner();
    }
    
    private void listOfDvds() throws DvdLibraryDaoException {
        view.displayDvdListBanner();
        List<Dvd> dvdList = dao.getAllDvds();
        view.displayDvdList(dvdList);
    }
    
    private void editDvd() throws DvdLibraryDaoException {
        view.editDvdBanner();
        String dvdTitle = view.getDvdTitleChoice();
        Dvd firstDvd = dao.getDvd(dvdTitle);
        Dvd editedDvd = view.editDvd(firstDvd);
        dao.editDvd(editedDvd.getTitle(), editedDvd);
        view.editSuccessBanner();
    }
    
    private void removeDvd() throws DvdLibraryDaoException {
        view.displayRemoveDvdBanner();
        String dvdId = view.getDvdTitle();
        dao.deleteDvd(dvdId);
        view.displaySuccessRemoveDvdBanner();
    }
    
    public void viewDvd() throws DvdLibraryDaoException {
        view.displayDvdBanner();
        String title = view.getDvdTitleChoice();
        Dvd dvd = dao.getDvd(title);
        view.displayDvd(dvd);
    }
    
    private void exitMessage() {
        view.exitMessage();
    }
    
    private void unknownCommand() {
        view.unknownCommand();
    }
}
