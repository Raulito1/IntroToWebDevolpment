/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.addressbook.ui;

import java.util.Scanner;

/**
 *
 * @author raulalvarado
 */
public class UserIOConsoleImpl implements UserIO {
    Scanner scan = new Scanner(System.in);

    @Override
    public void print(String message) {
        System.out.println(message + '\n');
    }

    @Override
    public double readDouble(String prompt) {
        print(prompt);
        return Double.parseDouble(scan.nextLine());
    }

    @Override
    public double readDouble(String prompt, double min, double max) {
        double newDouble;
        do {
            print(prompt);
            System.out.println("");
            newDouble = Double.parseDouble(scan.nextLine());
        }
        while (newDouble < min || newDouble > max);
        return newDouble;
    }

    @Override
    public float readFloat(String prompt) {
        print(prompt);
        return Float.parseFloat(scan.nextLine());
    }

    @Override
    public float readFloat(String prompt, float min, float max) {
        float newFloat;
        do {
            print(prompt);
            System.out.println("");
            newFloat = Float.parseFloat(scan.nextLine());
        }
        while (newFloat < min || newFloat > max);
        return newFloat;
    }

    @Override
    public int readInt(String prompt) {
        print(prompt);
        return Integer.parseInt(scan.nextLine());
    }

    @Override
    public int readInt(String prompt, int min, int max) {
        int newInt;
        do {
            print(prompt);
            System.out.println("");
            newInt = Integer.parseInt(scan.nextLine());
        }
        while (newInt < min || newInt > max);
        return newInt;
    }

    @Override
    public long readLong(String prompt) {
        print(prompt);
        return Long.parseLong(scan.nextLine());
    }

    @Override
    public long readLong(String prompt, long min, long max) {
        long newLong;
        do {
            print(prompt);
            System.out.println("");
            newLong = Long.parseLong(scan.nextLine());
        }
        while (newLong < min || newLong > max);
        return newLong;
    }

    @Override
    public String readString(String prompt) {
        print(prompt);
        System.out.println("");
        return scan.nextLine();
    }
}
