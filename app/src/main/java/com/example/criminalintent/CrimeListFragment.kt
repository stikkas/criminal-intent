package com.example.criminalintent

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.criminalintent.databinding.FragmentCrimeListBinding
import kotlinx.coroutines.launch

private const val TAG = "CrimeListFragment"

class CrimeListFragment : Fragment() {
    private val viewModel: CrimeListViewModel by viewModels()
    private var binding: FragmentCrimeListBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                val crimes = viewModel.loadCrimes()
                binding?.crimeRecyclerView?.adapter = CrimeListAdapter(crimes)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentCrimeListBinding.inflate(layoutInflater, container, false).let {
        it.crimeRecyclerView.layoutManager = LinearLayoutManager(context)
        binding = it
        it.root
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}