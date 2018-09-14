package com.example.ajisaputrars.finalprojectdicoding

import android.support.test.espresso.Espresso
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.rule.ActivityTestRule
import android.support.v7.widget.RecyclerView
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

class TeamSearchActivityTest {
    @Rule
    @JvmField var activityRule = ActivityTestRule(TeamSearchActivity::class.java)

    @Test
    fun testSearchMatch() {
        Espresso.onView(ViewMatchers.withId(R.id.search_src_text)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.search_src_text)).perform(ViewActions.typeText("Barcelona"))
        delay()

        Espresso.onView(ViewMatchers.withId(R.id.listEvent)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        delay()

        Espresso.onView(ViewMatchers.withId(R.id.listEvent)).perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, ViewActions.click())
        )
        delay()

        Espresso.onView(ViewMatchers.withId(R.id.menu_item_add_favorite_menu_detail_match)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.menu_item_add_favorite_menu_detail_match)).perform(ViewActions.click())

        Espresso.pressBack()

        Espresso.onView(ViewMatchers.withId(R.id.listEvent)).perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, ViewActions.click())
        )
        delay()
        Espresso.onView(ViewMatchers.withId(R.id.menu_item_add_favorite_menu_detail_match)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.menu_item_add_favorite_menu_detail_match)).perform(ViewActions.click())
    }

    private fun delay(second: Long = 1) {
        Thread.sleep(1000 * second)
    }
}