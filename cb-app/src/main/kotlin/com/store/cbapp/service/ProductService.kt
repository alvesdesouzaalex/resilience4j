package com.store.cbapp.service

import com.store.cbapp.client.ProductClient
import com.store.cbapp.exception.ServiceUnavailableException
import com.store.cbapp.model.Brand
import com.store.cbapp.model.Product
import feign.FeignException.ServiceUnavailable
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


    fun getProductFallback(ex: ServiceUnavailable): List<Product> {
        logger.info("ServiceUnavailableException - Service took to long to respond")
        throw ServiceUnavailableException("Service took to long to respond")
    }

    fun getProductFallback(ex: CallNotPermittedException): List<Product> {
        logger.info("CallNotPermittedException - Service took to long to respond")
        val product = Product("123", "Product Fallback", Brand("Olympicus", "running"), 0.0, 0)
        return listOf(product)
    }

}