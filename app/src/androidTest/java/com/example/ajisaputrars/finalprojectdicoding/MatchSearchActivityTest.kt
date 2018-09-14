package com.example.ajisaputrars.finalprojectdicoding

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.typeText
import android.support.test.espresso.Espresso.pressBack
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import com.example.ajisaputrars.finalprojectdicoding.R.id.*
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)

class MatchSearchActivityTest {
    @Rule
    @JvmField var activityRule = ActivityTestRule(MatchSearchActivity::class.java)

    @Test
    fun testSearchMatch() {
        onView(withId(search_src_text)).check(matches(isDisplayed()))
        onView(withId(search_src_text)).perform(typeText("Barcelona"))
        delay()

        onView(withId(R.id.listEvent)).check(matches(isDisplayed()))
        delay()

        onView(withId(R.id.listEvent)).perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click())
        )
        delay()

        onView(withId(R.id.menu_item_add_favorite_menu_detail_match)).check(matches(isDisplayed()))
        onView(withId(R.id.menu_item_add_favorite_menu_detail_match)).perform(click())

        pressBack()
        onView(withId(R.id.listEvent)).perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click())
        )
        delay()
        onView(withId(R.id.menu_item_add_favorite_menu_detail_match)).check(matches(isDisplayed()))
        onView(withId(R.id.menu_item_add_favorite_menu_detail_match)).perform(click())
    }

    private fun delay(second: Long = 1) {
        Thread.sleep(1000 * second)
    }
}