package com.pluu.savedstate.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.SavedStateViewModelFactory
import androidx.lifecycle.ViewModelProvider
import com.pluu.savedstate.databinding.ActivityCounterBinding
import timber.log.Timber

class SavedStateViewModelCounterActivity : AppCompatActivity() {

    private lateinit var counterViewModel: SavedStateCounterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityCounterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        counterViewModel = ViewModelProvider(
            this,
            SavedStateViewModelFactory(application, this)
        ).get(SavedStateCounterViewModel::class.java)

        Timber.d("ViewModel = ${counterViewModel.hashCode()}")

        counterViewModel.countState.observe(this, Observer {
            binding.counter.text = it.toString()
        })

        binding.fab.setOnClickListener {
            counterViewModel.incCounter()
        }
    }
}