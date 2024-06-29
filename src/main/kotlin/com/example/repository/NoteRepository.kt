package com.example.repository

import com.example.data.dao.NoteDao
import com.example.data.model.Note
import com.example.data.model.User
import com.example.data.table.NoteTable
import com.example.data.table.UserTable
import org.jetbrains.exposed.sql.*

class NoteRepository : NoteDao {
    override suspend fun addNote(note: Note, email: String) {
        DatabaseFactory.dbQuery {
            NoteTable.insert { nt ->
                nt[NoteTable.userEmail] = email
                nt[NoteTable.noteTitle] = note.noteTitle
                nt[NoteTable.description] = note.description
                nt[NoteTable.date] = note.date
            }
        }
    }

    override suspend fun getAllNotes(email: String): List<Note> = DatabaseFactory.dbQuery {
        NoteTable.select {
            NoteTable.userEmail.eq(email)
        }.mapNotNull {
            rowToNote(it)
        }
    }

    override suspend fun updateNote(note: Note, email: String) {
        DatabaseFactory.dbQuery {
            NoteTable.update(
                where = {
                    NoteTable.userEmail.eq(email) and  NoteTable.id.eq(note.noteId)
                }
            ) { nt ->
                nt[NoteTable.noteTitle] = note.noteTitle
                nt[NoteTable.description] = note.description
                nt[NoteTable.date] = note.date
            }
        }
    }

    override suspend fun deleteNote(id: Int, email: String) {
        DatabaseFactory.dbQuery {
            NoteTable.deleteWhere {
                NoteTable.userEmail.eq(email) and NoteTable.id.eq(id)
            }
        }
    }


    private fun rowToNote(row: ResultRow?) : Note? {
        if (row == null) {
            return null
        }
        return Note(
            noteId = row[NoteTable.id],
            noteTitle = row[NoteTable.noteTitle],
            description = row[NoteTable.description],
            date = row[NoteTable.date]
        )
    }

}