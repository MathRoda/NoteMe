package com.mathroda.noteme.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mathroda.noteme.core.util.Constants
import com.mathroda.noteme.data.entity.Note

@Database(
    entities = [Note::class],
    version = 1,
    exportSchema = false
)
abstract class NoteMeDatabase: RoomDatabase() {

    abstract val dao: NoteDao

    companion object {
        const val DATABASE_NAME = Constants.DB_NAME
    }
}