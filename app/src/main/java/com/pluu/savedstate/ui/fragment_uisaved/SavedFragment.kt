package com.pluu.savedstate.ui.fragment_uisaved

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.pluu.savedstate.R
import com.pluu.savedstate.databinding.SavedFragmentBinding
import com.pluu.savedstate.ui.SavedStateCounterViewModel
import timber.log.Timber

class SavedFragment : Fragment() {

    // Fragment-ktx를 사용하여 ViewModel 지연 생성
    private val counterActivityViewModel: SavedStateCounterViewModel by activityViewModels()
    private val counterFragmentViewModel: SavedStateCounterFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return SavedFragmentBinding.inflate(inflater, container, false).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Timber.d("ViewModel = ${counterActivityViewModel.hashCode()}")

        val activityCounter = view.findViewById<TextView>(R.id.activityCounter)
        val activityFab = view.findViewById<View>(R.id.activityFab)

        counterActivityViewModel.countLiveData.observe(requireActivity(), Observer {
            activityCounter.text = it.toString()
        })
        counterActivityViewModel.extraLiveData.observe(requireActivity(), Observer {
            Timber.d("Activity Extra Value : $it")
        })
        activityFab.setOnClickListener {
            counterActivityViewModel.incCounter()
        }

        val fragmentCounter = view.findViewById<TextView>(R.id.fragmentCounter)
        val fragmentFab = view.findViewById<View>(R.id.fragmentFab)

        counterFragmentViewModel.countLiveData.observe(viewLifecycleOwner, Observer {
            fragmentCounter.text = it.toString()
        })
        counterFragmentViewModel.extraLiveData.observe(viewLifecycleOwner, Observer {
            Timber.d("Fragment Extra Value : $it")
        })
        fragmentFab.setOnClickListener {
            counterFragmentViewModel.incCounter()
        }
    }
}