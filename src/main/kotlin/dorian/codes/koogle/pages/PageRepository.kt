package dorian.codes.koogle.pages

import dorian.codes.koogle.pages.Page
import org.springframework.data.repository.CrudRepository

interface PageRepository : CrudRepository<Page, Long> {
}