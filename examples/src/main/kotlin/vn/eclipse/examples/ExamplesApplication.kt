package vn.eclipse.examples

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ExamplesApplication

fun main(args: Array<String>) {
    runApplication<ExamplesApplication>(*args)
}
