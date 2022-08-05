package com.mathroda.noteme.presentation.main_screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.mathroda.noteme.R
import com.mathroda.noteme.databinding.FragmentNoteListBinding
import com.mathroda.noteme.presentation.main_screen.adapter.NotesAdapter
import com.mathroda.noteme.presentation.viewmodel.NoteViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NoteListFragment : Fragment() {

    private lateinit var binding: FragmentNoteListBinding
    private val viewModel: NoteViewModel by viewModels()
    private lateinit var notesAdapter: NotesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =  FragmentNoteListBinding.inflate(inflater, container, false)
        notesAdapter = NotesAdapter()

        onAddNoteClick()
        setupNotesRecyclerView()

        lifecycleScope.launchWhenStarted {
            viewModel.getAllNotes.collect {
                notesAdapter.differ.submitList(it)
            }
        }

        lifecycleScope.launchWhenStarted {
            viewModel.searchNote.collect {
                notesAdapter.differ.submitList(it)
            }
        }

        binding.searchBox.addTextChangedListener {
            viewModel.funSearchNote(it.toString().trim())
        }

        onNoteClick()

        return binding.root
    }

    private fun onNoteClick() {
        notesAdapter.onClick = {
            val bundle = Bundle().apply {
                putParcelable("note", it)
            }
            findNavController().navigate(R.id.action_noteListFragment_to_noteFragment, bundle)
        }
    }

    private fun setupNotesRecyclerView() {
        binding.rvNotes.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = notesAdapter
        }
    }

    private fun onAddNoteClick() {
        binding.btnAdd.setOnClickListener {
            findNavController().navigate(R.id.action_noteListFragment_to_noteFragment)
        }
    }
}