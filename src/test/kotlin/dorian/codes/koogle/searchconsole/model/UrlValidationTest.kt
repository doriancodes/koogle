package dorian.codes.koogle.searchconsole.model

import dorian.codes.koogle.searchconsole.Url
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class UrlValidationTest {

    @Test
    fun validateUrl(){
        val validUrl = "http://www.google.com"
        val invalidUrl = "abcssss,,s"

        assert(Url.isValidUrl(validUrl))
        assert(!Url.isValidUrl(invalidUrl))
    }
}