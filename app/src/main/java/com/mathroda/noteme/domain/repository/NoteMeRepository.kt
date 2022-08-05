package com.mathroda.noteme.domain.repository

import com.mathroda.noteme.data.entity.Note
import kotlinx.coroutines.flow.Flow

interface NoteMeRepository {

    /**
     * This function do both inserting and updating a note
     */
    suspend fun upsertNote(note: Note)

    suspend fun deleteNote(note: Note)

    fun getALlNotes(): Flow<List<Note>>

    fun searchNote(searchQuery: String): Flow<List<Note>>
}