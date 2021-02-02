/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine;

import com.sg.vendingmachine.controller.VendingMachineController;
import com.sg.vendingmachine.dao.VendingMachineAuditDao;
import com.sg.vendingmachine.dao.VendingMachineAuditDaoFileImpl;
import com.sg.vendingmachine.dao.VendingMachineDao;
import com.sg.vendingmachine.dao.VendingMachineDaoFileImpl;
import com.sg.vendingmachine.service.VendingMachineServiceLayer;
import com.sg.vendingmachine.service.VendingMachineServiceLayerImpl;
import com.sg.vendingmachine.ui.UserIO;
import com.sg.vendingmachine.ui.UserIOConsoleImpl;
import com.sg.vendingmachine.ui.VendingMachineView;

/**
 *
 * @author raulalvarado
 */
public class App {
    public static void main(String[] args) {
    UserIO myIO = new UserIOConsoleImpl();
    VendingMachineView myView = new VendingMachineView(myIO);
    VendingMachineAuditDao myAuditDao = new VendingMachineAuditDaoFileImpl();
    VendingMachineDao myDao = new VendingMachineDaoFileImpl();
    VendingMachineServiceLayer myService = new VendingMachineServiceLayerImpl(myDao, myAuditDao);
    VendingMachineController controller = new VendingMachineController(myView, myService);
    controller.run();
    }
}
