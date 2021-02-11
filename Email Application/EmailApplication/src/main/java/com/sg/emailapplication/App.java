/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.emailapplication;

import com.sg.emailapplication.dto.Email;

/**
 *
 * @author raulalvarado
 */
public class App {
    public static void main(String[] args) {
        // passing in the constructor
        Email email = new Email("Jon", "Doe");
        System.out.println(email.showInfo());
        
    }
}
