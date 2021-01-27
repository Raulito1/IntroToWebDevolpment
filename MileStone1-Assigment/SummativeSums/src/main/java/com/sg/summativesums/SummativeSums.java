/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.summativesums;

/**
 *
 * @author raulalvarado
 */
public class SummativeSums {
    public static void main(String[] args) {
        int[] array1 = {1, 90, -33, -55, 67, -16, 28, -55, 15};
        int[] array2 = {999, -60, -77, 14, 160, 301};
        int[] array3 = {10, 20, 30, 40, 50, 60, 70, 80, 90, 100, 110, 120, 130,
            140, 150, 160, 170, 180, 190, 200, -99};        
        // calling my method from below 
        int result1 = addArrayElements(array1);
        int result2 = addArrayElements(array2);
        int result3 = addArrayElements(array3);
        
        // printing out the reuslts to the console
        System.out.println("#1 Array sum: " + result1);
        System.out.println("#2 Array sum: " + result2);
        System.out.println("#3 Array sum: " + result3);
    }
    // creating a seperate method to add the sum up for my array of ints
    private static int addArrayElements(int array[]) {
        int result = 0;
        
        for (int i = 0; i < array.length; i++) {
            result += array[i];
        }
        return result;
    }
}
