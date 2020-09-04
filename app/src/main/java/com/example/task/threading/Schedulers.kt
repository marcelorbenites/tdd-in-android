package com.example.task.threading

import android.os.Handler
import android.os.HandlerThread
import android.os.Looper

interface Scheduler {
    fun schedule(action: () -> Unit)
}

class HandlerScheduler(private val name: String) : Scheduler {

    private val handler by lazy {
        val handlerThread = HandlerThread(name)
        handlerThread.start()
        Handler(handlerThread.looper)
    }

    override fun schedule(action: () -> Unit) {
        handler.post {
            action()
        }
    }
}

object MainHandlerScheduler: Scheduler {

    private val mainHandler by lazy {
        Handler(Looper.getMainLooper())
    }

    override fun schedule(action: () -> Unit) {
        mainHandler.post {
            action()
        }
    }
}
