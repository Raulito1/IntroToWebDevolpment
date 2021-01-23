/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.guessmefinally;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author raulalvarado
 */
public class GuessMeFinally {
   public static void main(String[] args) {
        // INSTRUCTIONS 
        // Make the number chosen, a random number between -100 and 100
        // If they user gets it wrong, give them another chance at guessing
        // 
        //
        // adding to a previous assignment a while loop as part 
        // of the instructions
        // import Random() class
        Random randomNum = new Random();
        //import Scanner class for userInput
        Scanner userInput = new Scanner(System.in);
         int num = randomNum.nextInt(100 - -100) + -100;
        System.out.println("Ive chosen a number between -100 and 100. Betcha can't guess it!");
        int userGuess1 = userInput.nextInt();
        if (userGuess1 == num) {
            System.out.println("WOW, nice guess! That was it!");
        }
       
        while (userGuess1 != num) {
                 
            if (userGuess1 > num) {
                System.out.println("Ha, nice try - too high! Try again!");
                 userGuess1 = userInput.nextInt();
            }
             if (userGuess1 < num) {
                 System.out.println("Ha, nice try - too low! Try again!");
                 userGuess1 = userInput.nextInt();
             }
            
        
        }
        
        System.out.println("Finally! It's about time you got it!");
    }
}
