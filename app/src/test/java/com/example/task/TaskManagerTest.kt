package com.example.task

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.shadows.ShadowSQLiteConnection

@RunWith(AndroidJUnit4::class)
class TaskManagerTest {

    @Before
    fun setUp() {
        ShadowSQLiteConnection.setUseInMemoryDatabase(true)
    }

    @After
    fun tearDown() {
        ShadowSQLiteConnection.reset()
    }

    @Test
    fun `Given I have no tasks And I see Save Task screen And I fill description When I tap Save button Then I see Task List screen with description`() {

        val scenario = ActivityScenario.launch(MainActivity::class.java)
        val addTask = withId(R.id.view_task_list_add_task_button)
        val description = withId(R.id.view_add_task_description_input_field)
        val saveTask = withId(R.id.view_add_task_save_task_button)

        onView(addTask).perform(click())
        onView(description).perform(replaceText("My description"))
        onView(saveTask).perform(click())

        onView(withText("My description")).check(matches(isDisplayed()))

        scenario.close()
    }
}