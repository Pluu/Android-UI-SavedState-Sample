package com.pluu.savedstate.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import timber.log.Timber

class SavedStateCounterViewModel(
    private val handle: SavedStateHandle
) : ViewModel() {

    // Get value of SavedStateHandle
    private var counter = handle.get<Int>("counter") ?: 0
        set(value) {
            // Set value of SavedStateHandle
            handle.set("counter", value)
            field = value
        }

    private val _countForm = MutableLiveData<Int>(counter)
    val countState: LiveData<Int> = _countForm

    // Get LiveData of SavedStateHandle
    val countLiveData: LiveData<Int> = handle.getLiveData("count", 0)

    fun incCounter() {
        ++counter
        Timber.d("Inc Counter => $counter")
        _countForm.value = counter
    }
}
