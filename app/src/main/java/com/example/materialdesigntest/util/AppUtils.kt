package com.example.materialdesigntest.util

import android.content.Context

object AppUtils {

    fun getScreenWidthDp(context: Context): Int {
        val displayMetrics = context.resources.displayMetrics
        return (displayMetrics.widthPixels / displayMetrics.density).toInt()
    }
}