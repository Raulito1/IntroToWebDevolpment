/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.simplesort;

import java.util.Arrays;

/**
 *
 * @author raulalvarado
 */
public class SimpleSort {
    public static void main(String[] args) {
        int[] firstHalf = {3, 7, 9, 10, 16, 19, 20, 34, 55, 67, 88, 99};
        int[] secondHalf = {1, 4, 8, 11, 15, 18, 21, 44, 54, 79, 89, 100};
        
        //int[] wholeNumbers = new int[24];
        // initialized int n and m with the length of each array
        int n = firstHalf.length;
        int m = secondHalf.length;
        // then initialized int megedArray by adding the length of first array and seconf array
        int mergedArray[] = new int [n + m];
        // then i use my sortedMerge Method to sort the new Array
        sortedMerge(firstHalf,secondHalf,mergedArray,n,m);
        // use a for loop to iterate through my new Array and printout to the console
        System.out.println("Here ya go - all nice and ordered:");
        for (int i = 0; i < n + m; i++) {
            System.out.print(" " + mergedArray[i]);
        }
    }
    
    public static void sortedMerge(int firstHalf[], int secondHalf[], int mergedArray[], int n, int m) {
        // Initialize traversing indices for all 3 arrays to 0
        // i for ArrayA
        // j for ArrayB
        // k for mergedArray
        int i = 0, j = 0, k = 0;
        // adding all elements of ArrayA to mergedArray
        // while i is less than n whihch n is firstHalf.length
        
        while (i < n) {
            mergedArray[k] = firstHalf[i];
            i++;
            k++;
        }
        // adding all elements of arrayB which is secondHalf
        
         while (j < m) {
             mergedArray[k] = secondHalf[j];
             j++;
             k++;
         }
         // Now, sort the mergedArray using Arrays.sort() method.
         // Return mergedArray
         Arrays.sort(mergedArray);
    }
}
