/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.rockpaperscissors;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author raulalvarado
 */
public class RockPaperScissors {
    // INSTRUCTIONS
    
    // 1. Each player chooses Rock,Paper,Scissors
    // 2. If both players choose the same thing, the round is a tie
    // 3. Otherwise:
    //  a.Paper wraps Rock to win
    //  b.Scissors cut Paper to win
    //  c. Rock breaks Scissors to win
    
    // REQUIREMNTS
    // It will be a Java console project
    // - the projwct first asks the user how many rounds he/she wants to play.
    // - Max of 10 Min of 1 if user ask of that range print error messageand quits
    // - If the number of rounds is in rangew the program plays that number of rounds
    // - For each round R,S,P the program does the following:
    // - the computer ask the user his/her choice 
    // - Aftr the computer ask the user for input, the computer randomly  chooses R,S,P and
    //   displaysthe result of the round (tie,user win, or comp win)
    // - The program must keep track of how many rounds are ties, user wins or comp wins
    // - After all rounds have been played the winner is declared , the program must ask the user if he/ she
    //   wants to play agian.
    //  - If no print out "thank you for playing" and exit 
    //  - if yes the program starts over asking how many rounds again
    public static void main(String[] args) {
        
      Scanner userInput = new Scanner(System.in);
      Random randNum = new Random();
      boolean userAnswer = askIfPlaying();
      
      while (userAnswer) {
          // vars initialized to keep up with rounds lost,won, or ties
          int roundsToPlay = howManyRounds();
          int win = 0;
          int lost = 0;
          int tie = 0;
          
          for (int i = 0; i < roundsToPlay; i++) {
              System.out.println("Paper, Scissor, Rock");
              String yourChoice = userInput.nextLine();
              int userPick = valueOfChoices(yourChoice);
              int compChoice = randNum.nextInt(3);
              
              switch (userPick) {
                    case 0:
                        if (compChoice == 0) {
                          System.out.println("Its a TIE!! I chose Paper");
                          tie++;
                        } else if (compChoice == 1) {
                          System.out.println("Ha, you Lost!!, I chose Scissor");
                          lost++;
                        } else {
                            System.out.println("Aw Schucks!, you WIN!! I chose Rock");
                            win++;
                        }
                        break;
                      
                    case 1:
                        if (compChoice == 1) {
                            System.out.println("Its a TIE!! I chose Scissor");
                            tie++;
                        } else if (compChoice == 1) {
                            System.out.println("Aw Schucks!, you WIN!! I chose Rock");
                            win++;
                        } else {
                            System.out.println("Ha, you Lost!!, I chose Paper");
                            lost++;
                        }
                        break;
                    
                    case 2:
                        if (compChoice == 2) {
                            System.out.println("Its a TIE!!, I chose Rock");
                            tie++;
                        } else if (compChoice == 1 ) {
                            System.out.println("Ha!, you Lost!! I chose Scissor");
                            lost++;
                        } else {
                            System.out.println("Aw Shcucks!, you WIN!! I chose Paper");
                            win++;
                        }
                        break;
                      
                      
                      

                      // and Most switch statements will have a default statement to run if 
                      // nothing runs in my cases
                  default:
                      System.out.println("Invalid Choice");
                      System.exit(1);
                      break;
              }
          }
          // will print out to the console my results of wins, lost, and tires
          System.out.println("Wins: " + win);
          System.out.println("Loss: " + lost);
          System.out.println("Ties: " + tie);
          
          // then will ask the user if he/she would like to play
          // if yes run the askIfPlaying method and if no exit program succes
          userAnswer = askIfPlaying();
      }
       
        
        
    }
   
    // create different method just to recap everything ive learned in previous 
    // assignments
    public static boolean askIfPlaying() {
        System.out.println("Would you like to play Paper, Scissor, Rock?(y/n) ");
        // import my Scanner class for userInput
        Scanner userInput = new Scanner(System.in);
        String userChoice = userInput.nextLine();
        return userChoice.equalsIgnoreCase("y");
        
    }
    // created a method to ask user how many rounds would he/she like to play
    // and it returns that amount 
    public static int howManyRounds() {
        Scanner userInput = new Scanner(System.in);
        System.out.println("A min 1 and a max of 10, how many rounds would you like to play? ");
        
        int roundsAmount = userInput.nextInt();
        if (roundsAmount < 0 || roundsAmount > 10) {
            System.out.println("Invalid Choice");
            System.exit(1);
        } 
        return roundsAmount;
    }
    // a method to give values to userInput
    public static int valueOfChoices(String choice) {
       // this method has a parameter of String named choice
       // so depepnding on what user inouts in it will respond with these choice
       if (choice.equalsIgnoreCase("paper")) {
           return 0;
       }
       
       if (choice.equalsIgnoreCase("scissor")) {
           return 1;
       }
       
       if (choice.equalsIgnoreCase("rock")) {
           return 2;
       } 
       return -1;
       
    }
    
    // a method to give values to computer choices 
    public static String compChoices(int x) {
        if (x == 0) {
            return "paper";
        } 
        if (x == 1) {
            return "scissor";
        }
        if (x == 2) {
            
            return "rock";
        }
        
        return "Invalid Choice";
    }
}
