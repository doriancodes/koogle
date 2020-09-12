package dorian.codes.koogle

import dorian.codes.koogle.crawler.WebCrawler
import dorian.codes.koogle.crawler.validateUrl
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication


@SpringBootApplication
class KoogleApplication

fun main(args: Array<String>) {
	val log: Logger = LoggerFactory.getLogger(KoogleApplication::class.java)
	runApplication<KoogleApplication>(*args)

	val wcrawler: WebCrawler = WebCrawler()

	//wcrawler.crawlerDemo("https://www.wikipedia.org/")

	validateUrl("http://www.wikipedia.org/")
}
