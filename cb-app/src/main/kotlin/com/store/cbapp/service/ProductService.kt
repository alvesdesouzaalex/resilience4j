package com.store.cbapp.service

import com.store.cbapp.client.ProductClient
import com.store.cbapp.exception.ServiceUnavailableException
import com.store.cbapp.model.Product
import feign.FeignException
import io.github.resilience4j.circuitbreaker.CallNotPermittedException
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker
import mu.KLogging
import org.springframework.stereotype.Service


@Service
class ProductService(
    val productClient: ProductClient
) {

    companion object : KLogging()

    @CircuitBreaker(name = "default", fallbackMethod = "getProductFallback")
    fun getProducts() = productClient.getProducts()


    fun getProductFallback(ex: CallNotPermittedException): List<Product> {
        logger.info ("Service took to long to respond")
        throw ServiceUnavailableException(ex.message)
    }

}