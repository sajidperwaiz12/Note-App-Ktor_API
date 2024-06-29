package com.example.data.table

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table

object NoteTable : Table() {
    val id: Column<Int> = integer("noteId").autoIncrement()
    val userEmail: Column<String> = varchar("userEmail", 512).references(UserTable.email)
    val noteTitle: Column<String> = text("noteTitle")
    val description: Column<String> = text("description")
    val date: Column<Long> = long("date")

    override val primaryKey: PrimaryKey = PrimaryKey(id)
}