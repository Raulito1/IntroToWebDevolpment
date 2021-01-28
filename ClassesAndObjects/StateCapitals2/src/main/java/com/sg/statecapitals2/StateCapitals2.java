/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.statecapitals2;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author raulalvarado
 */
public class StateCapitals2 {
    public static void main(String[] args) {
        
       HashMap<String, String> population =  new HashMap<>();
       
       population.put("Alabama", "Montgomery");
       population.put("Alaska", "Juneau");
       population.put("Arizona", "Phoenix");
       population.put("Arkansas", "Little Rock");
       
       HashMap<Integer, Integer> populations = new HashMap<>();
        
       populations.put(20500, 156);
       populations.put(31000, 3255);
       populations.put(1445000, 517);
       populations.put(193000, 116);
       
       Set<String> keys = population.keySet();
       Set<Integer> key = populations.keySet();
       
       
       
    }
}
