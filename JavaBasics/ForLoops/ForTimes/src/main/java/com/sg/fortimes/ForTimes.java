/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.fortimes;

import java.util.Scanner;

/**
 *
 * @author raulalvarado
 */
public class ForTimes {
    public static void main(String[] args) {
        // Import Scanner class to get user Input
        Scanner userInput = new Scanner(System.in);
        
        System.out.println("Which times table shall I recite? ");
        int userNum = userInput.nextInt();
        
        // used the blank System.out.println(); for spacing
        System.out.println("");
        for (int i = 1; i <= 15; ++i) {
            // usinf print f which uses the formatter clas
            // and the %d is for decimal format 
            System.out.printf("%d * %d = %d \n", userNum, i, userNum * i);
        }
        
    }
}
