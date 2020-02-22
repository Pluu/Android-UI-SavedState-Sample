package com.pluu.savedstate.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.pluu.savedstate.util.printLog
import com.pluu.savedstate.util.simpleName
import timber.log.Timber

class SavedStateCounterViewModel(
    private val handle: SavedStateHandle
) : ViewModel() {

    private val COUNT_KEY = "count"
    val extraLiveData: LiveData<Int> = handle.getLiveData("case_1", 0)

    // Get value of SavedStateHandle
    private var counter = handle.get<Int>(COUNT_KEY) ?: 0
        set(value) {
            // Set value of SavedStateHandle
            handle[COUNT_KEY] = value
            field = value
        }

    // Get LiveData of SavedStateHandle
    val countLiveData: LiveData<Int> = handle.getLiveData(COUNT_KEY, 0)

    init {
        Timber.tag(simpleName).d("SavedStateHandle")
        handle.printLog(Log.DEBUG, this.simpleName)
    }

    fun incCounter() {
        Timber.d("Inc Counter => $counter")
        ++counter
    }
}
