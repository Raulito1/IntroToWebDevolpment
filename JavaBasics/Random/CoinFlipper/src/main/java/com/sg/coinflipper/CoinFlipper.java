/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.coinflipper;

import java.util.Random;

/**
 *
 * @author raulalvarado
 */
public class CoinFlipper {
    public static void main(String[] args) {
         // INSTRUYCTIONS 
        // Write a program that simulates a random coin toss.
        // Do not use .nextInt() class
        
        
        
        
        // import Random class
        Random randomizer = new Random();
        
        boolean headsOrTails = randomizer.nextBoolean();
        
        System.out.println("HEADS OR TAILS?");
        System.out.println("Ready, Set, Flip....!!");
        
        if (headsOrTails == true) {
            System.out.println("You flipped HEADS!");
        } else {
            System.out.println("You flipped TAILS!");
        }
    
    }
}
