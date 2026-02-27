package com.example.androiduitesting;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.CoreMatchers.is;

import androidx.test.espresso.action.ViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTest {

    // for some reason adb connection keeps killing the tests below all pass but for some reason it says 1 of the test fails at least on my machine, but I can see them real time completing... so im not too sure. I think its just issues with windows as when I code on my macbook I never have any ide bugs

    @Rule
    public ActivityScenarioRule<MainActivity> scenario =
            new ActivityScenarioRule<MainActivity>(MainActivity.class);


    @Test
    public void testActivitySwitch() {
        // Add a city
        onView(withId(R.id.button_add)).perform(click());
        onView(withId(R.id.editText_name)).perform(ViewActions.typeText("Edmonton"));
        onView(withId(R.id.button_confirm)).perform(click());

        // Click the city in the listview
        onData(is("Edmonton")).inAdapterView(withId(R.id.city_list)).perform(click());

        // Confirm ShowActivity opened by checking for the back button
        onView(withId(R.id.textBackButton)).check(matches(isDisplayed()));
    }


    @Test
    public void testCityName() {
        // Add a city
        onView(withId(R.id.button_add)).perform(click());
        onView(withId(R.id.editText_name)).perform(ViewActions.typeText("Calgary"));
        onView(withId(R.id.button_confirm)).perform(click());

        // Click the city in the listview
        onData(is("Calgary")).inAdapterView(withId(R.id.city_list)).perform(click());

        // Confirm the city shows in ShowActivity
        onView(withId(R.id.textCityName)).check(matches(withText("Calgary")));
    }


    @Test
    public void testBackButton() {
        // Add a city
        onView(withId(R.id.button_add)).perform(click());
        onView(withId(R.id.editText_name)).perform(ViewActions.typeText("Toronto"));
        onView(withId(R.id.button_confirm)).perform(click());

        // Click the city to open ShowActivity
        onData(is("Toronto")).inAdapterView(withId(R.id.city_list)).perform(click());

        // Press the back button
        onView(withId(R.id.textBackButton)).perform(click());

        // Confirm the back button works by checking if we are back on the MainActivity
        onView(withId(R.id.city_list)).check(matches(isDisplayed()));
    }
}