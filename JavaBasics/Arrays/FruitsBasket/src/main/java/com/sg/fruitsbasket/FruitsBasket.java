/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.fruitsbasket;

import java.util.ArrayList;

/**
 *
 * @author raulalvarado
 */
public class FruitsBasket {
    public static void main(String[] args) {
        // INSTRUCTIONS
        // 
        //Iterate over the below array and determine how many oranges and how
        // many apples it contains, and print it out.
        
        // Then make 2 new arrays, one for each type, and sort the fruit into those
        // new arrays. (I.e. move the String value from the first array into the
        // appropriate sorting array.)
        
        // Finally, once they are sorted, print out how many fruit you have total, and then how many oranges are in
        // orange array, and hoe many apples are in the apple array.
        
        
        String[] fruit = {"Orange", "Apple", "Orange", "Apple", "Orange", "Apple", 
            "Orange", "Apple", "Orange", "Orange", "Orange", "Apple", "Orange", "Orange",
            "Apple", "Orange", "Orange", "Apple", "Apple", "Orange", "Apple", "Apple", 
            "Orange", "Orange", "Apple", "Apple", "Apple", "Apple", "Orange", "Orange",
            "Apple", "Apple", "Orange", "Orange", "Orange", "Orange", "Apple", "Apple", 
            "Apple", "Apple", "Orange", "Orange", "Apple", "Orange", "Orange", "Apple", 
            "Orange", "Orange", "Apple", "Apple", "Orange", "Orange", "Apple", "Orange", 
            "Apple", "Orange", "Apple", "Orange", "Apple", "Orange", "Orange"};
        
        int numOranges = 0;
        int numApples = 0;
        
        
        ArrayList<String> Oranges = new ArrayList<String>();
        ArrayList<String> Apples = new ArrayList<String>();
     
        
        
        for (int i = 0; i < fruit.length; i++) { 
            
           if (fruit[i].equals("Orange")) {
               numOranges++;
               Oranges.add(fruit[i]);
           } else {
                numApples++;
                Apples.add(fruit[i]);
            }
           
           
           
        }
        
        System.out.println("Total# of Fruit in the Basket: " + fruit.length);
        System.out.println("Number of Oranges: " + numOranges);
        System.out.println("Number of Apples: " + numApples);
    
    }
}
