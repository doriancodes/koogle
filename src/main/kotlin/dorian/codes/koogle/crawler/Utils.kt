package dorian.codes.koogle.crawler

import dorian.codes.koogle.page.Page
import dorian.codes.koogle.page.PageType
import org.jsoup.Jsoup
import org.jsoup.nodes.Document

private val maps: Map<PageType, String> = mapOf(PageType.LINK to "a[href]", PageType.IMPORT to "link[href]")

fun print(msg: String, vararg args: Any) {
    println(String.format(msg, *args))
}

fun trim(s: String, width: Int): String? {
    return if (s.length > width) s.substring(0, width - 1) + "." else s
}


fun extractPages(document: Document): List<Page> {

    return maps.flatMap { (key, _) ->
        renderPage(key, document)
    }
    /**
    return document.select("a[href]").map { element ->
    Page(url = element.attr("abs:href"), title = element.attr("title"), text = element.text(), type = PageType.LINK)
    }**/
}

private fun renderPage(pageType: PageType, document: Document): List<Page> {
    return if (pageType == PageType.LINK) {

        document.select(maps[pageType]).map { element ->
            Page(url = element.attr("abs:href"), title = element.attr("title"), text = element.text(), type = pageType)
        }
    } else {
        document.select(maps[pageType]).map { element ->
            Page(url = element.attr("abs:href"), title = element.tagName(), text = element.attr("rel"), type = pageType)

        }
    }
}


fun getDocument(url: String): Document {
    return Jsoup.connect(url).get()

}