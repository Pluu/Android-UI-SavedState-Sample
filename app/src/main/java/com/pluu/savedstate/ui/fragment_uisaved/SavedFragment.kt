package com.pluu.savedstate.ui.fragment_uisaved

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.pluu.savedstate.databinding.SavedFragmentBinding
import com.pluu.savedstate.ui.SavedStateCounterViewModel
import timber.log.Timber

class SavedFragment : Fragment() {

    // Fragment-ktx를 사용하여 ViewModel 지연 생성
    private val counterActivityViewModel: SavedStateCounterViewModel by activityViewModels()
    private val counterFragmentViewModel: SavedStateCounterFragmentViewModel by viewModels()

    private var _binding: SavedFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = SavedFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Timber.d("ViewModel = ${counterActivityViewModel.hashCode()}")

        counterActivityViewModel.countLiveData.observe(viewLifecycleOwner, Observer {
            binding.activityCounter.text = it.toString()
        })
        counterActivityViewModel.extraLiveData.observe(viewLifecycleOwner, Observer {
            Timber.d("Activity Extra Value : $it")
        })
        binding.activityFab.setOnClickListener {
            counterActivityViewModel.incCounter()
        }

        counterFragmentViewModel.countLiveData.observe(viewLifecycleOwner, Observer {
            binding.fragmentCounter.text = it.toString()
        })
        counterFragmentViewModel.extraLiveData.observe(viewLifecycleOwner, Observer {
            Timber.d("Fragment Extra Value : $it")
        })
        binding.fragmentFab.setOnClickListener {
            counterFragmentViewModel.incCounter()
        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}