package com.example.transaction_management
import org.springframework.stereotype.Service
import org.springframework.security.core.context.SecurityContextHolder

@Service
class TransactionService(
    private val transactionRepository: TransactionRepository,
    private val userService: UserService
) {

    fun getTransactions(): List<Transaction> {
        return transactionRepository.findAll()
    }

    fun createTransaction(transaction: Transaction): Transaction {
        val authentication = SecurityContextHolder.getContext().authentication
            ?: throw IllegalStateException("No authenticated user found")
        val email = authentication.principal as String
        val owner = userService.findByEmail(email)
        transaction.owner = owner
        return transactionRepository.save(transaction)
    }

    fun getTransactionById(id: Long): Transaction {
        return transactionRepository.findById(id).orElseThrow{ TransactionNotFoundException(id) }
    }

    fun updateTransaction(id: Long, updatedTransaction: Transaction): Transaction {
        val existingTransaction = transactionRepository.findById(id).orElseThrow { TransactionNotFoundException(id) }
        existingTransaction.amount = updatedTransaction.amount
        existingTransaction.type = updatedTransaction.type
        existingTransaction.category = updatedTransaction.category
        existingTransaction.description = updatedTransaction.description

        return transactionRepository.save(existingTransaction)
    }

    fun deleteTransaction(id: Long): Boolean {
        val transaction = transactionRepository.findById(id).orElseThrow { TransactionNotFoundException(id) }
        transactionRepository.deleteById(id)
        return true
    }
}