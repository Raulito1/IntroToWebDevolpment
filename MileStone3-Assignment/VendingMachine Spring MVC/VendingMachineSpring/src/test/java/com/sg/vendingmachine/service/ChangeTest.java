/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.service;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author raulalvarado
 */
public class ChangeTest {
    
    Change change;
    
    public ChangeTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getNumPennies method, of class Change.
     */
    @Test
    public void testGetNumPennies() {
        change = new Change(4);
        assertEquals(4, change.getNumPennies());
    }

    /**
     * Test of getNumNickels method, of class Change.
     */
    @Test
    public void testGetNumNickels() {
        change = new Change(115);
        assertEquals(1, change.getNumNickels());
    }

    /**
     * Test of getNumDimes method, of class Change.
     */
    @Test
    public void testGetNumDimes() {
        change = new Change(115);
        assertEquals(1, change.getNumDimes());
    }

    /**
     * Test of getNumQuarters method, of class Change.
     */
    @Test
    public void testGetNumQuarters() {
        change = new Change(115);
        assertEquals(4, change.getNumQuarters());
    }


    
}
