/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.statecapitals1;

import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

/**
 *
 * @author raulalvarado
 * Sample output (order may vary):
STATES:
=======
Alabama
Alaska
Arizona
Arkansas
…
…
 
CAPITALS:
=========
Montgomery
Juneau
Phoenix
Little Rock
…
…
 
STATE/CAPITAL PAIRS:
====================
Alabama - Montgomery
Alaska - Juneau
Arizona - Phoenix
Arkansas - Little Rock
 * 
 */
public class StateCapitals1 {
    public static void main(String[] args) {
        
        HashMap<String, String> states = new HashMap<>();
        
        // add first State and Capital
        states.put("Alabama", "Montgomery");
        // add the second State and Capital
        states.put("Alaska", "Juneau");
        // add the third State and Capital
        states.put("Arizona", "Phoenix");
        // add the fourth State and Capital
        states.put("Arkansas", "Little Rock");
        
        // get the Set of keys from the map
        Set<String> keys = states.keySet();
        
        // print the keys to the screen -
        System.out.println("STATES:");
        System.out.println("==========");
        for (String k : keys) {
            System.out.println(k);
        }
        // get the Collection of values from the Map
        Collection<String> values = states.values();
        // list all of the population values -
        System.out.println("");
        System.out.println("CAPITALS:");
        System.out.println("===========");
        for (String v : values) {
            System.out.println(v);
        }
        
        System.out.println("");
        System.out.println("STATE/CAPITAL PAIRS:");
        System.out.println("======================");
        // print the keys and associated values to the screen
        for (String k1 : keys) {
            System.out.println(k1 + "-" + states.get(k1));
        }
    }
}
