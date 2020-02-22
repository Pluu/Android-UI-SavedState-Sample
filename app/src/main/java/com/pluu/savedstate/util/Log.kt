package com.pluu.savedstate.util

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.SavedStateHandle
import timber.log.Timber

fun Bundle.printLog(level: Int = Log.DEBUG, tag: String, prefix: String = "") {
    keySet().forEach { key ->
        when (val value = get(key)) {
            is Bundle -> {
                Timber.tag(tag).log(level, "$prefix ⎣ [$key]")
                value.printLog(level = level, tag = tag, prefix = "$prefix   ")
            }
            else -> {
                Timber.tag(tag).log(level, "$prefix ⎣ [$key]:[$value]")
            }
        }
    }
}

fun SavedStateHandle.printLog(level: Int = Log.DEBUG, tag: String, prefix: String = "") {
    keys().forEach { key ->
        when (val value = get<Any>(key)) {
            is Bundle -> {
                Timber.tag(tag).log(level, " ⎣ [$key]=[${get<Any>(key)}]")
                value.printLog(level = level, tag = tag, prefix = "$prefix   ")
            }
            else -> {
                Timber.tag(tag).log(level, "$prefix ⎣ [$key]:[$value]")
            }
        }
    }
}