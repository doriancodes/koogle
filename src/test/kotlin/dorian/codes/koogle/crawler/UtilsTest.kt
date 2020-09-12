package dorian.codes.koogle.crawler

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements
import org.junit.jupiter.api.Test

class UtilsTest {
    @Test
    fun shouldValidate() {
        val goodUrl = "https://www.wikipedia.org/"
        val bad1 = "http://www.wikipedia.org/"

        assert(goodUrl == validateUrl(goodUrl))
    }
}