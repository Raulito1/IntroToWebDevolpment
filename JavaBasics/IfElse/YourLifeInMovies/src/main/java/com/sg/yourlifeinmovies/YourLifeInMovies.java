/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.yourlifeinmovies;

import java.util.Scanner;

/**
 *
 * @author raulalvarado
 * Filename: YOurLifeInMovies
 * * Write a program that displays a different message based on the year
 *   someone was born to make them feel old.
 *  * If they were born before 2005, print out that Pixar's 'UP' came out
 *    half a decade ago
 *  * If they were born before 1995, print out that the first Harry Potter
 *    came out over 15 years ago.
 *  * If they were born before 1985, print out Space Jam came out not last
 *    decade, but the one before THAT.
 *  * If they were born before 1975, print out that the original Jurassic Park
 *    release is closer to the date of the first lunar landing than it is to
 *    today
 *  * If they were born before 1965, print out that the MASH TV series has
 *    been around for almost half a century!
 * * Note: A person who is born in 1980 should display three messages, one
 *   for being born before 2005, one for 1995, and finally for 1985.
 */
public class YourLifeInMovies {
    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        
        String userName;
        int userBday;
        
        System.out.println("Hey, let's paly a game! What's your name? "); 
        userName = userInput.next();
        
        System.out.println("Ok, " + userName + ", whenn were you born? ");
        userBday = userInput.nextInt();
        
        System.out.println("Well " + userName + "...");
        
        if (userBday < 2005) {
            System.out.println("Pixar's 'UP' came out half a decade ago.");
        }
        
        if (userBday < 1995) {
            System.out.println("the first Harry Potter came out over 15 years ago");
        }
        
        if (userBday < 1985) {
            System.out.println("SpaceJam came out not last decade, but the one before THAT.");
        }
        
        if (userBday < 1975) {
            System.out.println("the original Jurassic Park release is closer to the date of the first lunar landing than i is to today.");
        }
        
        if (userBday < 1965) {
            System.out.println("the MASH TV series has been around for almost half a century!");
        }
        
    }
}
