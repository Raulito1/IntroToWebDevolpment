/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.factorizer;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author raulalvarado
 */
public class Factorizer {
    public static void main(String[] args) {
        // INSTRUCTIONS
        
        // *Your program must be a Java Console Application called Factorizer
        // * This program must ask the user the number for which he/she wants to factot.
        // * The program must print put the original number
        // * The program must print out the total number of factors for the number
        // * The program must print out each factor of the number(not including the number itself)
        // * The program must print out whether or not the number is perfect 
        // * The program must print out whether or not the number is prime.
        
        // import Scanner class to get userInput
        Scanner userInput = new Scanner(System.in);
        // ask the user for number to factor and store it an int var calle userNum
        System.out.println("What number would you like to factor? ");
        int userNum = userInput.nextInt();
        // then i create an ArrayList to store the factors of the number input
        // named the arrayList factorList
        ArrayList<Integer> factorList = new ArrayList<Integer>();
        // created and int var sumOfFactors and initialized to keep up with how many factors there ar in the number inouted
        int sumOfFactors = 0;
        // then i used a forLoop to iterate and add to my Arraylist the factors of the userNum
        for (int i = 1; i < userNum; i++) {
            if (userNum % i == 0) {
                factorList.add(i);
            }
        }
        // here i print out to the console the factors of the number that userInput
        // print the numbers that are in my ArrayList called factorList
        System.out.println("The factors of " + userNum + " are: ");
        System.out.println(factorList);
        // created another forLoop to iterate the size of my arrayList
        for (int i = 0; i < factorList.size(); i++ ) {
           // int sumOfFactors = 0; and then add the numbers of my arrayList 
           // to get total number of factors of a num that is inputed
            sumOfFactors = sumOfFactors + factorList.get(i);
        }
        // i created an if statement to determine if the userNum is prime or perfect 
        if (sumOfFactors == userNum) {
            System.out.println(userNum +" is a perfect number");
        } else {
            System.out.println(userNum +" is not a perfect number.");
        }
        
        if (factorList.size() == 1) {
            System.out.println(userNum + " is a prime number");
        } else {
            System.out.println(userNum + " is not a prime number");
        }
    }
}
