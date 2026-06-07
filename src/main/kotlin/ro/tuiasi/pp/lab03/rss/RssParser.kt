package ro.tuiasi.pp.lab03.rss

import org.jsoup.Jsoup
import org.jsoup.parser.Parser

/**
 * Parser pentru feed-uri RSS în format XML.
 *
 * Utilizează jsoup pentru parsarea documentului XML și extragerea
 * elementelor de tip canal și articol.
 */
class RssParser {

    /**
     * Parsează un șir XML conform structurii RSS și returnează un [RssChannel].
     *
     * Structura XML așteptată:
     * ```xml
     * <rss>
     * <channel>
     * <title>...</title>
     * <link>...</link>
     * <description>...</description>
     * <item>
     * <title>...</title>
     * <link>...</link>
     * <description>...</description>
     * </item>
     * </channel>
     * </rss>
     * ```
     *
     * @param xmlString Conținutul XML al feed-ului RSS ca șir de caractere
     * @return [RssChannel] populat cu datele din XML
     */
    fun parse(xmlString: String): RssChannel {
        // Parsam textul ca XML, nu HTML, pentru ca Jsoup sa respecte case-sensitivity-ul si tag-urile
        val doc = Jsoup.parse(xmlString, "", Parser.xmlParser())

        // Selectam elementul parinte channel
        val channel = doc.selectFirst("channel")
            ?: throw IllegalArgumentException("Format XML invalid: nu s-a gasit tag-ul <channel>")

        // Folosim combinatorul " > " pentru a lua titlul direct de sub channel, nu titlurile din interiorul item-urilor
        val channelTitle = channel.selectFirst("channel > title")?.text() ?: ""
        val channelLink = channel.selectFirst("channel > link")?.text() ?: ""
        val channelDesc = channel.selectFirst("channel > description")?.text() ?: ""

        // Iceram prin fiecare tag de tip <item> si construim obiectele data class
        val items = channel.select("item").map { itemNode ->
            RssItem(
                title = itemNode.selectFirst("title")?.text() ?: "",
                link = itemNode.selectFirst("link")?.text() ?: "",
                description = itemNode.selectFirst("description")?.text() ?: ""
            )
        }

        return RssChannel(channelTitle, channelLink, channelDesc, items)
    }
}