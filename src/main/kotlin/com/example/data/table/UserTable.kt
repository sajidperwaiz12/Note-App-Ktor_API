package com.example.data.table

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table

object UserTable: Table() {
    val email: Column<String> = varchar("email", 512)
    val name: Column<String> = varchar("name", 512)
    val hashPassword: Column<String> = varchar("hashPassword", 512)

    override val primaryKey: PrimaryKey = PrimaryKey(email)
}