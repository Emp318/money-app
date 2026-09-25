package com.example.transaction_management
import org.springframework.data.jpa.repository.JpaRepository

interface TransactionRepository : JpaRepository<Transaction, Long> {
    fun findByOwnerId(ownerId: Long): List<Transaction>
}
interface TransactionRepository : JpaRepository<Transaction,Long>