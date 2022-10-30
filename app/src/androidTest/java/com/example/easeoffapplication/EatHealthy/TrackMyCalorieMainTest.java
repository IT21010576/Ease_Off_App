package com.example.easeoffapplication.EatHealthy;

import junit.framework.TestCase;

public class TrackMyCalorieMainTest extends TestCase {

    public void testCarbToCal() {
        assertEquals(4000,new TrackMyCalorieMain().carbToCal(1000));
        assertEquals(200,new TrackMyCalorieMain().carbToCal(50));
        assertEquals(0,new TrackMyCalorieMain().carbToCal(0));
    }

    public void testProtToCal() {
        assertEquals(8000,new TrackMyCalorieMain().protToCal(2000));
        assertEquals(400,new TrackMyCalorieMain().protToCal(100));
        assertEquals(0,new TrackMyCalorieMain().protToCal(0));
    }

    public void testFatToCal() {
        assertEquals(9000,new TrackMyCalorieMain().fatToCal(1000));
        assertEquals(900,new TrackMyCalorieMain().fatToCal(100));
        assertEquals(0,new TrackMyCalorieMain().fatToCal(0));
    }

    public void testGetTotalCalories() {
        assertEquals(2500,new TrackMyCalorieMain().getTotalCalories(1000,500,1000));
        assertEquals(250,new TrackMyCalorieMain().getTotalCalories(100,50,100));
        assertEquals(0,new TrackMyCalorieMain().getTotalCalories(0,0,0));
    }
}