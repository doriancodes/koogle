package dorian.codes.koogle.page

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class Page(
        var url: String,
        var title: String,
        var text: String,
        var slug: String = title.toSlug(),
        var type: PageType? = null,
        @Id @GeneratedValue var id: Long? = null
)
private fun String.toSlug() = toLowerCase()
        .replace("\n", " ")
        .replace("[^a-z\\d\\s]".toRegex(), " ")
        .split(" ")
        .joinToString("-")
        .replace("-+".toRegex(), "-")

enum class PageType{
    LINK, MEDIA, IMPORT
}