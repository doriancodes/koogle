package dorian.codes.koogle.config

import dorian.codes.koogle.page.Page
import dorian.codes.koogle.page.PageRepository
import dorian.codes.koogle.crawler.WebCrawler
import org.springframework.boot.ApplicationRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class KoogleInitConfiguration {

    @Bean
    fun databaseInitializer(pageRepository: PageRepository) = ApplicationRunner {
        val wikipedia = Page(url = "https://www.wikipedia.org/", title = "Wikipedia", text = "")
        val google = Page(url = "https://www.google.com/", title = "Google", text = "")

        val craw: WebCrawler = WebCrawler()

        val wikilinks: List<Page> = craw.crawler(wikipedia.url)
        val googleLinks: List<Page> = craw.crawler(google.url)

        wikilinks.map {
            link -> pageRepository.save(link)
        }

        googleLinks.map {
            link -> pageRepository.save(link)
        }

    }
}