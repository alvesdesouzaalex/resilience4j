package com.store.cbapp.client

import com.store.cbapp.model.Product
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.GetMapping

@Component
@FeignClient(name = "products", url = "http://127.0.0.1:5000")
interface ProductClient {

    @GetMapping("/products", consumes = ["application/json"])
    fun getProducts(
    ): List<Product>
}