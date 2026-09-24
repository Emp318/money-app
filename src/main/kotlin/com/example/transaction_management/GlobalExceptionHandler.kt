package com.example.transaction_management

import org.springframework.http.HttpStatus
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(TransactionNotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handleTransactionNotFoundException(exception: TransactionNotFoundException): Map<String, String> {
        return mapOf("error" to exception.message.orEmpty())
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleValidationErrors(exception: MethodArgumentNotValidException): Map<String, String> {
        return exception.bindingResult.fieldErrors.associate { it.field to (it.defaultMessage ?: "Invalid value") }
    }
}