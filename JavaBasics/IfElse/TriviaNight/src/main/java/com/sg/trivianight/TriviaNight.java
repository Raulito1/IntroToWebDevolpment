/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.trivianight;

import static java.lang.System.exit;
import java.util.Scanner;

/**
 *
 * @author raulalvarado
 */
public class TriviaNight {
    public static void main(String[] args) {
        
        // creating my scanner to get user Input
        Scanner userInput = new Scanner(System.in);
        
        String yesOrNo;
        int answer1;
        int answer2;
        int answer3;
        int totalAnswers = 0;
        
        System.out.println("It's TRIVIA NIGHT! are you ready?!");
        yesOrNo = userInput.next();
        if (yesOrNo.equalsIgnoreCase("n")) {
            exit(0);
        }
        
    
        System.out.println("FIRST QUESTION!");
        System.out.println("What is the Lowest Level Programming Language?");
        System.out.print("1 Source code       ");
        System.out.print("2 Assembley Language");
        System.out.println("");
        System.out.print("3 C#                ");
        System.out.print("4 Machine code");
        System.out.println("");
        answer1 = userInput.nextInt();
        System.out.println("");
        System.out.println("YOUR ANSWER: " + answer1);
        System.out.println("");
        if (answer1 == 4) {
            totalAnswers++;
        } 
        
        
        System.out.println("SECOND QUESTION!");
        System.out.println("Website Security CAPTCHA Forms Are Decended From The WOrk of?");
        System.out.print("1 Grace Hopper       ");
        System.out.print("2 Alan Turing");
        System.out.println("");
        System.out.print("3 Charles Babbage                ");
        System.out.print("4 Larry Page");
        System.out.println("");
        answer2 = userInput.nextInt();
        System.out.println("YOUR ANSWER: " + answer2);
        System.out.println("");
        if (answer2 == 2) {
            totalAnswers++;
        } 
        
        
        System.out.println("THIRD QUESTION!");
        System.out.println("Which of These Sci-Fi Ships Was Once Slated for a Full-Size Replica in Las Vegas?");
        System.out.print("1 Serenity       ");
        System.out.print("2 The Battlestar Galactica");
        System.out.println("");
        System.out.print("3 The USS Enterprise                ");
        System.out.print("4 The Millennium Falcon");
        System.out.println("");
        answer3 = userInput.nextInt();
        System.out.println("YOUR ANSWER: " + answer3);
        System.out.println("");
        if (answer3 == 3) {
            totalAnswers++;
        } 
        
        System.out.println("Total answers correct: " + totalAnswers);
        
        
    
    }
}
