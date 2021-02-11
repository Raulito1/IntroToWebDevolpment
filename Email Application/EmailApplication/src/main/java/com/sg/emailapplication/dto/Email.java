/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.emailapplication.dto;

import java.util.Scanner;

/**
 *
 * @author raulalvarado
 */
public class Email {
    
    private String firstName;
    private String lastName;
    private String password;
    private String department;
    private String email;
    private int mailboxCapacity = 500;
    private int defaultPasswordLength = 10;
    private String alternateEmail;
    private String companySuffix = ".company.com";
    
    // Construstor to recieve the first and last name
    public Email(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        
        // Call a method asking for the department - return the department
        this.department = setDepartment();
        
        // Call a method that returns a random password
        this.password = randomPassword(defaultPasswordLength);
        System.out.println("Your password is" + this.password);
        
        // Combine elemntes to generate emial
        email = firstName.toLowerCase() + "." + lastName.toLowerCase() + "@" + department + companySuffix;
    }
    
    // Ask for the department
    private String setDepartment() {
        System.out.print("New Worker " + firstName + " " + lastName +"\n" + "DEPARTMENT CODES\n" + "1. sales\n" + "2. development\n" + "3. accounting\n" + "4. none\n" + "Enter a department code:");
        // println to make space not show on console
        System.out.println("");
        Scanner in = new Scanner(System.in);
        int deptChoice = in.nextInt();
        if (deptChoice == 1) {return "Sales"; }
        else if (deptChoice == 2) {return "Development";}
        else if (deptChoice == 3) {return "Accounting";}
        else { return "";}
        
    }
    
    // Generate a random password
    private String randomPassword(int length) {
        String passwordSet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890!@#$%&*";
        char[] password = new char[length];
        for (int i = 0; i < length;i++) {
          int rand = (int) (Math.random()*passwordSet.length());
            password[i] = passwordSet.charAt(rand);
        }
        return new String(password);
    }
    //===== SETTERS =========
    // Set the mailbox capacity
    public void setMailboxCapacity(int capacity) {
        this.mailboxCapacity = capacity;
    }
    
    // Set the alternate Email
    public void setAlternateEmail(String altEmail) {
        this.alternateEmail = altEmail;
    }
    // Change the password
    public void ChangePassword(String password) {
        this.password = password;
    }
    //====== GETTERS =========
    public int getMailboxCapacity() {
        return mailboxCapacity;
    }
    
    public String getAlternateEmail() {
        return alternateEmail;
    }
    
    public String getPassword() {
        return password;
    }
    
    public String showInfo(){
        return "DISPLAY NAME: " + firstName + " " + lastName +
                "\nCOMPANY EMAIL: " + email +
                "\nEMAIL CAPACITY: " + mailboxCapacity + "mb";
    }
}
