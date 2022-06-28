package com.flandre.android

import android.app.Activity

object ActivityCollector {
    private val activities = ArrayList<Activity>()

    fun addActivity(activity: Activity) {
        activities.add(activity)
    }

    fun removeActivity(activity: Activity) {
        activities.remove(activity)
    }

    fun finishAll() {
        for (actvity in activities) {
            actvity.finish()
        }
        activities.clear()
    }
}