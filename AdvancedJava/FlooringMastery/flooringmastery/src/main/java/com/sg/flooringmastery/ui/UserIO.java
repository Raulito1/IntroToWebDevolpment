/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.ui;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 *
 * @author raulalvarado
 */
public interface UserIO {
            void print(String message);
    
            double readDouble(String prompt);

	    double readDouble(String prompt, double min, double max);

	    float readFloat(String prompt);

	    float readFloat(String prompt, float min, float max);

	    int readInt(String prompt);

	    int readInt(String prompt, int min, int max);

	    long readLong(String prompt);

	    long readLong(String prompt, long min, long max);

	    String readString(String prompt);
            
            String readString(String prompt, int max);
            
            String readMoney(BigDecimal amount);
            
            LocalDate readDate(String prompt);
            
            LocalDate readDate(String prompt, LocalDate min, LocalDate max);
            
            BigDecimal readBigDecimal(String prompt, int scale);
            
            BigDecimal readBigDecimal(String prompt, int scale, BigDecimal min);
}
