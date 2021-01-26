/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.fortimesfor;

import java.util.Scanner;

/**
 *
 * @author raulalvarado
 * INSTRUCTIONS
 *  Refactor your times table program from the previous exercise
 *  instead of just "reciting" a times table, the program prints out each "times"
 *  as a question, and waits for an answer. If the user answers correctly - they
 *  get a point! If not ... the get corrected.
 * 
 *  Print out the total number of points at the end.
 * 
 *  Bonus: print out a message that they should study more if they get less 
 *  than 50% correct, and give them a congratulatory message if they got over
 *  90 % correct!
 */
public class ForTimesFor {
    public static void main(String[] args) {
     // Import Scanner class to get user Input
        Scanner userInput = new Scanner(System.in);
        
        System.out.println("Which times table shall I recite? ");
        int userNum = userInput.nextInt();
        
        
        
        // created variable to keep count of how many answers you got correct
        // also created a boolean var and intialized it to false;
        // for a do/while loop reason I chose to do a do/while loop
        // becasue a do/while loop is to run at least once before it checks condition
        int tallyPoints = 0;
        boolean askAgain = false;
        
        // here i start my do/while loop 
        do {
        for (int i = 1; i <= 15; ++i) {
            // usinf print f which uses the formatter clas
            // and the %d is for decimal format 
            
            System.out.printf("%d * %d = is: \n", userNum, i, userNum * 1);
            int userAnswer = userInput.nextInt();
            
            
            // here i created my if statement for ech condition of the userInput
            // if correct print this out 
            if  (userAnswer == (userNum * i)) {
                System.out.println("Correct!");
                tallyPoints ++;
            } 
            // else if incorrect print this out
            if (userAnswer != (userNum * i)) {
                System.out.println("Sorry no, the answer is: " + (userNum * i));
            }

        } 
           // here the do while loop ends so itll check my condition in the while block
        } while (askAgain );
        System.out.println("You got " + tallyPoints + " correct");
       // then i print out how many questions you got correct by using concatenation
       
       if  (tallyPoints <= 7) {
           System.out.println("You should study more");
       } 
       
       if (tallyPoints >= 13) {
           System.out.println("Way to go!! good job");
       }
  }      
}
