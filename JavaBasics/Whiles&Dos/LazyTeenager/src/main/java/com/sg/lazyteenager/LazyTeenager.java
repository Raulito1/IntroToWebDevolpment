/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.lazyteenager;

import java.util.Random;

/**
 *
 * @author raulalvarado
 */
public class LazyTeenager {
    public static void main(String[] args) {
        
         // Write a program that simulates a lazy teenger who won't clean their room
        // until their paretn tells them repeatedly to do it.
        
        // You should write this in a do/while loop,which continues to execute until
        // the room is clean
        
        
        // created a in var named i to keep up with how many times it prints
        // out to console
        int i = 1;
        // then imported random class to generate a random int with bounds of 20
        Random random = new Random();
        int randNum = random.nextInt(20);
        
        do {
            System.out.println("Clean your room!!" + "(x" + i + ")");
            i ++;
        } while (i <= randNum);
         if (randNum < 15) {
             System.out.println("FINE I'LL CLEAN MY ROOM. BUT I REFUSE TO EAT MY PEAS. ");
         } else if (randNum >= 15) {
             System.out.println("Clean your room!! That's IT, I'm doing it!! YOU'RE GROUNDED AND I'M TAKING YOUR XBOX  ");
         }
   
    }
}
