package dorian.codes.koogle.services

import dorian.codes.koogle.models.Page
import dorian.codes.koogle.repositories.PageRepository
import org.springframework.stereotype.Service

@Service
class PageService(private val pageRepository: PageRepository) {

    fun findAll(): MutableIterable<Page> = pageRepository.findAll()

    fun saveAll(pages: List<Page>): MutableIterable<Page> = pageRepository.saveAll(pages)

    fun save(page: Page) = pageRepository.save(page)

}