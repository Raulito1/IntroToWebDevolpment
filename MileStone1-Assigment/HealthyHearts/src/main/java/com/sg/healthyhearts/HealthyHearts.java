/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.healthyhearts;

import java.util.Scanner;

/**
 *
 * @author raulalvarado
 */
public class HealthyHearts {
    //INSTRUCTIONS!!
    // Make a simple calculator that asks the user for their age. Then calculate the
    // healthy heart rate range for that age, and disply it.
    // Their maximum heart rate should be 220 - their age.
    // Thr target heart rate zone is the 50 - 85% of the maximum.
    
    public static void main(String[] args) {
        
        Scanner userInput = new Scanner(System.in);
        
        int userAge;
        int maxHR;
        int targetHR;
        double targetHR2;
        
        System.out.println("What is you age? ");
        userAge = userInput.nextInt();
        
        maxHR = 220 - userAge;
        targetHR = maxHR / 2;
        targetHR2 = maxHR * .85;
        
        System.out.println("Your maximum heart rate should be " + maxHR + " beats per minute");
        System.out.println("Your target HR Zone is " + targetHR + " - " + Math.round(targetHR2) + " beats per minute");
        
    }
}
