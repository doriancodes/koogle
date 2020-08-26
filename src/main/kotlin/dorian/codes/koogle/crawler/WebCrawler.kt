package dorian.codes.koogle.crawler

import dorian.codes.koogle.page.Page
import dorian.codes.koogle.page.PageType
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements


public class WebCrawler {

    fun crawler(url: String): List<Page> {
        print("Fetching %s...", url)
        val doc: Document = Jsoup.connect(url).get()

       return extractPages(doc);
    }

    fun getDocument(url: String): Document {
        return Jsoup.connect(url).get()

    }

    fun crawlerDemo(url: String) {
        print("Fetching %s...", url)
        val doc: Document = Jsoup.connect(url).get()
        val links: Elements = doc.select("a[href]")
        val media: Elements = doc.select("[src]")
        val imports: Elements = doc.select("link[href]")
        print("\nMedia: (%d)", media.size)
        for (src in media) {
            if (src.normalName().equals("img")) print(" * %s: <%s> %sx%s (%s)",
                    src.tagName(), src.attr("abs:src"), src.attr("width"), src.attr("height"),
                    trim(src.attr("alt"), 20)!!) else print(" * %s: <%s>", src.tagName(), src.attr("abs:src"))
        }
        print("\nImports: (%d)", imports.size)
        for (link in imports) {
            print(" * %s <%s> (%s)", link.tagName(), link.attr("abs:href"), link.attr("rel"))
        }
        print("\nLinks: (%d)", links.size)
        for (link in links) {
            print(" * a: <%s>  (%s)", link.attr("abs:href"), trim(link.text(), 35)!!)
        }
    }

    private fun print(msg: String, vararg args: Any) {
        println(String.format(msg, *args))
    }

    private fun trim(s: String, width: Int): String? {
        return if (s.length > width) s.substring(0, width - 1) + "." else s
    }

    fun extractPages(document: Document): List<Page> {
        return document.select("a[href]").map { element ->
            Page(url = element.attr("abs:href"), title = element.attr("title"), text = element.text(), type = PageType.LINK)
        }
    }

}