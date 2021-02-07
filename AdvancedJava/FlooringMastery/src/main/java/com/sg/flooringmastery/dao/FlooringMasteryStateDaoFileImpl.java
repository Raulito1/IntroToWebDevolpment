/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.State;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author raulalvarado
 */
public class FlooringMasteryStateDaoFileImpl implements FlooringMasteryStateDao {

    private static final String STATES_FILE = "taxes.txt";
    private static final String DELIMETER = ",";

    @Override
    public State getState(String stateAbbr) throws FlooringMasteryPersistenceException {
           List<State>states = loadStates();
        if (states == null) {
            return null;
        } else {
            State chosenState = states.stream()
                        .filter(s -> s.getStateAbbr().equalsIgnoreCase(stateAbbr))
                        .findFirst().orElse(null);
            return chosenState;
        }
    }
    
    private List<State> loadStates() throws FlooringMasteryPersistenceException {
        Scanner scanner;
        List<State> states = new ArrayList<>();
        
        try {
            scanner = new Scanner(
                new BufferedReader(
                    new FileReader (STATES_FILE)));
        } catch (FileNotFoundException e) {
            throw new FlooringMasteryPersistenceException (
                    "-_- Could not load states data into memory", e);
        }
        String currentLine;
        String[] currentTokens;
        scanner.nextLine();
        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentTokens = currentLine.split(DELIMETER);
            // should not have more than 2 delimeters
            if (currentTokens.length == 2) {
                State currentState = new State();
                currentState.setStateAbbr(currentTokens[0]);
                currentState.setTaxRate(new BigDecimal(currentTokens[1]));
                states.add(currentState);
            } else {
                
            }
        }
        scanner.close();
        if (!states.isEmpty()) {
            return states;
        } else {
            return null;
        }
    }
    
    
    
}
