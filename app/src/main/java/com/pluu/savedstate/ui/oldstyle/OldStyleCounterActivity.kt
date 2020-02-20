package com.pluu.savedstate.ui.oldstyle

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.pluu.savedstate.databinding.ActivityCounterBinding
import timber.log.Timber

class OldStyleCounterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityCounterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.extras
        val case_1 = bundle?.getInt("default_case_1")
        val case_2 = bundle?.getString("default_case_2")
        val case_3 =bundle?.getSerializable("default_case_3") as Array<Int>

        Timber.d("case1 : $case_1")
        Timber.d("case2 : $case_2")
        Timber.d("case3 : $case_3")
    }
}