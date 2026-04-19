package ro.tuiasi.pp.lab03.rss

/**
 * Reprezintă un element (articol) dintr-un feed RSS.
 *
 * @property title Titlul articolului
 * @property link URL-ul articolului
 * @property description Descrierea scurtă a articolului
 */
data class RssItem(
    val title: String,
    val link: String,
    val description: String
)

/**
 * Reprezintă un canal RSS complet, cu metadate și lista de articole.
 *
 * @property title Titlul canalului
 * @property link URL-ul canalului
 * @property description Descrierea canalului
 * @property items Lista de articole din canal
 */
data class RssChannel(
    val title: String,
    val link: String,
    val description: String,
    val items: List<RssItem>
)
