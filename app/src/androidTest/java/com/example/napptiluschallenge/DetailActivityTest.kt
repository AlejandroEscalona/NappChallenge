package com.example.napptiluschallenge



import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.*
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.intent.matcher.IntentMatchers.*
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.example.napptiluschallenge.mainModel.view.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@LargeTest
@RunWith(AndroidJUnit4::class)
class DetailActivityTest {
    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)


    //in list item, click in country
    @Test
    fun test_WorkerClickCountry(){
        Thread.sleep(2000)
        onView(withId(R.id.recyclerView))
            .perform(RecyclerViewActions.
            actionOnItemAtPosition<RecyclerView.ViewHolder>(4, click()))
        onView(withId(R.id.tvDescription))
            .check(matches(isDisplayed()))

        onView(withId(R.id.tvCountry))
            .perform(click())

//        Thread.sleep(2000)
//
//        pressBack()
//
//        onView(withId(R.id.tvDescription))
//            .check(matches(isDisplayed()))
    }

    //Select list item, nav to DetailActivity and press back to home
    @Test
    fun test_WorkerClickEmail(){
        Thread.sleep(2000)
        onView(withId(R.id.recyclerView))
            .perform(RecyclerViewActions.
            actionOnItemAtPosition<RecyclerView.ViewHolder>(4, click()))

        onView(withId(R.id.tvDescription))
            .check(matches(isDisplayed()))

        onView(withId(R.id.tvEmail))
            .perform(click())

//        Thread.sleep(2000)
//
//        pressBack()
//
//        onView(withId(R.id.tvDescription))
//            .check(matches(isDisplayed()))

    }
}