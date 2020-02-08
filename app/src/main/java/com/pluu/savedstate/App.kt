package com.pluu.savedstate

import android.app.Activity
import android.app.Application
import android.os.Bundle
import timber.log.Timber

private val Any.simpleName: String
    get() = javaClass.simpleName

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())

        registerActivityLifecycleCallbacks(object : ActivityLifecycleCallbacks {
            override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
                Timber.i("Created : ${activity.simpleName}")
                savedInstanceState?.keySet()?.forEach { key ->
                    Timber.i("SaveInstanceState [$key]:[${savedInstanceState.get(key)}]")
                }
            }

            override fun onActivityStarted(activity: Activity) {
                Timber.i("Started : ${activity.simpleName}")
            }

            override fun onActivityResumed(activity: Activity) {
                Timber.i("Resumed : ${activity.simpleName}")
            }

            override fun onActivityPaused(activity: Activity) {
                Timber.i("Paused : ${activity.simpleName}")
            }

            override fun onActivityStopped(activity: Activity) {
                Timber.i("Stopped : ${activity.simpleName}")
            }

            override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
                Timber.i("SaveInstanceState : ${activity.simpleName}")
                outState.keySet().forEach { key ->
                    Timber.i("SaveInstanceState [$key]:[${outState.get(key)}]")
                }
            }

            override fun onActivityDestroyed(activity: Activity) {
                Timber.i("Destroyed : ${activity.simpleName}")
            }
        })
    }
}