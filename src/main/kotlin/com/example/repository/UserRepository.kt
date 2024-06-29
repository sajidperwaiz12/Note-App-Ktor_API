package com.example.repository

import com.example.data.dao.UserDao
import com.example.data.model.User
import com.example.data.table.UserTable
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.statements.InsertStatement

class UserRepository : UserDao {
    override suspend fun addUser(user: User) : User? {
        var statement : InsertStatement<Number>? = null
        DatabaseFactory.dbQuery {
            statement = UserTable.insert { ut ->
                ut[UserTable.email] = user.email
                ut[UserTable.hashPassword] = user.hashPassword
                ut[UserTable.name] = user.name
            }
        }
        return rowToUser(statement?.resultedValues?.get(0))
    }

    override suspend fun findUserByEmail(email: String) = DatabaseFactory.dbQuery {
        UserTable.select {
            UserTable.email.eq(email)
        }.map {
            rowToUser(it)
        }.singleOrNull()
    }

    private fun rowToUser(row: ResultRow?) : User? {
        if (row == null) {
            return null
        }
        return User(
            email = row[UserTable.email],
            hashPassword = row[UserTable.hashPassword],
            name = row[UserTable.name]
        )
    }
}