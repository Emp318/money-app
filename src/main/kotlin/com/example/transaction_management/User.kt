package com.example.transaction_management

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class User (

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null,
    var email: String,
    var password: String,
    var role: String = "USER"
)