package dorian.codes.koogle.models.pages

import dorian.codes.koogle.models.Page
import org.springframework.data.repository.CrudRepository

interface PageRepository : CrudRepository<Page, Long> {
}