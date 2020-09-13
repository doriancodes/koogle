package dorian.codes.koogle.controllers

import dorian.codes.koogle.crawler.WebCrawler
import dorian.codes.koogle.models.Page
import dorian.codes.koogle.services.PageService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping("/search-console")
class SearchConsoleController(private val pageService: PageService) {

    val crawler: WebCrawler = WebCrawler()

    @GetMapping
    fun searchConsole(model: Model): String {
        model["title"] = "Koogle Search Console"
        return "search-console"
    }

    @PostMapping
    fun insert(@RequestParam url: String, model: Model): String {
        model["title"] = "Search Console"
        val childrenPages: List<Page> = crawler.crawler(url).map {
            u -> crawler.getContentOfPage(u.mainUrl)
        }

        pageService.saveAll(childrenPages)
        pageService.save(crawler.getContentOfPage(url)).render()

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