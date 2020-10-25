package dorian.codes.koogle

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication


@SpringBootApplication
class KoogleApplication

fun main(args: Array<String>) {
	val log: Logger = LoggerFactory.getLogger(KoogleApplication::class.java)
	runApplication<KoogleApplication>(*args)
}
