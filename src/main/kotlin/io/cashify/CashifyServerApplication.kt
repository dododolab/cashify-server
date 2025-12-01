package io.cashify

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CashifyServerApplication

fun main(args: Array<String>) {
    runApplication<CashifyServerApplication>(*args)
}
