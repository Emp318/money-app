package com.example.transaction_management

import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import jakarta.validation.Valid

@RestController
class TransactionController (private val transactionService: TransactionService) {


    @GetMapping("/api/transactions")
    fun getTransactions(): List<Transaction> {
        return transactionService.getTransactions()
    }

    @PostMapping("/api/transactions")
    fun createTransaction(@Valid @RequestBody transaction: Transaction): Transaction {
        return transactionService.createTransaction(transaction)
    }

    @GetMapping("/api/transactions/{id}")
    fun getTransactionById(@PathVariable(value = "id") id: Long): Transaction? {
        return transactionService.getTransactionById(id)
    }

    @PutMapping("/api/transactions/{id}")
    fun updateTransactionById(@PathVariable(value = "id") id: Long, @Valid @RequestBody transaction: Transaction): Transaction? {
        return transactionService.updateTransaction(id, transaction)
    }

    @DeleteMapping("api/transactions/{id}")
    fun deleteTransaction(@PathVariable id: Long) {
       transactionService.deleteTransaction(id)
    }
}