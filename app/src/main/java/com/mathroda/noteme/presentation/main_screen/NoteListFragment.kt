package com.mathroda.noteme.presentation.main_screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.mathroda.noteme.R
import com.mathroda.noteme.databinding.FragmentNoteListBinding
import com.mathroda.noteme.presentation.main_screen.adapter.NotesAdapter
import com.mathroda.noteme.presentation.viewmodel.NoteViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NoteListFragment : Fragment() {

    private lateinit var binding: FragmentNoteListBinding
    // private val viewModel: NoteViewModel by viewModels()
    private lateinit var notesAdapter: NotesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =  FragmentNoteListBinding.inflate(inflater, container, false)
        notesAdapter = NotesAdapter()

        onAddNoteClick()
        return binding.root
    }

    private fun onAddNoteClick() {
        binding.btnAdd.setOnClickListener {
            findNavController().navigate(R.id.action_noteListFragment_to_noteFragment)
        }
    }
}