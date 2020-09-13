package dorian.codes.koogle.repositories

import dorian.codes.koogle.models.Url
import org.springframework.data.repository.CrudRepository

interface UrlRepository : CrudRepository<Url, Long> {
    fun findBySlug(slug: String): List<Url> = findAll().filter{ it.slug == slug }

    fun findByMainUrl(mainUrl: String): Url? = findAll().find { it.mainUrl == mainUrl}
}