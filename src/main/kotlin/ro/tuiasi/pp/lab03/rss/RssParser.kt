package ro.tuiasi.pp.lab03.rss

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
     *   <channel>
     *     <title>...</title>
     *     <link>...</link>
     *     <description>...</description>
     *     <item>
     *       <title>...</title>
     *       <link>...</link>
     *       <description>...</description>
     *     </item>
     *   </channel>
     * </rss>
     * ```
     *
     * @param xmlString Conținutul XML al feed-ului RSS ca șir de caractere
     * @return [RssChannel] populat cu datele din XML
     */
    fun parse(xmlString: String): RssChannel {
        // TODO("De implementat")
        // Pași de urmat:
        // 1. Parsați xmlString folosind jsoup cu parser XML:
        //    val doc = Jsoup.parse(xmlString, "", org.jsoup.parser.Parser.xmlParser())
        // 2. Extrageți elementul <channel> din document
        // 3. Citiți titlul, link-ul și descrierea canalului (primele elemente directe, nu din <item>)
        // 4. Iterați prin toate elementele <item> și construiți lista de RssItem
        // 5. Returnați un RssChannel cu datele extrase
        TODO("De implementat: parsați XML-ul RSS și construiți structura RssChannel")
    }
}
