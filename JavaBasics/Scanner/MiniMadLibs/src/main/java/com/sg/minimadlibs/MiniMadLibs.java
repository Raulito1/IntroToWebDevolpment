/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.minimadlibs;

import java.util.Scanner;

/**
 *
 * @author raulalvarado
 *  Make a program that lets you play mad libs!
 *  You'll have to ask the user for the following (unless you use a different mad
 *  lib!)
 *  1) noun 
 *  2) adjective
 *  3) noun
 *  4) number
 *  5) adjective
 *  6,7,8) 3 plural nouns
 *  9) verb present tense
 *  10) same verb but past tense
 * 
 */
public class MiniMadLibs {
    
    public static void main(String[] args) {
        
        Scanner userInput = new Scanner(System.in);
        
        String noun1;
        String adjective2;
        String noun3;
        int number4;
        String adjective5;
        String pluralNoun6;
        String pluralNoun7;
        String pluralNoun8;
        String presentVerb9;
        String pastTenseVerb10;
        
        System.out.println("I need a noun: ");
        noun1 = userInput.next();
        System.out.println("Now an adj: ");
        adjective2 = userInput.next();
        System.out.println("Another noun: ");
        noun3 = userInput.next();
        System.out.println("And a number: ");
        number4 = userInput.nextInt();
        System.out.println("Another adj: ");
        adjective5 = userInput.next();
        System.out.println("A plural noun: ");
        pluralNoun6 = userInput.next();
        System.out.println("Another one: ");
        pluralNoun7 = userInput.next();
        System.out.println("One more: ");
        pluralNoun8 = userInput.next();
        System.out.println("A verb (present tense)");
        presentVerb9 = userInput.next();
        System.out.println("Same verb (past tense)");
        pastTenseVerb10 = userInput.next();
        
        System.out.println("*** NOW LETS GET MAD (libs) ***");
        System.out.println( noun1 + ": the " + adjective2 + " frontier. These are thr voyages of the starship " + noun3 + ". Its" );
        System.out.println(number4 + " - year mission: to explore strange " + adjective5 + " " + pluralNoun6 + ", to seek out " + adjective5 + " " + pluralNoun7 + " and");
        System.out.println( adjective5 + " " + pluralNoun8 + ", to boldly " + presentVerb9 + " where no one has " + pastTenseVerb10 + " before.");
        
    
    }
}
