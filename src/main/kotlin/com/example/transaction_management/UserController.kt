package com.example.transaction_management

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.http.HttpStatus

@RestController
class UserController(private val userService: UserService) {

    @PostMapping("/api/users")
    @ResponseStatus(HttpStatus.CREATED)
    fun createUser(@RequestBody user: User): User {
        return userService.saveUser(user)
    }

    @GetMapping("/api/users")
    fun getUserByEmail(@RequestParam email: String): User? {
        return userService.findByEmail(email)
    }
}