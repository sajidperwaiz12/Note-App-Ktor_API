package com.example.data.dao

import com.example.data.model.User

interface UserDao {
    suspend fun addUser(user: User) : User?
    suspend fun findUserByEmail(email: String) : User?
}