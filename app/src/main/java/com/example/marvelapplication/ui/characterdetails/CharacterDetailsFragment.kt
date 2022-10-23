package com.example.marvelapplication.ui.characterdetails

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.marvelapplication.databinding.FragmentCharacterDetailBinding
import com.example.marvelapplication.vm.characterdetails.CharacterDetailsViewModel
import com.example.marvelapplication.vm.characterdetails.usecase.CharacterDetailsResult
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterDetailsFragment : Fragment() {
    private val logTag: String? = CharacterDetailsFragment::class.simpleName
    private val mCharacterDetailsViewModel: CharacterDetailsViewModel by viewModels()
    private var _binding: FragmentCharacterDetailBinding? = null
    private val binding get() = _binding!!
    private val args by navArgs<CharacterDetailsFragmentArgs>()
    private val adapter = CharacterDetailsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentCharacterDetailBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val recyclerView = binding.recyclerview
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            mCharacterDetailsViewModel.getFlowDataFromServer(args.characterId.toString()).collect {
                Log.d(logTag, "flow data called:$it")
                when (it) {
                    is CharacterDetailsResult.Loading -> {
                        binding.progressLoading.visibility = View.VISIBLE
                    }
                    is CharacterDetailsResult.DismissLoading -> {
                        binding.progressLoading.visibility = View.GONE
                    }
                    is CharacterDetailsResult.Error -> {
                        Toast.makeText(requireContext(), it.msg, Toast.LENGTH_SHORT).show()
                    }
                    is CharacterDetailsResult.NoData -> {
                        Toast.makeText(requireContext(), it.msg, Toast.LENGTH_SHORT).show()
                    }
                    is CharacterDetailsResult.Success -> {
                        adapter.updateData(it.data)
                    }
                }
            }
        }

        return root
    }
}