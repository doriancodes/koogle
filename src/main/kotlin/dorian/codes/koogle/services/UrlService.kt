package dorian.codes.koogle.services

import dorian.codes.koogle.models.Url
import dorian.codes.koogle.repositories.UrlRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class UrlService(private val urlRepository: UrlRepository) {
    fun save(url: Url): Url {

        return if (urlRepository.findByMainUrl(url.mainUrl) != null) {
            urlRepository.save(url)

        } else {
            url
        }
    }

}