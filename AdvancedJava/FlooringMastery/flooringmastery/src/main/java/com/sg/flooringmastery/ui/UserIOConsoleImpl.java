/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.ui;

import java.math.BigDecimal;
import static java.math.BigDecimal.ROUND_UP;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 *
 * @author raulalvarado
 */
public class UserIOConsoleImpl implements UserIO {
    
    Scanner scan = new Scanner(System.in);

    @Override
    public void print(String message) {
        System.out.println(message);
    }

    @Override
    public double readDouble(String prompt) {
        System.out.println(prompt);
       return scan.nextDouble();
    }

    @Override
    public double readDouble(String prompt, double min, double max) {
       double newDoub = readDouble(prompt);
        if(newDoub < min && newDoub > max) {
            newDoub = readDouble(prompt);
        } return newDoub;
    }

    @Override
    public float readFloat(String prompt) {
        System.out.println(prompt);
        return scan.nextFloat();
    }

    @Override
    public float readFloat(String prompt, float min, float max) {
        float newFloat = readFloat(prompt);
        if (newFloat < min && newFloat > max) {
            newFloat = readFloat(prompt);
        } return newFloat;
    }

    @Override
    public int readInt(String prompt) {
        System.out.println(prompt);
        int newInt = scan.nextInt();
        scan.nextLine();
        return newInt;
    }

    @Override
    public int readInt(String prompt, int min, int max) {
        int newInt = readInt(prompt);
        if (newInt < min && newInt > max) {
            newInt = readInt(prompt);
        } return newInt;
    }

    @Override
    public long readLong(String prompt) {
        System.out.println(prompt);
        return scan.nextLong();
    }

    @Override
    public long readLong(String prompt, long min, long max) {
        long newLong = readLong(prompt);
        if (newLong < min && newLong > max) {
            newLong = readLong(prompt);
        } return newLong;
    }

    @Override
    public String readString(String prompt) {
        System.out.println(prompt);
        return scan.nextLine();
    }

    @Override
    public String readMoney(BigDecimal amount) {
        return NumberFormat.getCurrencyInstance().format(amount);
    }

    @Override
    public LocalDate readDate(String prompt) {
        boolean valid = false;
        LocalDate date = null;
        do {
            String value = null;
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
                value = readString(prompt);
                date = LocalDate.parse(value, formatter);
                valid = true;
            } catch (DateTimeParseException ex) {
                System.out.printf("The date entered '%s' is not a valid date. (MM/DD/YYYY)\n", value); 
            }
        } while (!valid);
        return date;
    }

    @Override
    public LocalDate readDate(String prompt, LocalDate min, LocalDate max) {
        boolean valid = false;
        LocalDate date = null;
        do {
            date = readDate(prompt);
            if (date.isAfter(min) && date.isBefore(max)) {
                valid = true;
            } else {
                System.out.printf("Choose a date within bounds. (%s to %s)\n", min, max);
            }
        } while (!valid);
        return date;
    } 

    @Override
    public String readString(String prompt, int max) {
        boolean valid = false;
        String userInput = "";
        do {
            userInput = readString(prompt);
            if (userInput.length() <= max) {
                valid = true;
            } else {
                System.out.printf("The entry must be %s letters or less.\n", max);
            }
        } while (!valid);
        return userInput;
    }

    @Override
    public BigDecimal readBigDecimal(String prompt, int scale) {
        boolean valid = false;
        BigDecimal newNum = null;
        do {
            String value = null;
            try {
                value = readString(prompt);
                newNum = new BigDecimal(value).setScale(scale, ROUND_UP);
                valid = true;
            } catch (NumberFormatException ex) {
                System.out.printf("Your input '%s' is not a number.\n", value);
            }
        } while (!valid);
        return newNum;
    }

    @Override
    public BigDecimal readBigDecimal(String prompt, int scale, BigDecimal min) {
        boolean valid = false;
        BigDecimal newNum = null;
        do {
            newNum = readBigDecimal(prompt, scale);
            if (newNum.compareTo(min) >= 0) {
                valid = true;
            } else {
                String minNumString = String.valueOf(min);
                System.out.printf("The num must be greater than %s.\n", minNumString);
            }
        } while (!valid);
        return newNum;
    }

  
     
}
