package dorian.codes.koogle.searchconsole

import dorian.codes.koogle.crawler.WebCrawler
import dorian.codes.koogle.pages.Page
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping("/search-console")
class SearchConsoleController(private val searchConsoleService: SearchConsoleService) {

    val webCrawler: WebCrawler = WebCrawler()

    @GetMapping
    fun searchConsole(model: Model): String {
        model["title"] = "Koogle Search Console"
        return "search-console"
    }

    @PostMapping
    fun insert(@RequestParam url: String, model: Model): String {
        model["title"] = "Search Console"
                searchConsoleService.insertNew(url)

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