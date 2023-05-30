package com.yapp.bol

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(scanBasePackages = ["yapp.rating"])
open class WebApplication

fun main(args: Array<String>) {
    runApplication<WebApplication>(*args)
}
