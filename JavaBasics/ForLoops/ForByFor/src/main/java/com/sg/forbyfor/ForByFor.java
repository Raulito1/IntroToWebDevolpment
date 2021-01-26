/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.forbyfor;

/**
 *
 * @author raulalvarado
 */
public class ForByFor {
    public static void mian(String[] args) {
        // INSTRUCTIONS
        //
        // For Loops are great. But know what's GREATER? Loops WITHIN
        // LOOPS. That's right. NESTED for loops! With ASCII Art!
        
        // 1. Change the code so that the middle column is made of $, the middle row
        // is made of @ and the very center is made of #
        
        for (int i = 0; i < 3; i++) {
            System.out.print("|");
            
            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 3; col++) {
                    System.out.print("*" );
                }
                System.out.print("|");
            }
            System.out.println("");
        }
    }
}
