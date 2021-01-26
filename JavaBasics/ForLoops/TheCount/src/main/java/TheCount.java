
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author raulalvarado
 */
public class TheCount {
    public static void main(String[] args) {
        // Import Scanner to get the UserInput
        Scanner userInput = new Scanner(System.in);
        
        System.out.println("*** I LOVE TO COUNT!! LET ME SHARE MY COUNTING WITH YOU!! ***");
        System.out.println("Sart At: ");
        int startNum = userInput.nextInt();
        System.out.println("Stop At: ");
        int stopNum = userInput.nextInt();
        System.out.println("Count By: ");
        int countByNum = userInput.nextInt();
        
        int list = 0;
        
         for(int i = startNum; i < stopNum + 1; i = i + countByNum) {
             list++;
             System.out.println(i + " ");
             if (list % 3 == 0) {
                 System.out.println("Ah Ah Ah! I LOVE TO COUNT!!");
             }
         }
    }
}
