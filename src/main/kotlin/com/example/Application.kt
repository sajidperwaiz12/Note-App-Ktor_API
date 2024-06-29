package com.example

import com.example.authentication.JwtService
import com.example.authentication.hash
import com.example.plugins.*
import com.example.repository.DatabaseFactory
import com.example.repository.UserRepository
import io.ktor.server.application.*
import io.ktor.server.locations.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    install(Locations)
    configureSerialization()
    configureSecurity()
    configureRouting()
}
