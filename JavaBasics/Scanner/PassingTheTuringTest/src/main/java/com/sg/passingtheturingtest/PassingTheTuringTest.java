/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.passingtheturingtest;

import java.util.Scanner;

/**
 *
 * @author raulalvarado
 */
public class PassingTheTuringTest {
    public static void main(String[] args) {
        
        // INSTRUCTIONS!!
        //
        // Create a program that uses taken user input in a conversation. First ask the
        // user for their name. Then display that name, and tell them yours(or your
        // AI's name!) Ask them for their favorite colo. Then didpley that, but in a 
        // conversational way. Do the same thing with favorite foos and number, and
        // then say goodbye!
        
        Scanner inputReader = new Scanner(System.in);
        
        String yourName;
        String favColor;
        String favFruit;
        String favNum;
        
        
        
        System.out.println("Hello there!");
        System.out.println("What's your name? ");
        yourName = inputReader.next();
        System.out.println("Hi, " + yourName + "! " + " What's you color?");
        favColor = inputReader.next();
        System.out.println("Huh, " + favColor + "? Mine's Electric Lime.");
        System.out.println("I really like limes. They're my favorite fruit, too.");
        System.out.println("What's YOUR favorite fruit, " + yourName + "?");
        favFruit = inputReader.next();
        System.out.println("Really? " + favFruit + "?" + " That's wild!");
        System.out.println("Speaking of favorites, what's your favorite number?");
        favNum = inputReader.next();
        System.out.println(favNum + " is a cool number. Mine's -7.");
        System.out.println("Did you know 42 * -7 is -294? That's a cool number too!");
        System.out.println("Well, thanks for talking to me, goodbye " + yourName);
    }
}
