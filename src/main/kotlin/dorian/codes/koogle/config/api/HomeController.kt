package dorian.codes.koogle.config.api

import dorian.codes.koogle.page.Page
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping


@Controller
class HomeController {
    @GetMapping("/")
    fun home(model: Model): String {
        model["title"] = "Home"
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