package dorian.codes.koogle.models.urls

import dorian.codes.koogle.models.UrlType
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