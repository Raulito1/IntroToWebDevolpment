/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.differentkettleoffish;

/**
 *
 * @author raulalvarado
 */
public class DifferentKettleOfFish {
    public static void main(String[] args) {
        // INSTRUCTIONS
        // 1. take, the code, and refactor (or change) the while loop into a for loop!
        // 2. Make the output look the same as it was in the while loop! What changed?! (write in a comment)
        
        int fish = 1;
        // while (fish < 10) {
        // commented out the while loop and replaced it as a for loop
        for (int i = 0;i < 10; i++) {
            if (fish == 3) {
                System.out.println("RED fish!");
            } else if (fish == 4) {
                System.out.println("BLUE fish!");
            } else {
                System.out.println(fish + " fish!");
            }
            
            fish++;
        }    
    }
}
