package com.example.transaction_management

class TransactionNotFoundException(id: Long) : RuntimeException("Transaction with id $id not found")