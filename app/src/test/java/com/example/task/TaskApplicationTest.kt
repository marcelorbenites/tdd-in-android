package com.example.task

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.task.list.TaskListScreen
import com.example.task.save.SaveTaskScreen
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.shadows.ShadowSQLiteConnection

@RunWith(AndroidJUnit4::class)
class TaskApplicationTest {

    @Before
    fun setUp() {
        ShadowSQLiteConnection.setUseInMemoryDatabase(true)
    }

    @After
    fun tearDown() {
        ShadowSQLiteConnection.reset()
    }

    @Test
    fun `Given I have no tasks yet When I open task application Then I see Task List screen And I see no tasks`() {

        val taskApplication = TaskApplication(InstrumentationRegistry.getInstrumentation().targetContext)

        taskApplication.open()

        taskApplication.withScreenCallback { screen ->
            assertEquals(emptyList<String>(), (screen as TaskListScreen).tasks)
        }
    }

    @Test
    fun `Given I see Task List screen When I tap add task Then I see Save Task screen`() {

        val taskApplication = TaskApplication(InstrumentationRegistry.getInstrumentation().targetContext)
        taskApplication.open()

        taskApplication.addTask()

        taskApplication.withScreenCallback { screen ->
            assertEquals(true, screen is SaveTaskScreen)
        }
    }

    @Test
    fun `Given I have a task And I close the application When I open task application Then I see Task List screen And I see the task`() {

        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val taskApplication = TaskApplication(context)
        taskApplication.open()
        taskApplication.addTask()
        taskApplication.saveTask("Clean up my room")
        val newTaskApplication = TaskApplication(context)

        newTaskApplication.open()

        newTaskApplication.withScreenCallback { screen ->
            assertEquals(true, screen is TaskListScreen)
            assertEquals(listOf("Clean up my room"), (screen as TaskListScreen).tasks)
        }
    }
}
