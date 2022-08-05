package com.mathroda.noteme.presentation.note_screen

import android.opengl.Visibility
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.NavArgs
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.mathroda.noteme.R
import com.mathroda.noteme.data.entity.Note
import com.mathroda.noteme.databinding.FragmentNoteBinding
import com.mathroda.noteme.presentation.events.NoteMeEvents
import com.mathroda.noteme.presentation.viewmodel.NoteViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NoteFragment : Fragment() {

    private lateinit var binding: FragmentNoteBinding
    private val args by navArgs<NoteFragmentArgs>()
    private val viewModel: NoteViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentNoteBinding.inflate(inflater, container, false)

        args.note?.let {
            binding.apply {
                edTitle.setText(it.title)
                note.setText(it.note)
            }

            binding.delete.visibility = View.VISIBLE
        }

        binding.apply {
            accept.setOnClickListener {
                val id = args.note?.id ?: 0
                val title = edTitle.text.toString()
                val note = note.text.toString()

                Note(id, title, note).also {
                    if (title.isEmpty() && note.isEmpty()) {
                        Toast.makeText(context, "Fill all the fields!", Toast.LENGTH_SHORT)
                            .show()
                        return@setOnClickListener
                    }

                    viewModel.onEvent(NoteMeEvents.AddNote(it))
                    findNavController().navigateUp()
                }
            }
        }

        binding.apply {
            delete.setOnClickListener {
                val id = args.note!!.id
                val title = edTitle.text.toString()
                val note = note.text.toString()

                Note(id, title, note).also {
                    viewModel.onEvent(NoteMeEvents.DeleteNote(it))
                    findNavController().navigateUp()
                }
            }
        }

        binding.arrowBack.setOnClickListener {
            findNavController().popBackStack()
        }


        return binding.root
    }
}