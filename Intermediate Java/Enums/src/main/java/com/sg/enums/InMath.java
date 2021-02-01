/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.enums;

import java.util.Scanner;

/**
 *
 * @author raulalvarado
 */
public class InMath {
    public int calculate(MathOperator operator, int operand1, int operand2) {
        
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your first operand");
        operand1 = scanner.nextInt();
        System.out.println("Enter your second operand");
        operand2 = scanner.nextInt();
        System.out.println("Pick and Operator [+, - , / , *]");
        
        switch(operator) {
            case PLUS:
                return operand1 + operand2;
            case MINUS:
                return operand1 - operand2;
            case MULTIPLY:
                return operand1 * operand2;
            case DIVIDE:
                return operand1 / operand2;
            default :
                throw new UnsupportedOperationException();
        }
    }
    
}
