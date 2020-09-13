package dorian.codes.koogle.crawler

import dorian.codes.koogle.models.Page
import dorian.codes.koogle.models.Url
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements
import org.springframework.stereotype.Component


@Component
class WebCrawler {

    fun crawler(url: String): List<Url> {
        print("Fetching %s...", url)
        val validatedUrl: String = validateUrl(url)
        val doc: Document = Jsoup.connect(validatedUrl).get()

        return extractUrls(doc, url);
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

    fun getContentOfPage(url: String): Page {
        val validatedUrl: String = validateUrl(url)
        val doc: Document = Jsoup.connect(validatedUrl).get()
        return Page(url, doc.title(), doc.text().substring(0, 250))
    }


}