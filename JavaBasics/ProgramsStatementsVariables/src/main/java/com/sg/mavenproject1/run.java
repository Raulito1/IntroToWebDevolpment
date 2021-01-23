/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.mavenproject1;

/**
 *
 * @author raulalvarado
 */
public class run {
    public static void main(String[] args) {
        // NOTES: Progeams must be written for peoplw to read, and only incidnetally for
        // machines to execute
        
        // JAVA supports 3 types of comments:
        // - Single-Line
        // - Multi-Line
        // - Doc
        
        
        // Single-Line Comments 
        //  Single-Line comments begin with //. The compiler will ignore everything
        // between the // and the end of the line.
        System.out.println("Hello, World!");// print to console
        
        
         // Declare variables to use in the examples
        int result;
        int operand1;
        int operand2;
        int operand3;

        //
        // Assignment
        //
        // Initialize result with the value of 0 by using the 
        // assignment (=) operator. The assignment operator takes the 
        // value on the right and assigns it to the variable on the 
        // left
        result = 0; // now result has the value of 0

        // Initialize the operands
        operand1 = 5;
        operand2 = 7;

        // Assignment works with variable values as well as literal 
        // values. We'll set operand3 to the same value as operand2
        operand3 = operand2; // now both have the value 7

        //
        // Addition
        //
        // Addition is a binary infix operator.  It works with 
        // literals:
        result = 42 + 53;  // result is now 95

        // It also works with variables:
        result = operand1 + operand2;  // result now equals 12

        // It works with a combination of literals and variables:
        result = 1 + operand1;  // result now equals 6

        // You can chain addition operators together:
        // result now equals 20
        result = 1 + operand1 + operand2 + operand3;

        // Finally, the += operator is used to add a value to a 
        // variable. result += operand1 is equivalent to 
        // result = result + operand1.
        // NOTE: the initial value of result is used to calculate the 
        //       new value of result:
        result = 2;  // set result to 2
        result += 4;  // result is now equal to 6 (2 + 4)
        result += operand1; // result is now equal to 11 (6 + 5)

        //
        // Subtraction
        //
        // Subtraction is a binary infix operator.  It works with 
        // literals:
        result = 9 - 5;  // result is now 4

        // It also works with variables:
        result = operand1 - operand2;  // result now equals -2

        // It works with a combination of literals and variables:
        result = 15 - operand1;  // result now equals 10

        // You can chain subtraction operators together:
        // result now equals 0
        result = 19 - operand1 - operand2 - operand3;

        // Finally, the -= operator is used to add a value to a 
        // variable. result -= operand1 is equivalent to 
        // result = result - operand1.
        // NOTE: the initial value of result is used to calculate the 
        //       new value of result:
        result = 2;  // set result to 2
        result -= 4;  // result is now equal to -2 (2 - 4)
        result -= operand1; // result is now equal to -7 (-2 - 5)

        //
        // Multiplication
        //
        // Multiplication is a binary infix operator.  It works with 
        // literals:
        result = 2 * 3;  // result is now 6

        // It also works with variables:
        result = operand1 * operand2;  // result now equals 35

        // It works with a combination of literals and variables:
        result = 2 * operand1;  // result now equals 10

        // You can chain multiplication operators together:
        // result now equals 490
        result = 2 * operand1 * operand2 * operand3;

        // Finally, the *= operator is used to add a value to a 
        // variable. result *= operand1 is equivalent to 
        // result = result * operand1.
        // NOTE: the initial value of result is used to calculate the 
        //       new value of result:
        result = 2;  // set result to 2
        result *= 4;  // result is now equal to 8 (2 * 4)
        result *= operand1; // result is now equal to 40 (8 * 5)

        //
        // Division and Modulus
        //
        // Division is a binary infix operator.  It works with 
        // literals:
        result = 6 / 3;  // result is now 2

        // It also works with variables:
        result = operand1 / operand2;  // result now equals 0        

        // What?!?!?!?!  When dividing integers, integer division 
        // is used - we only get the whole number part of the 
        // quotient.  In this case, 7 goes into 5 0 times with a 
        // remainder of 5
        // We use the modulus operator (%) to get the remainder:
        result = operand1 % operand2;  // result now equals 5

        // It works with a combination of literals and variables:
        result = 20 / operand1;  // result now equals 4

        // You can chain division operators together:
        // result now equals 1
        result = 245 / operand1 / operand2 / operand3;

        // Finally, the /= operator is used to add a value to a 
        // variable. result /= operand1 is equivalent to 
        // result = result / operand1.
        // NOTE: the initial value of result is used to calculate the 
        //       new value of result:
        result = 40;  // set result to 40
        result /= 4;  // result is now equal to 10 (40 / 4)
        result /= operand1; // result is now equal to 2 (10 / 5)
        
        //==================== Adder Version 1 =======================
            // declare sum and inisitalize it to 0
    int sum = 0;
    //delare and initialize our operands
    int operand1 = 3;
    int operand2 = 2;
        
    //assign the sum of operand1 and operand2 to sum
    sum = operand1 + operand2;
        
    // NOTE: the plus (+) operator is acting as the 
    // string concatenation operator instead of the 
    // addition operator.  In Java, we use the plus (+)
    // operator to concatentate string values.
    System.out.println("Sum is: " + sum);

        
    }   
        // Multi-line comments begin wit /* , can span multiple linws, and end with */
        // Thecompilier ignores everything between /* and */ inclusive
        /*
            public static void main(String[] args)
            This is the entry point for the program.
            Prints "Hello, World!" to the console.
    
            public static void main(String[] args) {
            System.out.println("Hello, World");
            }
        */
        
    
    /**
     *  `Documentation for a method
     * 
     * 
     * 
     * 
     */
    
}
