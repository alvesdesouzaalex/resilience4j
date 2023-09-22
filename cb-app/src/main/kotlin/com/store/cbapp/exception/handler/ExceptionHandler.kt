package com.store.cbapp.exception.handler

import feign.FeignException
import mu.KLogging
import org.springframework.http.HttpStatus
import org.springframework.web.ErrorResponse
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ExceptionHandler {

    companion object : KLogging()

    @ExceptionHandler(FeignException.ServiceUnavailable::class)
    fun serviceUnavailable(e: Throwable): ErrorResponse {
        logger.error(e) { "Service unavailable exception" }
        return ErrorResponse.builder(e, HttpStatus.SERVICE_UNAVAILABLE, "Products MS Api unavailable for while").build()
    }

    @ExceptionHandler(FeignException.GatewayTimeout::class)
    fun serviceTimeout(e: Throwable): ErrorResponse {
        logger.error(e) { "Gateway timeout exception" }
        return ErrorResponse.builder(e, HttpStatus.GATEWAY_TIMEOUT, "Products MS Api with unexpected timeout").build()
    }

    @ExceptionHandler(FeignException.NotFound::class)
    fun notFoundException(e: Throwable): ErrorResponse {
        logger.error(e) { "Gateway timeout exception" }
        return ErrorResponse.builder(e, HttpStatus.NOT_FOUND, "NOT FOUND").build()
    }

}
