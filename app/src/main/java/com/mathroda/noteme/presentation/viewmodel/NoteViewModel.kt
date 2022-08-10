package com.mathroda.noteme.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mathroda.noteme.data.entity.Note
import com.mathroda.noteme.domain.repository.NoteMeRepository
import com.mathroda.noteme.presentation.events.NoteMeEvents
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
    private val repository: NoteMeRepository
): ViewModel() {

    val getAllNotes = repository.getALlNotes()

    private val _searchNote = MutableStateFlow<List<Note>>(emptyList())
    val searchNote: StateFlow<List<Note>> = _searchNote

    fun funSearchNote(searchQuery: String) = viewModelScope.launch {
        repository.searchNote(searchQuery).collect {
            _searchNote.emit(it)
        }
    }

    fun onEvent(events: NoteMeEvents) {
        when(events) {
            is NoteMeEvents.AddNote -> {
                viewModelScope.launch {
                    repository.upsertNote(events.note)
                }
            }

            is NoteMeEvents.UpdateNote -> {
                viewModelScope.launch {
                    repository.upsertNote(events.note)
                }
            }

            is NoteMeEvents.DeleteNote -> {
                viewModelScope.launch {
                    repository.deleteNote(events.note)
                }
            }
        }
    }

}