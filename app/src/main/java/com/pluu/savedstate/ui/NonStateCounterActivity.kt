package com.pluu.savedstate.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.pluu.savedstate.databinding.ActivityCounterBinding
import timber.log.Timber

class NonStateCounterActivity : AppCompatActivity() {

    private var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityCounterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.fab.setOnClickListener {
            ++count
            Timber.d("Click => Count = $count")
            binding.counter.text = count.toString()
        }
    }
}
