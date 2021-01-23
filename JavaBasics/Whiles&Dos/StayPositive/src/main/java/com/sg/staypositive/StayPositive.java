/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.staypositive;

import java.util.Scanner;

/**
 *
 * @author raulalvarado
 */
public class StayPositive {
    public static void main(String[] args) {
        
        // import Scanner class for userInput
        Scanner userInput = new Scanner(System.in);
        
        System.out.println("What number should i count down from? ");
        int userNum = userInput.nextInt();
        
        System.out.println("Here goes!");
        
        while (userNum > 0) {
            System.out.println(userNum);
            userNum --;
        }
    }
}
