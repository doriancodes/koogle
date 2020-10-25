package dorian.codes.koogle.config

import dorian.codes.koogle.pages.Page
import dorian.codes.koogle.pages.PageRepository
import dorian.codes.koogle.crawler.WebCrawler
import dorian.codes.koogle.searchconsole.Url
import dorian.codes.koogle.searchconsole.UrlService

import org.springframework.boot.ApplicationRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class KoogleInitConfiguration {

    @Bean
    fun databaseInitializer(urlService: UrlService, pageRepository: PageRepository) = ApplicationRunner {
        val wikipedia = Page(url = "https://www.wikipedia.org/", title = "Wikipedia", text = "")
        val google = Page(url = "https://www.google.com/", title = "Google", text = "")

        val craw: WebCrawler = WebCrawler()

        val wikilinks: List<Url> = craw.crawler(wikipedia.url)
        val googleLinks: List<Url> = craw.crawler(google.url)

        val wikiUrls: List<Url> = wikilinks.map { link ->
            urlService.save(link)
        }

        val googleUrls = googleLinks.map { link ->
            urlService.save(link)
        }

        wikiUrls.forEach { url ->
            pageRepository.save(craw.getContentOfPage(url.mainUrl))
        }

        googleUrls.forEach { url ->
            pageRepository.save(craw.getContentOfPage(url.mainUrl))
        }

    }
}