package buckpal

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CleanArchitectureWithKotlinApplication

fun main(args: Array<String>) {
    runApplication<CleanArchitectureWithKotlinApplication>(*args)
}
