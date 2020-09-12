package dorian.codes.koogle.pages

import org.springframework.data.repository.CrudRepository

interface PageRepository : CrudRepository<Page, Long> {
}