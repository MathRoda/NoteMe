package com.mathroda.noteme.data.repository

import com.mathroda.noteme.data.database.NoteDao
import com.mathroda.noteme.data.entity.Note
import com.mathroda.noteme.domain.repository.NoteMeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NoteMeRepositoryImpl @Inject constructor(
   private val dao: NoteDao) : NoteMeRepository {

    override suspend fun upsertNote(note: Note) {
        return dao.upsertNote(note)
    }

    override suspend fun deleteNote(note: Note) {
        return dao.deleteNote(note)
    }

    override fun getALlNotes(): Flow<List<Note>> {
        return dao.getALlNotes()
    }

    override fun searchNote(searchQuery: String): Flow<List<Note>> {
        return dao.searchNote(searchQuery)
    }
}