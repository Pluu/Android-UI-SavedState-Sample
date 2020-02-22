package com.pluu.savedstate.util

import android.os.Bundle
import androidx.lifecycle.SavedStateHandle
import timber.log.Timber

fun Bundle.printLog(tag: String) {
    keySet().forEach { key ->
        Timber.tag(tag).i(" ⎣ [$key]:[${get(key)}]")
    }
}

fun SavedStateHandle.printLog(tag: String) {
    keys().forEach { key ->
        Timber.tag(tag).d(" ⎣ [$key]=[${get<Any>(key)}]")
    }
}