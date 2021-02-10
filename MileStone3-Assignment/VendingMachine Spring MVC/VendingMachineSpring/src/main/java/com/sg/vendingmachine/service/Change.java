/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.service;

/**
 *
 * @author raulalvarado
 */
public class Change {
    // creating a Change class to calculate change and return it in 
    // int value
    
    private int numPennies = 0;
    private int numNickels = 0;
    private int numDimes = 0;
    private int numQuarters = 0;
    
    public Change(int numPennies) {
        if (numPennies >= 25) {
            this.numQuarters = numPennies / 25;
            numPennies -= this.numQuarters * 25;
        }
        if (numPennies >= 10) {
            this.numDimes = numPennies / 10;
            numPennies -= this.numDimes * 10;
        }
        if (numPennies >= 5) {
            this.numNickels = numPennies / 5;
            numPennies -= this.numNickels * 5;
        }
        if (numPennies < 5) {
            this.numPennies = numPennies;
        }
    }
// setting Getters only 
    public int getNumPennies() {
        return numPennies;
    }

    public int getNumNickels() {
        return numNickels;
    }

    public int getNumDimes() {
        return numDimes;
    }

    public int getNumQuarters() {
        return numQuarters;
    }
    // setting Equals and HashCode

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + this.numPennies;
        hash = 89 * hash + this.numNickels;
        hash = 89 * hash + this.numDimes;
        hash = 89 * hash + this.numQuarters;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Change other = (Change) obj;
        if (this.numPennies != other.numPennies) {
            return false;
        }
        if (this.numNickels != other.numNickels) {
            return false;
        }
        if (this.numDimes != other.numDimes) {
            return false;
        }
        if (this.numQuarters != other.numQuarters) {
            return false;
        }
        return true;
    }
    
    
    
}
