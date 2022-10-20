package com.example.marvelapplication.ui.characters

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.marvelapplication.R
import com.example.marvelapplication.databinding.FragmentCharactersListBinding
import com.example.marvelapplication.vm.characters.CharactersViewModel
import com.example.marvelapplication.vm.characters.usecase.CharactersResult
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharactersListFragment : Fragment() {

    private val mCharactersViewModel: CharactersViewModel by viewModels()
    private var _binding: FragmentCharactersListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentCharactersListBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // init swipe to refresh
        binding.refresh.setOnRefreshListener {
            mCharactersViewModel.getCharactersFromServer()
        }

        mCharactersViewModel.liveCharacters.observe(viewLifecycleOwner) {
            when (it) {
                is CharactersResult.Success -> {
                    binding.refresh.isRefreshing = false
                    val data = it.data
                    Log.d("TEST", Gson().toJson(data))
                }
                is CharactersResult.Error -> {
                    binding.refresh.isRefreshing = false
                    Toast.makeText(requireContext(), it.msg, Toast.LENGTH_LONG).show()
                }
                is CharactersResult.Loading -> {
                    binding.refresh.isRefreshing = true
                }
                is CharactersResult.NoData -> {
                    binding.refresh.isRefreshing = false
                    Toast.makeText(
                        requireContext(),
                        getString(R.string.no_data_received),
                        Toast.LENGTH_LONG
                    ).show()
                }
                else -> {
                    binding.refresh.isRefreshing = false
                }
            }
        }

        return root
    }
}