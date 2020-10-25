package dorian.codes.koogle.searchconsole

import org.springframework.stereotype.Service

@Service
class UrlService(private val urlRepository: UrlRepository) {
    fun save(url: Url): Url {

        return if (urlRepository.findByMainUrl(url.mainUrl) == null) {
            urlRepository.save(url)

        } else {
            url
        }
    }

}