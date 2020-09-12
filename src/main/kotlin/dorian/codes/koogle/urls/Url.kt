package dorian.codes.koogle.urls

import dorian.codes.koogle.pages.UrlType
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class Url(
        var mainUrl: String,
        var type: UrlType,
        @Id @GeneratedValue
        var id: Long? = null
)