/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.luckysevens;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author raulalvarado
 */
public class LuckySevens {
    public static void main(String[] args) {
        
    // Import Scanner class for userInput
        Scanner userInput = new Scanner(System.in);
        // Import Random class to generate random number 
        Random rand = new Random();
        
        int dice1, dice2;
        int bankStart = 0;
        //int balance;
        int maxBank = 0;
        int diceRolls = 0;
        int maxRolls = 0;
        
        System.out.println("How many dollars do you have? ");
        int balance = userInput.nextInt();
        
        while (balance > 1) {
            
            
            dice1 = rand.nextInt(6) + 1;
            dice2 = rand.nextInt(6) + 1;
            diceRolls++; 
            
            if (dice1 + dice2 == 7) {
                balance += 4;
            } else {
                balance --;
            }
            
            if (balance > maxBank) {
                maxBank = balance;
                maxRolls = diceRolls;
            }
            
            
        }
        System.out.printf("You are broke after %d rolls %n%n", diceRolls );
        System.out.printf("You should have quit afetr %d rolls when you had"
        + " $%d.00 %n%n", maxRolls, maxBank);
    }
}
