package com.pluu.savedstate.ui.fragment_uisaved

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.commit
import com.pluu.savedstate.R
import com.pluu.savedstate.databinding.SavedActivityBinding

class SavedFragmentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = SavedActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                replace(
                    R.id.fragmentContainer,
                    SavedFragment::class.java,
                    bundleOf("fragment_case" to "P1")
                )
            }
        }
    }
}