package dorian.codes.koogle.api

import dorian.codes.koogle.pages.Page
import dorian.codes.koogle.pages.PageRepository
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
        if (url != null) {
            //val validatedUrl: String = validateUrl(url)
            model["pages"] = pageRepository.findAll().filter { page -> page.slug == url.extract() }.map { it.render() }
            return "results"
        }
        return "home"
    }

    private fun String.extract() = split(".").filterNot { s -> stopWords.contains(s) }[0]
    private val stopWords: Set<String> = setOf("www", "com", "https://", "http://", "https://www", "http://www")

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