package com.example.transaction_management
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import jakarta.persistence.ManyToOne
import jakarta.persistence.JoinColumn
import jakarta.validation.constraints.Positive
import jakarta.validation.constraints.NotBlank

@Entity
@Table(name = "transactions")
data class Transaction (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @field:Positive
    var amount: Double,

    @field:NotBlank
    var type: String,

    @field:NotBlank
    var category: String,

    @field:NotBlank
    var description: String,

    @ManyToOne
    @JoinColumn(name = "user_id")
    var owner: User? = null


)