package dorian.codes.koogle.searchconsole

import dorian.codes.koogle.crawler.WebCrawler
import dorian.codes.koogle.pages.Page
import dorian.codes.koogle.pages.UrlType
import dorian.codes.koogle.pages.PageService
import org.springframework.stereotype.Service

@Service
class SearchConsoleService(
        private val urlService: UrlService,
        private val pageService: PageService,
        private val webCrawler: WebCrawler
) {

    fun insertNew(urlString: String): Page {



        val url: Url = Url(urlString, UrlType.LINK) //TODO check how to get the type dynamically

        urlService.save(url)

        val childrenUrls: List<Url> = webCrawler.crawler(url.mainUrl)

        val childrenPages: List<Page> = childrenUrls.map { it -> urlService.save(it) }.map {
            u -> webCrawler.getContentOfPage(u.mainUrl)
        }

        pageService.saveAll(childrenPages)

        return pageService.save(webCrawler.getContentOfPage(url.mainUrl))
    }

}