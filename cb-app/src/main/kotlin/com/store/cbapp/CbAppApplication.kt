package com.store.cbapp

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@EnableFeignClients
@SpringBootApplication
class CbAppApplication

fun main(args: Array<String>) {
	runApplication<CbAppApplication>(*args)
}
