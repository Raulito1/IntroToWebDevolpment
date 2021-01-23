/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.birthstones;

import java.util.Scanner;

/**
 *
 * @author raulalvarado
 */
public class BirthStones {
    public static void main(String[] args) {
        
        Scanner userInput = new Scanner(System.in);
       
        int userStone;
        
        System.out.println("What month's birthstone are you wanting to know? ");
        userStone = userInput.nextInt();
        
         if (userStone == 1) {
            System.out.println("January's birthstone is Garnet");
        }
        
         if (userStone == 2) {
             System.out.println("February's birthstone is Amethyst");
        }
         
         if (userStone == 3) {
             System.out.println("March's birthstone is Aquamarine");
        }
         
         if (userStone == 4) {
             System.out.println("April's birthstone is Diamond");
        } 
        
         if (userStone == 5) {
             System.out.println("May's birthstone is Emerald");
        }
        
         if (userStone == 6) {
             System.out.println("June's birthstone is Pearl");
        } 
         
         if (userStone == 7) {
             System.out.println("July's birthstone is Ruby");
        }
         
         if (userStone == 8) {
             System.out.println("August's birthstone is Peridot");
        }
         
         if (userStone == 9) {
             System.out.println("September's birthstone is Saphire");
        }
         
         if (userStone == 10) {
             System.out.println("October's birthstone is Opal");
        }
         
         if (userStone == 11) {
             System.out.println("November's birthstone is Topaz");
        }
         
         if (userStone == 12) {
             System.out.println("December's birthstone is Turquoise");
        } else if ( userStone <= 0 || userStone > 12) {
             System.out.println("Ithink you must be confused, " + userStone + " doesnt match a month.");
        }
    }
}
