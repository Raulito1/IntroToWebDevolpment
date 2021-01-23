/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.lovesme;

/**
 *
 * @author raulalvarado
 */
public class LovesMe {
    public static void main(String[] args) {
        
         boolean lovesMe;
        
        do {
            lovesMe = true;
           for (int i = 0; i < 17; i++ ) {
               System.out.println("It LOVES me NOT!");
               System.out.println("It LOVES me!");
           
           }
           break;
        } while (lovesMe);
        System.out.println("I knew it! IT LOVES ME!");
    }
   
    
}
