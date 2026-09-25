package com.example.transaction_management

class TransactionAccessDeniedException(id: Long) : RuntimeException("You do not have access to transaction with id $id")