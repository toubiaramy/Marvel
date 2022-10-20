package com.example.marvelapplication.ui.characters

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelapplication.R
import com.example.marvelapplication.data.characters.Character
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
    private val adapter = CharactersAdapter()

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

        // init recycle view
        val recyclerView = binding.recyclerview
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        val itemTouchHelper = ItemTouchHelper(simpleItemTouchCallback)
        itemTouchHelper.attachToRecyclerView(recyclerView)

        mCharactersViewModel.liveCharacters.observe(viewLifecycleOwner) {
            when (it) {
                is CharactersResult.Success -> {
                    binding.refresh.isRefreshing = false
                    Log.d("TEST: ", Gson().toJson(it.data.data.results))
                    adapter.setData(it.data.data.results)
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

    // listener for swipe on recyle view item
    private var simpleItemTouchCallback: ItemTouchHelper.SimpleCallback = object :
        ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
                or ItemTouchHelper.DOWN or ItemTouchHelper.UP
        ) {
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            return false
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, swipeDir: Int) {
            val position = viewHolder.adapterPosition
            onItemDeleted(adapter.getItem(position), position)
        }
    }

    // notify user deletion
    fun onItemDeleted(char: Character, position: Int) {
        val dialogBuilder = AlertDialog.Builder(requireContext())
        dialogBuilder.setPositiveButton(getString(R.string.yes)) { _, _ ->
            // mUserViewModel.deleteUser(user)
            adapter.notifyItemChanged(position)
            Toast.makeText(
                requireContext(),
                "${char.name} Successfully Deleted!",
                Toast.LENGTH_SHORT
            ).show()
        }

        dialogBuilder.setNegativeButton(getString(R.string.no)) { _, _ ->
            adapter.notifyItemChanged(position)
        }
        dialogBuilder.setTitle("Delete ${char.name}?")
        dialogBuilder.create().show()
    }
}