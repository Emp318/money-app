package com.example.transaction_management

import jakarta.validation.Valid
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestBody

@RestController
@RequestMapping("/api/auth")
class AuthController(private val userService: UserService, private val jwtService: JwtService) {

    @PostMapping("/register")
    fun register(@Valid @RequestBody request: RegisterRequest): User {
        return userService.register(request)
    }

    @PostMapping("/login")
    fun login(@Valid @RequestBody request: LoginRequest): AuthReponse {
        val user = userService.login(request)
        val token = jwtService.generateToken(user)
        return AuthReponse(token)
    }
}