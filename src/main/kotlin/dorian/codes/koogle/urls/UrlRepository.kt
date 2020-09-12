package dorian.codes.koogle.urls

import org.springframework.data.repository.CrudRepository

interface UrlRepository : CrudRepository<Url, Long> {
}