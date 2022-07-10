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
class MainActivityTest {
    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)


    //RecyclerView comes into view
    @Test
    fun test_RecyclerViewIsDisplay(){
        val recycler = onView(withId(R.id.recyclerView))
        recycler.check(matches(isDisplayed()))
    }

    //Select list item, nav to DetailActivity
    @Test
    fun test_selectedListItemAndWorkerDetail(){
        Thread.sleep(1000)
        onView(withId(R.id.recyclerView))
            .perform(RecyclerViewActions.
            actionOnItemAtPosition<RecyclerView.ViewHolder>(4, click()))
        onView(withId(R.id.tvDescription))
            .check(matches(isDisplayed()))
    }

    //Select list item, nav to DetailActivity and press back to home
    @Test
    fun test_backNavigation(){

        val recycler = onView(withId(R.id.recyclerView))
        recycler.check(matches(isDisplayed()))

        //wait to response from API
        Thread.sleep(1000)

        //verify click at worker 20 and nav to DetailActivity
        onView(withId(R.id.recyclerView))
            .perform(RecyclerViewActions.
            actionOnItemAtPosition<RecyclerView.ViewHolder>(20, click()))

        onView(withId(R.id.tvCountry)).check(matches(isDisplayed()))

        pressBack()

        recycler.check(matches(isDisplayed()))
    }

}