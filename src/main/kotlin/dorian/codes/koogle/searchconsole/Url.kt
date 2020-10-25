package dorian.codes.koogle.searchconsole

import dorian.codes.koogle.pages.UrlType
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class Url(
        var mainUrl: String,
        var type: UrlType,
        var slug: String = mainUrl.extract(),
        @Id @GeneratedValue
        var id: Long? = null
)

private fun String.toSlug() = toLowerCase()
        .replace("\n", " ")
        .replace("[^a-z\\d\\s]".toRegex(), " ")
        .split(" ")
        .joinToString("-")
        .replace("-+".toRegex(), "-")

private fun String.extract() = split(".").filterNot { s -> stopWords.contains(s) }[0]

private val stopWords: Set<String> = setOf("www", "com", "https://", "http://", "https://www", "http://www", "org", "it", "de")
