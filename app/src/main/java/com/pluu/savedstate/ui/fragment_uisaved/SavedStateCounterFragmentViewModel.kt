package com.pluu.savedstate.ui.fragment_uisaved

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import timber.log.Timber

class SavedStateCounterFragmentViewModel(
    private val handle: SavedStateHandle
) : ViewModel() {

    private val COUNT_KEY = "frag_count"
    val extraLiveData: LiveData<String> = handle.getLiveData("fragment_case", "Default Text")

    // Get value of SavedStateHandle
    private var counter = handle.get<Int>(COUNT_KEY) ?: 0
        set(value) {
            // Set value of SavedStateHandle
            handle[COUNT_KEY] = value
            field = value
        }

    init {
        Timber.d(">>> Run")
    }

    // Get LiveData of SavedStateHandle
    val countLiveData: LiveData<Int> = handle.getLiveData(COUNT_KEY, 0)

    init {
        handle.keys().forEach { key ->
            Timber.d("Received [$key]=[${handle.get<Any>(key)}]")
        }
    }

    fun incCounter() {
        Timber.d("Inc Counter => $counter")
        ++counter
    }
}
