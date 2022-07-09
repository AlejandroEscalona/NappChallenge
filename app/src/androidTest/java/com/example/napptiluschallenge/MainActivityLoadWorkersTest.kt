package com.example.napptiluschallenge

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
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
class MainActivityLoadWorkersTest {
    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun loadWorkerTest(){
        val tvTitle = onView(withId(R.id.tvTitle))
        //verify title in Activity main
        tvTitle.check(matches(withText("Workers")))
//        tvTitle.perform(click())
//        tvTitle.perform(replaceText("filter"))

        val btnfilter = onView(withId(R.id.btnFilter))
        btnfilter.perform(click())

        val recycler = onView(withId(R.id.recyclerView))
        recycler.check(matches(not(isDisplayed())))

        onView(withId(R.id.recyclerView))
          //  .check(matches(atPosition(0, withText("Test Text"))))

        //onView(withId(R.id.recyclerView)
//            .atPositionOnView(1, R.id.tvName))
//            .check(matches(not(withText("Test text"))))

//        val name = onView(withId(R.id.))
//        name.check(matches(withText("")))
    }

    @Test fun scrollToItemBelowFold_checkItsText() {
        // First, scroll to the position that needs to be matched and click on it.
        onView(withId(R.id.recyclerView))
            .perform(
             //   RecyclerViewActions.actionOnItemAtPosition(
                    2,
                    click()
                )
            )

        // Match the text in an item below the fold and check that it's displayed.
//        val itemElementText = "${activityRule.activity.resources
//            .getString(R.string.item_element_text)} ${ITEM_BELOW_THE_FOLD.toString()}"
//        onView(withText(itemElementText)).check(matches(isDisplayed()))
    }



}