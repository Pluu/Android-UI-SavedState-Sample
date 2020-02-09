package com.pluu.savedstate.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import timber.log.Timber

class SavedStateCounterViewModel(
    private val handle: SavedStateHandle
) : ViewModel() {

    private var counter = handle.get<Int>("counter") ?: 0
        set(value) {
            handle.set("counter", value)
            field = value
        }

    private val _countForm = MutableLiveData<Int>()
    val countState: LiveData<Int> = _countForm

    fun incCounter() {
        ++counter
        Timber.d("Inc Counter => $counter")
        _countForm.value = counter
    }
}
