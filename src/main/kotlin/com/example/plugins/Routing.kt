package com.example.plugins

import com.example.authentication.JwtService
import com.example.authentication.hash
import com.example.data.model.User
import com.example.repository.DatabaseFactory
import com.example.repository.NoteRepository
import com.example.repository.UserRepository
import com.example.routes.NoteRoutes
import com.example.routes.UserRoutes
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    DatabaseFactory.init()
    val db1 = UserRepository()
    val db2 = NoteRepository()
    val jwtService = JwtService()
    val hashFunction = {
            s: String -> hash(s)
    }

    routing {

        get("/") {
            call.respondText("Hello World!")
        }

        UserRoutes(db1, jwtService, hashFunction)
        NoteRoutes(db2, hashFunction)

    }
}
