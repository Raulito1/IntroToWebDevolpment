/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.controller;

import com.sg.vendingmachine.dao.PersistenceException;
import com.sg.vendingmachine.model.Item;
import com.sg.vendingmachine.service.InsufficientFundsException;
import com.sg.vendingmachine.service.NoItemInventoryException;
import com.sg.vendingmachine.service.VendingMachineServiceLayer;
import java.math.BigDecimal;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author raulalvarado
 */
@Controller
public class VendingController {
    
    private VendingMachineServiceLayer  service;
    
    @Inject
    public VendingController(VendingMachineServiceLayer service) {
        this.service = service;
    }
    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String root(Model model) {
        List<Item> items = null;

        try {
            items = service.getAllItems();
        } catch (PersistenceException e) {
            service.setMessage(e.getMessage());
            return "redirect:/";
        }

        BigDecimal moneyInserted = service.getCurrentMoney();
        Integer selection = service.getSelection();
        String message = service.getMessage();
        String changeMessage = service.getMessageForChange();

        model.addAttribute("items", items);
        model.addAttribute("money", moneyInserted);
        model.addAttribute("selection", selection);
        model.addAttribute("message", message);
        model.addAttribute("change", changeMessage);
        return "index";
    }

    @RequestMapping(value = "/addMoney", method = RequestMethod.POST)
    public String addMoney(HttpServletRequest request) {
        String btnValue = request.getParameter("money");

        if (btnValue.equals("Add Dollar")) {
            service.setCurrentMoney(new BigDecimal("1.00"));
        }
        if (btnValue.equals("Add Quarter")) {
            service.setCurrentMoney(new BigDecimal(".25"));
        }
        if (btnValue.equals("Add Dime")) {
            service.setCurrentMoney(new BigDecimal(".10"));
        }
        if (btnValue.equals("Add Nickel")) {
            service.setCurrentMoney(new BigDecimal(".05"));
        }

        return "redirect:/";
    }

    @RequestMapping(value = "/selectItem", method = RequestMethod.GET)
    public String selectItem(HttpServletRequest request) {
        Integer selectItem = Integer.parseInt(request.getParameter("id"));
        service.setSelection(selectItem);
        return "redirect:/";
    }

    @RequestMapping(value = "/makePurchase", method = RequestMethod.GET)
    public String makePurchase(HttpServletRequest request) {
        Integer selectItem = Integer.parseInt(request.getParameter("id"));
        try {
            service.purchaseItem(selectItem);
        } catch (InsufficientFundsException | PersistenceException | NoItemInventoryException e) {
            service.setMessage(e.getMessage());
            return "redirect:/";
        }
        return "redirect:/";
    }

    @RequestMapping(value = "/returnChange", method = RequestMethod.GET)
    public String returnChange(HttpServletRequest request) {
        try {
            service.cancelAndReturnChange();
        } catch (InsufficientFundsException | PersistenceException e) {
            service.setMessage(e.getMessage());
            return "redirect:/";
        }
        return "redirect:/";
    }
    
}
