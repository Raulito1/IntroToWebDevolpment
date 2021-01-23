/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.guessme;

import java.util.Scanner;

/**
 *
 * @author raulalvarado
 * INSTRUCTIONS 
 * 1.) Write a program that has a picked int stored in a variable, and
 * asks the user to guess it.
 * 
 * 2.) Ask the user to pick a number
 * 3.) If their choice is equal to the number, print out: "Wow, nice guess! That
 * was It!"
 * 4.) If their choice is less than the target number, print out: "Ha, nice try -
 * too low! I chose #"
 * 5.) If their choice is greater than the target number, print out: "Too bad,
 * way too high. I choose #"
 *
 */
public class GuessMe {
    public static void main(String[] args) {
        
        Scanner userInput = new Scanner(System.in);
        
        int numToGuess = 6;
        int yourGuess;
        
        System.out.println("I've chosen a number. Betcha can't guess it!");
        yourGuess = userInput.nextInt();
        System.out.println("Your guess: " + yourGuess);
        
        if (yourGuess == numToGuess) {
            System.out.println("Wow, nice guess! That was it!");
        }
        
        if (yourGuess < numToGuess) {
            System.out.println("Ha, nice try - too low! I chose " + numToGuess);
        }
        
        if (yourGuess > numToGuess) {
            System.out.println("Too bad, way too high. I chose " + numToGuess);
        }
    
    }
}
