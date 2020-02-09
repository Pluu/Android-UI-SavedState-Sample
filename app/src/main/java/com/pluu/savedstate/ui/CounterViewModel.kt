package com.pluu.savedstate.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import timber.log.Timber

class CounterViewModel : ViewModel() {

    private var counter = 0

    private val _countForm = MutableLiveData<Int>(counter)
    val countState: LiveData<Int> = _countForm

    fun incCounter() {
        ++counter
        Timber.d("Inc Counter => $counter")
        _countForm.value = counter
    }
}
