package com.example.transaction_management
import org.springframework.stereotype.Service
import org.springframework.security.core.context.SecurityContextHolder

@Service
class TransactionService(
    private val transactionRepository: TransactionRepository,
    private val userService: UserService
) {

    fun getTransactions(): List<Transaction> {
        val currentUser = getCurrentUser()
        return if (currentUser.role == "ADMIN") {
            transactionRepository.findAll()
        } else {
            transactionRepository.findByOwnerId(currentUser.id!!)
        }
    }

    fun createTransaction(transaction: Transaction): Transaction {
        val authentication = SecurityContextHolder.getContext().authentication
            ?: throw IllegalStateException("No authenticated user found")
        val email = authentication.principal as String
        val owner = userService.findByEmail(email)
        transaction.owner = owner
        return transactionRepository.save(transaction)
    }

    private fun getCurrentUser(): User {
        val authentication = SecurityContextHolder.getContext().authentication
            ?: throw IllegalStateException("No authenticated user found")
        val email = authentication.principal as String
        return userService.findByEmail(email)
            ?: throw IllegalStateException("Authenticated user not found in database")
    }

    fun getTransactionById(id: Long): Transaction {
        val transaction = transactionRepository.findById(id).orElseThrow { TransactionNotFoundException(id) }
        val currentUser = getCurrentUser()
        if (currentUser.role != "ADMIN" && transaction.owner?.id != currentUser.id) {
            throw TransactionAccessDeniedException(id)
        }
        return transaction
    }

    fun updateTransaction(id: Long, updatedTransaction: Transaction): Transaction {
        val existingTransaction = transactionRepository.findById(id).orElseThrow { TransactionNotFoundException(id) }
        val currentUser = getCurrentUser()
        if (currentUser.role != "ADMIN" && existingTransaction.owner?.id != currentUser.id) {
            throw TransactionAccessDeniedException(id)
        }
        existingTransaction.amount = updatedTransaction.amount
        existingTransaction.type = updatedTransaction.type
        existingTransaction.category = updatedTransaction.category
        existingTransaction.description = updatedTransaction.description

        return transactionRepository.save(existingTransaction)
    }

    fun deleteTransaction(id: Long): Boolean {
        val transaction = transactionRepository.findById(id).orElseThrow { TransactionNotFoundException(id) }
        val currentUser = getCurrentUser()
        if (currentUser.role != "ADMIN" && transaction.owner?.id != currentUser.id) {
            throw TransactionAccessDeniedException(id)
        }
        transactionRepository.deleteById(id)
        return true
    }
}