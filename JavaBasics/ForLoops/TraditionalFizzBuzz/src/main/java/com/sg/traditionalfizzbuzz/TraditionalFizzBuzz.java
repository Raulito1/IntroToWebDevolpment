/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.traditionalfizzbuzz;

import java.util.Scanner;

/**
 *
 * @author raulalvarado
 */
public class TraditionalFizzBuzz {
    public static void main(String[] args) {
        // INSTRUCTIONS
        // Ask the user for a number
        //
        // Counting from sero (using a for loop!) replace every multiple of 3 wiht "fizz"
        // and every multiple of % with "buzz". Multiples of BOTH should print out "fizz buzz"
        // Every time you print out fizz, buzz, or fizzbuzz - keep track. when you've
        // reached the number that as prompted from the user - stop! (You
        // might consider it ... taking a break, so to speak)
        
        // import Scanner class to get userInput for a num to use fizzbizz app
            Scanner userInput = new Scanner(System.in);
            
            System.out.println("How much units fizzing and buzzing do you need in your life? ");
            // then we store it in a int var name it whatever you want
            int userNum = userInput.nextInt();
            
            for (int i = 1; i <= 15; i++) {
                
                if (((i % 3) == 0) && (i % 5) == 0) {
                    System.out.println("fizzbuzz");
                }
                else if ((i % 3) == 0) {
                    System.out.println("fizz");
                }
                else if ((i % 5) == 0) {
                    System.out.println("buzz");
                }
                else {
                    System.out.println(i);
                }
            }
    }
}
