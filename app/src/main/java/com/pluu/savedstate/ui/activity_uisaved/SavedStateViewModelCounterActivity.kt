package com.pluu.savedstate.ui.activity_uisaved

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.pluu.savedstate.databinding.ActivityCounterBinding
import com.pluu.savedstate.ui.SavedStateCounterViewModel
import timber.log.Timber

class SavedStateViewModelCounterActivity : AppCompatActivity() {

    // Case3. Activity KTX
    // Activity-ktx를 사용하여 ViewModel 지연 생성
    private val counterViewModel: SavedStateCounterViewModel by viewModels()

//    private lateinit var counterViewModel: SavedStateCounterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityCounterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Case1. 기본 케이스
//        counterViewModel = ViewModelProvider(
//            this,
//            SavedStateViewModelFactory(application, this, intent?.extras)
//        ).get(SavedStateCounterViewModel::class.java)

        // Case2. defaultViewModelProviderFactory 사용 케이스
//        counterViewModel = ViewModelProvider(
//            this, defaultViewModelProviderFactory
//        ).get(SavedStateCounterViewModel::class.java)

        Timber.d("ViewModel = ${counterViewModel.hashCode()}")

        counterViewModel.countLiveData.observe(this, Observer {
            binding.counter.text = it.toString()
        })
        counterViewModel.extraLiveData.observe(this, Observer {
            Timber.d("Extra Value : $it")
        })

        binding.fab.setOnClickListener {
            counterViewModel.incCounter()
        }
    }
}