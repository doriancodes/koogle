package dorian.codes.koogle.pages

import org.springframework.stereotype.Service

@Service
class PageService(private val pageRepository: PageRepository) {

    fun findAll(): MutableIterable<Page> = pageRepository.findAll()

    fun saveAll(pages: List<Page>): MutableIterable<Page> = pageRepository.saveAll(pages)

    fun save(page: Page) = pageRepository.save(page)

}