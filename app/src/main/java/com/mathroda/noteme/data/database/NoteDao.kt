package com.mathroda.noteme.data.database

import androidx.room.*
import com.mathroda.noteme.data.entity.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
    /**
     * This function do both inserting and updating a note
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)

    @Query("SELECT * FROM note ORDER BY id DESC")
    fun getALlNotes(): Flow<List<Note>>

    @Query("SELECT * FROM note WHERE title LIKE '%'||:searchQuery||'%'")
    fun searchNote(searchQuery: String): Flow<List<Note>>
}