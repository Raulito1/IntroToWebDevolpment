/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.methodtomadness;

/**
 *
 * @author raulalvarado
 */
public class MethodToMadness {
    // THis is the main method to run the program
    public static void main(String[] args) {
        // invoking the method form  outside the main method
        eatMe();
        
        System.out.println("\n - Lewis Carroll, Alice in Wonderland.");
        
    }
    // this is another method out of the main method called eatMe with no
    // parameters
    static void eatMe() {
        System.out.println(" 'But I don't want to go among mad peopl,' Alice, remarked");
        System.out.println(" 'Oh, you can't help that, ' said the Cat.");
        System.out.println(" 'We're all mad here. I'm mad. You're mad.'");
    }
    // a second method created out of the main method
    static void drinkMe() {
        System.out.println(" 'How do you know I'm mad?' said Alice.");
        System.out.println(" 'You must be,' said the Cat, 'or you wouldn't have come here.' ");
    }
}
