package com.example.data.dao

import com.example.data.model.Note

interface NoteDao {

    suspend fun addNote(note: Note, email: String)
    suspend fun getAllNotes(email: String) : List<Note>
    suspend fun updateNote(note: Note, email: String)
    suspend fun deleteNote(id: Int, email: String)
}