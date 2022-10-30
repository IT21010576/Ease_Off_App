package com.example.easeoffapplication;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class SignupTest extends TestCase {

    @Rule
    public ActivityScenarioRule<Signup> activityRule = new ActivityScenarioRule<>(Signup.class);

    @Before
    public void setUp(){
        activityRule.getScenario();
    }

    @Test
    public void testPasswordLength() {
        onView(withId(R.id.createUsername)).perform(typeText("testUser"));
        onView(withId(R.id.enterdob)).perform(typeText("2000"));
        onView(withId(R.id.create_password)).perform(typeText("abcd"));
        onView(withId(R.id.create_password2)).perform(typeText("abcd"));
        onView(withId(R.id.signup_btn)).perform(click());
    }

    @Test
    public void testPasswordMatching() {
        onView(withId(R.id.createUsername)).perform(typeText("testUser"));
        onView(withId(R.id.enterdob)).perform(typeText("2000"));
        onView(withId(R.id.create_password)).perform(typeText("abcd12345"));
        onView(withId(R.id.create_password2)).perform(typeText("bcdd12345"));
        onView(withId(R.id.signup_btn)).perform(click());
    }

}