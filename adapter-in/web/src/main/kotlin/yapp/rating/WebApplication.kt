package yapp.rating

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class WebApplication

fun main(args: Array<String>) {
    runApplication<WebApplication>(*args)
}
