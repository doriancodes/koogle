package dorian.codes.koogle.models.urls

import org.springframework.data.repository.CrudRepository

interface UrlRepository : CrudRepository<Url, Long> {
}