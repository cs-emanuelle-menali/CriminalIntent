package com.example.emanuellemenali.criminalintent

import android.content.Intent
import android.support.test.espresso.Espresso.onData
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.typeText
import android.support.test.rule.ActivityTestRule
import android.support.test.espresso.matcher.ViewMatchers.*
import com.example.emanuellemenali.criminalintent.view.activity.CrimeActivity
import com.example.emanuellemenali.criminalintent.view.activity.CrimeListActivity
import org.hamcrest.CoreMatchers.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
class CriminalListTest {

    @JvmField @Rule
    val activityCrimeList = ActivityTestRule<CrimeListActivity>(CrimeListActivity::class.java,
            true, false)

    @JvmField @Rule
    val activityCrimeDetail = ActivityTestRule<CrimeActivity>(CrimeActivity::class.java,
            true, false)

    private fun launchActivityListCrime() {
        activityCrimeList.launchActivity(Intent())
    }

    private fun launchActivityCrime(){
        activityCrimeDetail.launchActivity(Intent())
    }

    @Test
    fun addNewCrimeToList(){

        launchActivityListCrime()

        onView(withId(R.id.new_crime)).perform(click())
        onView(allOf(withId(R.id.edit_crime_title), isDisplayed())).perform(typeText("Murder"));

    }

}