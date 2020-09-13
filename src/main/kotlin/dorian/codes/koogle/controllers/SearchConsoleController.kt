package dorian.codes.koogle.api

import dorian.codes.koogle.crawler.WebCrawler
import dorian.codes.koogle.pages.Page
import dorian.codes.koogle.pages.PageRepository
import dorian.codes.koogle.pages.defaultPage
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping("/search-console")
class SearchConsoleController(private val pageRepository: PageRepository) {

    val crawler: WebCrawler = WebCrawler()

    @GetMapping
    fun searchConsole(model: Model): String {
        model["title"] = "Koogle Search Console"
        return "search-console"
    }

    @PostMapping
    fun insert(@RequestParam url: String, model: Model): String {
        model["title"] = "Search Console"
        //val newPages: List<Page> = crawler.crawler(url)
        //println(newPages[0].url)
        //println(newPages[0].type)
        val childrenPages: List<Page> = crawler.crawler(url).map {
            u -> crawler.getContentOfPage(u.mainUrl)
        }

        pageRepository.saveAll(childrenPages)
        pageRepository.save(crawler.getContentOfPage(url)).render()

        /**
        newPages.map {
        page -> pageRepository.save(page)
        }.map { it.render() }*/
        return "search-console"
    }

    fun Page.render() = RenderedPage(
            slug,
            title,
            text,
            url
    )

    data class RenderedPage(
            val slug: String,
            val title: String,
            val text: String,
            val url: String
    )
}