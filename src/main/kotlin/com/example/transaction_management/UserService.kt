package com.example.transaction_management

import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository, private val passwordEncoder: PasswordEncoder) {
    fun findByEmail(email: String): User? {
        return userRepository.findByEmail(email).orElse(null)
    }
    fun saveUser(user: User): User {
        val hashed: String = passwordEncoder.encode(user.password)
            ?: throw IllegalStateException("Password encoding failed")
        user.password = hashed
        return userRepository.save(user)
    }
    fun register(request: RegisterRequest): User {
        if (userRepository.findByEmail(request.email).isPresent) {
            throw IllegalArgumentException("Email already registered")
        }
        val hashed: String = passwordEncoder.encode(request.password)
            ?: throw IllegalStateException("Password encoding failed")
        val user = User(email = request.email, password = hashed, role = "USER")
        return userRepository.save(user)
    }
    fun login(request: LoginRequest): User {
        val user = userRepository.findByEmail(request.email)
            .orElseThrow { InvalidCredentialsException() }
        if (!passwordEncoder.matches(request.password, user.password)) {
            throw InvalidCredentialsException()
        }
        return user
    }
}