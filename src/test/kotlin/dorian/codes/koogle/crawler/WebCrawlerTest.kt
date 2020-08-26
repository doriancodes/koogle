package dorian.codes.koogle.crawler

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements
import org.junit.jupiter.api.Test

class WebCrawlerTest {

    @Test
    fun shouldParseHTML() {
        val doc: Document = Jsoup.connect("https://en.wikipedia.org/").get()
        println(doc.title())
        val newsHeadlines: Elements = doc.select("#mp-itn b a")
        for (headline in newsHeadlines) {
            println(
                    headline.attr("title")+ " " + headline.absUrl("href"))
        }
    }
}