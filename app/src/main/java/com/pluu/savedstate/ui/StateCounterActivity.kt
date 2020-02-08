package com.pluu.savedstate.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.pluu.savedstate.databinding.ActivityCounterBinding
import timber.log.Timber

class StateCounterActivity : AppCompatActivity() {

    private var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityCounterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState != null) {
            count = savedInstanceState.getInt("count")
            Timber.d("Restore Count = $count")
            binding.counter.text = count.toString()
        }

        binding.fab.setOnClickListener {
            ++count
            Timber.d("Click => Count = $count")
            binding.counter.text = count.toString()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt("count", count)
        Timber.d("Save Count = $count")
        super.onSaveInstanceState(outState)
    }
}
