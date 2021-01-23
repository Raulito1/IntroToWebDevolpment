/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.fieldday;

import java.util.Scanner;

/**
 *
 * @author raulalvarado
 */
// INSTRUCTIONS
// Your company has organized a morale event! They're hosting a picnic
// and field day in the park, and of course, they want to play games! Team
// games! Team building games!

// To do that want assign all the people who show up to certai 
// teams based on their last name - they've figured out the distibution
// break down - all they need YOU to do is to write the program that can 
// sort! (ie. take a last name as input, outoput the team name!)

// Here are the specs:
//If a person's name falls before Baggins, then they are on the team "Red Dragons"
//If it falls after Baggins, but before Dresden, they are on the team "Dark Wizards"
//If it falls after Dresden, but before Howl, they are on the team "Moving Castles"
//If it falls after Howl, but before Potter, they are on the team "Golden Snitches"
//If it falls after Potter, but before Vimes, they are on the team "Night Guards"
//If it falls after Vimes, they are on the team “Black Holes”

public class FieldDay {
    public static void main(String[] args) {
        
         // create my Scanner for userInput
        Scanner userInput = new Scanner(System.in);
        
        System.out.println("What's your last name? ");
        String lastName = userInput.next();
        
        if (lastName.startsWith("a")) {
            System.out.println("Aha! You're on the team Red Dragons");
        }
        
        if (lastName.startsWith("b")|| (lastName.startsWith("c"))) {
            System.out.println("Aha! You're on the team Dark Wizarss");
        }
        
        if((lastName.startsWith("d")) || (lastName.startsWith("e") || (lastName.startsWith("f")))) {
            System.out.println("Aha! You're on the team Moving Castles");
        }
        
        if ((lastName.startsWith("g"))) {
            System.out.println("Aha! You're on the team Moving Castles");
        }
        
        if ((lastName.startsWith("h")) || (lastName.startsWith("i") || (lastName.startsWith("j")))) {
            System.out.println("Aha! You're on the Golden Snitches");
        }
        
        if ((lastName.startsWith("k")) || (lastName.startsWith("l") || (lastName.startsWith("m")))) {
            System.out.println("Aha! You're on the team Golden Snitches");
        }
        
        if ((lastName.startsWith("n")) || (lastName.startsWith("o"))) {
            System.out.println("Aha! You're on the team Golden Snitches");
        }
        
        if ((lastName.startsWith("p")) || (lastName.startsWith("q") || (lastName.startsWith("r")))) {
            System.out.println("Aha! You're on the team night Guards");
        }
        
        if ((lastName.startsWith("s")) || (lastName.startsWith("t") || (lastName.startsWith("u")))) {
            System.out.println("Aha! You're on the team Night Guards");
        }
        
        if ((lastName.startsWith("v")) || (lastName.startsWith("w"))) {
            System.out.println("Aha! You're on the team Black Holes!");
        }
        
        if ((lastName.startsWith("x")) || (lastName.startsWith("y") || (lastName.startsWith("z")))) {
            System.out.println("Aha! You're on the team Black Holes!");
        }
        
        System.out.println("Good luck in the games!");
    }
}
