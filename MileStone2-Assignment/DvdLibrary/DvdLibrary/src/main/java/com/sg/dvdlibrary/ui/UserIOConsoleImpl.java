/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.ui;

import java.util.Scanner;

/**
 *
 * @author raulalvarado
 */
public class UserIOConsoleImpl implements UserIO {
    
    Scanner scanner = new Scanner(System.in);

    @Override
    public void print(String msg) {
        System.out.println(msg);
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
            System.out.println("");
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
            System.out.println("");
            newFloat = Float.parseFloat(scanner.nextLine());
        }
        while (newFloat < min || newFloat > max);
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
            System.out.println("");
            newInt = Integer.parseInt(scanner.nextLine());
        }
        while (newInt < min || newInt > max);
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
            System.out.println("");
            newLong = Long.parseLong(scanner.nextLine());
        }
        while (newLong < min || newLong > max);
        return newLong;
    }

    @Override
    public String readString(String prompt) {
        print(prompt);
        return scanner.nextLine();
    }
    
}
