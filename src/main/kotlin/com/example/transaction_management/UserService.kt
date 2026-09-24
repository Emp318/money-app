package com.example.transaction_management

import org.springframework.stereotype.Service
import java.util.Optional

@Service
class UserService(private val userRepository: UserRepository) {
    fun findByEmail(email: String): User? {
        return userRepository.findByEmail(email).orElse(null)
    }
    fun saveUser(user: User): User {
        return userRepository.save(user)
    }
}