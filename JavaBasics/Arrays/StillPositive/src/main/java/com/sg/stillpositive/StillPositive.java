/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.stillpositive;

/**
 *
 * @author raulalvarado
 */
public class StillPositive {
    public static void main(String[] args) {
        
          int[] numbers = {389, -447, 26, -485, 712, -884, 94, -64, 868,
            -776, 227, -744, 422, -109, 259, -500, 278, -219, 789, -311};

        for (int n : getPositiveNum(numbers)) {
            System.out.print(" " + n);
        }
    }

    public static int[] getPositiveNum(int[] numbers) {

        int count = 0;
        for (int n : numbers) {
            if (n > 0) {
                count++;
            }
        }
        int[] nums = new int[count];
        int i = 0;
        for (int n : numbers) {
            if (n > 0) {
                nums[i] = n;
                i++;
            }
        }
        return nums;
    }

}
