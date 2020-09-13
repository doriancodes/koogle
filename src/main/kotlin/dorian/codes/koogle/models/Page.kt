package dorian.codes.koogle.models

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class Page(
        var url: String,
        var title: String,
        var text: String,
        var slug: String = url.extract(),
        var type: UrlType? = null,
        @Id @GeneratedValue var id: Long? = null
)

private fun String.toSlug() = toLowerCase()
        .replace("\n", " ")
        .replace("[^a-z\\d\\s]".toRegex(), " ")
        .split(" ")
        .joinToString("-")
        .replace("-+".toRegex(), "-")

private fun String.extract() = split(".").filterNot { s -> stopWords.contains(s) }[0]

private val stopWords: Set<String> = setOf("www", "com", "https://", "http://", "https://www", "http://www")

enum class UrlType {
    LINK, MEDIA, IMPORT
}

fun defaultPage(url: String, title: String? = null): Page {
    fun confirmTitle(): String = if (title.isNullOrBlank()) url.extract() else title!!
    return Page(url, confirmTitle(), "")
}