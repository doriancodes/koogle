package dorian.codes.koogle.crawler

import dorian.codes.koogle.models.UrlType
import dorian.codes.koogle.models.Url
import org.jsoup.Jsoup
import org.jsoup.nodes.Document

private val maps: Map<UrlType, String> = mapOf(UrlType.LINK to "a[href]", UrlType.IMPORT to "link[href]")

fun print(msg: String, vararg args: Any) {
    println(String.format(msg, *args))
}

fun trim(s: String, width: Int): String? {
    return if (s.length > width) s.substring(0, width - 1) + "." else s
}


fun extractUrls(document: Document, url: String): List<Url> {

    return maps.flatMap { (key, _) ->
        findUrls(key, document, url)
    }
    /**
    return document.select("a[href]").map { element ->
    Page(url = element.attr("abs:href"), title = element.attr("title"), text = element.text(), type = PageType.LINK)
    }**/
}

private fun findUrls(urlType: UrlType, document: Document, url: String): List<Url> {
    return document.select(maps[urlType]).map { it ->
        Url(mainUrl = url, type = urlType)
    }

}


fun validateUrl(url: String): String {

    val strictPattern = "^(https?)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]".toRegex()
    val strictPattern2 = "^(http?)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]".toRegex()
    val pattern1 = "^[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]".toRegex()

    return when {
        strictPattern.matches(url) -> {
            url
        }
        strictPattern2.matches(url) -> {
            url.replace("http", "https")

        }
        pattern1.matches(url) -> {
            "http://$url"
        }
        else -> {
            ""
        }
    }
}

fun getDocument(url: String): Document {
    return Jsoup.connect(url).get()

}