package com.mathroda.noteme.presentation.events

import com.mathroda.noteme.data.entity.Note

sealed class NoteMeEvents {
    data class AddNote(val note: Note): NoteMeEvents()
    data class UpdateNote(val note: Note): NoteMeEvents()
    data class DeleteNote(val note: Note): NoteMeEvents()
}
