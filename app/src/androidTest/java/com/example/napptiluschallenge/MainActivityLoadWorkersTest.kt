package com.example.napptiluschallenge

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.example.napptiluschallenge.common.entities.Worker
import com.example.napptiluschallenge.mainModel.view.MainActivity
import org.hamcrest.CoreMatchers.not
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.concurrent.thread

@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityLoadWorkersTest {
    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun loadWorkersTest(){
        //verify title in Activity main
        val tvTitle = onView(withId(R.id.tvTitle))
        tvTitle.check(matches(withText("Workers")))

        //wait to response from API
        Thread.sleep(3000)

        //verify recyclerView is display
        val recycler = onView(withId(R.id.recyclerView))
        recycler.check(matches(isDisplayed()))

        //verify worker 0 is loaded
        val item1 = onView(withText("Marcy"))
        item1.check(matches(isDisplayed()))

        //verify progressBar is hide
        onView(withId(R.id.lProgress)).check(matches(not(isDisplayed())))

        //verify scroll to position 15
        onView(withId(R.id.recyclerView))
            .perform(RecyclerViewActions.
            scrollToPosition<RecyclerView.ViewHolder>(15))

        //verify click at position 20
        onView(withId(R.id.recyclerView))
            .perform(RecyclerViewActions.
            actionOnItemAtPosition<RecyclerView.ViewHolder>(20, click()))

        //verify worker 20 is loaded
        val item20 = onView(withText("Todd"))
        item20.check(matches(isDisplayed()))
    }

//
//    @Test fun scrollToItemBelowFold_checkItsText() {
//        // First, scroll to the position that needs to be matched and click on it.
//        onView(withId(R.id.recyclerView))
//            .perform(
//               // RecyclerViewActions.actionOnItemAtPosition(
//                    2,
//                    click()
//                )
//            )
//
//         Match the text in an item below the fold and check that it's displayed.
//        val itemElementText = "${activityRule.activity.resources
//            .getString(R.string.item_element_text)} ${ITEM_BELOW_THE_FOLD.toString()}"
//        onView(withText(itemElementText)).check(matches(isDisplayed()))
//    }



}