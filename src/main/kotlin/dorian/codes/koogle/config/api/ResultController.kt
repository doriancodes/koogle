package dorian.codes.koogle.config.api

import dorian.codes.koogle.page.Page
import dorian.codes.koogle.page.PageRepository
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping

@Controller
class ResultController(private val pageRepository: PageRepository) {
    @GetMapping("/results")
    fun results(model: Model): String {
        model["title"] = "Results"
        model["pages"] = pageRepository.findAll().map { it.render() }
        return "results"
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