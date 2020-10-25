package dorian.codes.koogle.searchconsole

import dorian.codes.koogle.pages.UrlType
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.net.MalformedURLException
import java.net.URL
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
) {


    companion object {
        private val logger: Logger = LoggerFactory.getLogger(Url::class.java)
        fun isValidUrl(url: String): Boolean {
            return try {
                val u = URL(url)
                with(u) {
                    if (userInfo != null) logger.info("  userinfo =  $userInfo")
                    if (!host.isEmpty())  logger.info("  domain   =  $host")
                    if (port != -1)       logger.info("  port     =  $port")
                    if (!path.isEmpty())  logger.info("  path     =  $path")
                    if (query != null)    logger.info("  query    =  $query")
                    if (ref != null)      logger.info("  fragment =  $ref")
                }
                true
            } catch (e: MalformedURLException) {
                false
            }
        }
    }
}

private fun String.toSlug() = toLowerCase()
        .replace("\n", " ")
        .replace("[^a-z\\d\\s]".toRegex(), " ")
        .split(" ")
        .joinToString("-")
        .replace("-+".toRegex(), "-")

private fun String.extract() = split(".").filterNot { s -> stopWords.contains(s) }[0]

private val stopWords: Set<String> = setOf("www", "com", "https://", "http://", "https://www", "http://www", "org", "it", "de")
