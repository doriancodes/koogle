package dorian.codes.koogle.crawler

import dorian.codes.koogle.pages.Page
import dorian.codes.koogle.pages.PageRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.data.repository.findByIdOrNull

@DataJpaTest
class PageRepositoryTest @Autowired constructor(
        val entityManager: TestEntityManager,
        val pageRepository: PageRepository
) {
    @Test
    fun `When findByIdOrNull then return Article`() {
        val wikipedia = Page(url = "https://www.wikipedia.org/", title = "Wikipedia", text = "<html>\n</html>")
        entityManager.persist(wikipedia)
        //entityManager.flush()
        val found = pageRepository.findByIdOrNull(wikipedia.id!!)
        assertThat(found).isEqualTo(wikipedia)
    }
}