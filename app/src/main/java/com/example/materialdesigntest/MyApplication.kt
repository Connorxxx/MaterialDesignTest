package com.example.materialdesigntest

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.os.AsyncTask
import java.util.concurrent.Executor

class MyApplication : Application() {

    companion object {
        fun getApplication(): MyApplication {
            return instance
        }
        @SuppressLint("StaticFiledLeak")
        lateinit var context: Context
        lateinit var instance: MyApplication
        lateinit var mExecutor: Executor


    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        context = applicationContext

        mExecutor = AsyncTask.THREAD_POOL_EXECUTOR

    }

    fun runOnBackground(runnable: Runnable) {
        mExecutor.execute(runnable)
    }

    fun getApplication() = instance
}

