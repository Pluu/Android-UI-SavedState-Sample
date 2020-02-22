package com.pluu.savedstate.util

import android.os.Bundle
import androidx.lifecycle.SavedStateHandle
import timber.log.Timber

fun Bundle.printLog(tag: String, prefix: String = "") {
    keySet().forEach { key ->
        when (val value = get(key)) {
            is Bundle -> {
                Timber.tag(tag).d("$prefix ⎣ [$key]")
                value.printLog(tag = tag, prefix = "$prefix   ")
            }
            else -> {
                Timber.tag(tag).d("$prefix ⎣ [$key]:[$value]")
            }
        }
    }
}

fun SavedStateHandle.printLog(tag: String, prefix: String = "") {
    keys().forEach { key ->
        when (val value = get<Any>(key)) {
            is Bundle -> {
                Timber.tag(tag).d(" ⎣ [$key]=[${get<Any>(key)}]")
                value.printLog(tag = tag, prefix = "$prefix   ")
            }
            else -> {
                Timber.tag(tag).d("$prefix ⎣ [$key]:[$value]")
            }
        }
    }
}