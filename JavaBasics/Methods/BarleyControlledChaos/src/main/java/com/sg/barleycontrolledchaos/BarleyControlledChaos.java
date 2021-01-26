/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.barleycontrolledchaos;

import java.util.Random;

/**
 *
 * @author raulalvarado
 */
public class BarleyControlledChaos {
    public static void main(String[] args) {
        // INSTRUCTIONS
        
        // 1. write a method that returns  rondomly chosen color(have it choose
        // from at LEAST 5 different colors!)
        // 2. Write a method that returns a rondomly chosen animal(have it choose
        // from at LEAST 5 different animlas!)
        // 3. Write another method that returns a random integer chosen from a 
        // a range (min/max) that can be either of the two numbers or anything between
        // 4. When you're done defining & implementing these methods - plug them 
        // into the main method structure
        
        String color = diffColor();
        String animal = diffAnimal();
        String colorAgain = secondColor();
        int weight = randNum(5,200);
        int distance = randNum(10,20);
        int number = randNum(10000,20000);
        int time = randNum(2,6);
        
        System.out.println("Once, when I was very small...");
        
        System.out.println("I was chased by a " + color + ", " + weight + "lb " + " miniature " + animal + " for over " + distance + " miles!!");
        
        System.out.println("I had to hide in a field of over " 
        + number + " " + colorAgain + " poppies for nearly "
        + time + " hours until it left me alone!");
        
        System.out.println("\nIt was QUITE the experience,"
                + "let me tell you!");
    }
    
    public static String diffColor() {
       
        
        String[] firstColor = {"red","blue","white","pink","black"};
        int index = new Random().nextInt(firstColor.length);
        String random = (firstColor[index]);
        return random;

    }
    
    public static String secondColor() {
        // i have to return a String 
        // so to chose randomly out of at least five color choices(String)
        // i will create an array of type String[] first 
        String[] secondColor = {"red","blue","white","pink","black"};
        // next i will create an int var named index in my case
        // and use the nextInt class to generate a random int to choose randomly 
        // in my index out of my array and using the .length so itll use the le gth of ,y array to use
        // as my min an max 
        int index = new Random().nextInt(secondColor.length);
        // next i will store it in a String var named random2 to return it in String form
        String random2 = (secondColor[index]);
        // returning a String random2 which will print out random index out of my array named secondColor
        return random2;
    }
    
    public static String diffAnimal() {
       
       String[] animals = {"dog","cat","bird","snake","gecko"};
       int index = new Random().nextInt(animals.length);
       String randomAnimal = (animals[index]);
       return randomAnimal;
    }
    
    public static int randNum(int min, int max) {
        Random num = new Random();
        //Scanner userInput = new Scanner(System.in);
        
        return num.nextInt((max - min) + 1) + min;
       
    }

        
}
