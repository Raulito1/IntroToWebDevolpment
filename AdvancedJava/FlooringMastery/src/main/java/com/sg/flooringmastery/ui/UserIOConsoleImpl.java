/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.ui;

import java.math.BigDecimal;
import static java.math.BigDecimal.ROUND_DOWN;
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
    
    Scanner scanner = new Scanner(System.in);

    @Override
    public void print(String message) {
        System.out.println(message);
    }

    @Override
    public double readDouble(String prompt) {
        print(prompt);
        return Double.parseDouble(scanner.nextLine());
    }

    @Override
    public double readDouble(String prompt, double min, double max) {
        double newDoub;
        do {
            print(prompt);
            newDoub = Double.parseDouble(scanner.nextLine());
        }
        while (newDoub < min || newDoub > max);
        return newDoub;
    }

    @Override
    public float readFloat(String prompt) {
        print(prompt);
        return Float.parseFloat(scanner.nextLine());
    }

    @Override
    public float readFloat(String prompt, float min, float max) {
        float newFloat;
        do {
            print(prompt);
            newFloat = Float.parseFloat(scanner.nextLine());
        } while (newFloat < min || newFloat > max);
        return newFloat;
    }

    @Override
    public int readInt(String prompt) {
        print(prompt);
        return Integer.parseInt(scanner.nextLine());
    }

    @Override
    public int readInt(String prompt, int min, int max) {
        int newInt;
        do {
            print(prompt);
            newInt = Integer.parseInt(scanner.nextLine());
        } while (newInt < min || newInt > max);
        return newInt;
    }

    @Override
    public long readLong(String prompt) {
        print(prompt);
        return Long.parseLong(scanner.nextLine());
    }

    @Override
    public long readLong(String prompt, long min, long max) {
        long newLong;
        do {
            print(prompt);
            newLong = Long.parseLong(scanner.nextLine());
        } while (newLong < min || newLong > max);
        return newLong;
    }

    @Override
    public String readString(String prompt) {
        print(prompt);
        return scanner.nextLine();
    }

    @Override
    public String readString(String prompt, int max) {
        boolean isValid = false;
        String userInput = "";
        do {
            userInput = readString(prompt);
            if (userInput.length() <= max) {
                isValid = true;
            } else {
                System.out.printf("The entry must be %s letters or less.\n", max);
            }
        } while (!isValid);
        return userInput;
    }

    @Override
    public String readMoney(BigDecimal amount) {
        return NumberFormat.getCurrencyInstance().format(amount);
    }

    @Override
    public LocalDate readDate(String prompt) {
        boolean isValid = false;
        LocalDate date = null;
        do {
            String value = null;
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
                value = readString(prompt);
                date = LocalDate.parse(value, formatter);
                isValid = true;
            } catch (DateTimeParseException e) {
                System.out.printf("The date entered '%s' is not a valid date.(MM/dd/yyyy)",value);
            }
        } while (!isValid);
        return date;
    }

    @Override
    public LocalDate readDate(String prompt, LocalDate min, LocalDate max) {
        boolean isValid = false;
        LocalDate date = null;
        do {
            date = readDate(prompt);
            if (date.isAfter(min) && date.isBefore(max)) {
                isValid = true;
            } else {
                System.out.printf("Choose a date within bounds. (%s to %s)\n",min,max);
            }
        } while (!isValid);
        return date;
    }

    @Override
    public BigDecimal readBigDecimal(String prompt, int scale) {
        boolean isValid = false;
        BigDecimal newNum = null;
        do {
            String value = null;
            try {
                value = readString(prompt);
                newNum = new BigDecimal(value).setScale(scale, ROUND_UP);
            } catch (NumberFormatException e) {
                System.out.printf("Your input '%s' is not a number.\n", value);
            }
        } while (!isValid);
        return newNum;
    }

    @Override
    public BigDecimal readBigDecimal(String prompt, int scale, BigDecimal min) {
        boolean isValid = false;
        BigDecimal newNum = null;
        do {
            newNum = readBigDecimal(prompt, scale);
            if (newNum.compareTo(min) >= 0) {
                isValid = true;
            } else {
                String minNumString = String.valueOf(min);
                System.out.printf("The num must be greater than %s.\n", minNumString);
            }
        } while (!isValid);
        return newNum;
    }
    
}
