package bcg.dv.bootcamp

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class BootcampApplication

fun main(args: Array<String>) {
	runApplication<BootcampApplication>(*args)
}
