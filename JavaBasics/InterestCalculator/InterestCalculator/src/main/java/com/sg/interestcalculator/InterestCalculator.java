/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.interestcalculator;

import java.text.DecimalFormat;
import java.util.Scanner;

/**
 *
 * @author raulalvarado
 */
public class InterestCalculator {
    // INSTRUCTIONS
    // Write an Interest calculator program that works as described in the example:
    // John has $500 to invest. Sue knows of a mutual fund plan that pays 10%
    // interest annually, compounded quarterly. That is, every three months, the
    // principal is multiplied by 2.5%(the 10% nnual rate divided by 4 because it
    // is compounded 4 times per year) and the result is added to the principal
    // More generally, the new amount each quarter is equal to:
    // CurrentBalanvce *(1 + (QuartelyInterestRate / 100))
    
    // It should take in the following Inputs:
    // * Annual Interest Rate
    // * Initial amount of principal
    // * The number of years the money is ti stay in the fund
    
    // The output:
    // * The year number
    // * The principal at the beginning of the year
    // * The total amount of interest earnedd for the year
    // * The principal at the end of the year
    
    
    public static void main(String[] args) {
        
        // import scanner class to get userInput
        Scanner userInput = new Scanner(System.in);
        
        // created a String var named principleAmount to store the amount the user inputs
        // in for principle amount.
        // then use the ParseDouble class to change a string into a double amout
        System.out.println("What is the principle amount? ");
        String principleAmount = userInput.next();
        double p = Double.parseDouble(principleAmount);
        
        //will create 3 more like above for annual Interest, how many times a year is interest compounded
        // how many years to invest for
        System.out.println("What is the annual interest rate? ");
        String interestRate = userInput.next();
        double i = Double.parseDouble(interestRate);
        
        System.out.println("How many times a year is interest compounded? ");
        String interestCompounded = userInput.next();
        double ic = Double.parseDouble(interestCompounded);
        
        System.out.println("For how many years do you want to invest? ");
        String yearsToInvest = userInput.next();
        double y = Double.parseDouble(yearsToInvest);
        
        double endOfYearAmnt = p * Math.pow(((1 + (i / ic))), ic);
        double endOfYearProfit = endOfYearAmnt - p;
        double endOfYearAmnt2 = p * Math.pow(((1 +(i / ic))), (ic * y));
        double endOfYearProfit2 = endOfYearAmnt2 - p;
        
        DecimalFormat df = new DecimalFormat("0.00");
        String endOfYearAmntRound = df.format(endOfYearAmnt);
        String endOfYearProfitRound = df.format(endOfYearProfit);
        String endOfYearAmn2tRound = df.format(endOfYearAmnt2);
        String endOfYearProfit2Round = df.format(endOfYearProfit2);
        
        System.out.println("\nYou chose to invest $" + p + " at an interest rate of " + i
        + " (compounded " + ic + " times per year) for " + y + " years.");
        
        System.out.println("At the end of the first year, your new amount of money is $" + endOfYearAmntRound +
                ", which is a profit of $" + endOfYearProfitRound + ".") ;
        
        System.out.println("At the end of the last year, your new amount of money is $" + endOfYearAmnt2 +
                ", which is a profit of $" + endOfYearProfit2 + "."
                        + "");
    }

}
