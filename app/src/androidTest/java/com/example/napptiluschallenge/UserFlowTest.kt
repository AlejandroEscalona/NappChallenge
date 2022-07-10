package com.example.napptiluschallenge

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.*
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.example.napptiluschallenge.mainModel.view.MainActivity
import org.hamcrest.CoreMatchers.not
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class UserFlowTest {
    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun test_UserFlow(){
        //verify title in Activity main
        val tvTitle = onView(withId(R.id.tvTitle))
        tvTitle.check(matches(withText("Workers")))

        //wait to response from API
        Thread.sleep(1000)

        //verify recyclerView is display
        val recycler = onView(withId(R.id.recyclerView))
        recycler.check(matches(isDisplayed()))

        //verify worker 0 is loaded
        val item1 = onView(withText("Marcy"))
        item1.check(matches(isDisplayed()))

        //verify progressBar is hide
        onView(withId(R.id.lProgress))
            .check(matches(not(isDisplayed())))

        //verify scroll to position 15
        onView(withId(R.id.recyclerView))
            .perform(RecyclerViewActions.
            scrollToPosition<RecyclerView.ViewHolder>(15))

        //verify click at worker 20 and nav to DetailActivity
        onView(withId(R.id.recyclerView))
            .perform(RecyclerViewActions.
            actionOnItemAtPosition<RecyclerView.ViewHolder>(20, click()))


        onView(withId(R.id.tvDescription))
            .check(matches(isDisplayed()))
    }

}