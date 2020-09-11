package dorian.codes.koogle.api

import dorian.codes.koogle.page.Page
import dorian.codes.koogle.page.PageRepository
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam


@Controller
@RequestMapping("/")
class HomeController(private val pageRepository: PageRepository) {
    @GetMapping
    fun home(@RequestParam(value = "url", required = false) url: String?, model: Model): String {
        model["title"] = "Koogle Home"
        if (url != null){
            model["pages"] = pageRepository.findAll().filter { page -> page.url == url }.map { it.render() }
            return "results"
        }
        return "home"
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