package com.example.transaction_management

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import jakarta.validation.constraints.Pattern

@Entity
@Table(name = "users")
class User (

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null,
    var email: String,
    var password: String,

    @field:Pattern(regexp = "USER|ADMIN", message = "Role must be USER or ADMIN")
    var role: String = "USER"
)