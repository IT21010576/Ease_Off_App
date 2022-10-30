package com.example.easeoffapplication.EatHealthy;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import junit.framework.TestCase;

import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class NutriFactsTest extends TestCase {

    public void testCalculateAge() {
        assertEquals(20,new NutriFacts().calculateAge(2002,2022));
    }
}