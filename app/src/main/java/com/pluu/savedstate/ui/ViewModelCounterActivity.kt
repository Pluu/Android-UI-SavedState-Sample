package com.pluu.savedstate.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.pluu.savedstate.databinding.ActivityCounterBinding
import timber.log.Timber

class ViewModelCounterActivity : AppCompatActivity() {

    private lateinit var counterViewModel: CounterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityCounterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        counterViewModel = ViewModelProviders.of(this).get(CounterViewModel::class.java)

        Timber.d("ViewModel = ${counterViewModel.hashCode()}")

        counterViewModel.countState.observe(this, Observer {
            binding.counter.text = it.toString()
        })
        binding.fab.setOnClickListener {
            counterViewModel.incCounter()
        }
    }
}