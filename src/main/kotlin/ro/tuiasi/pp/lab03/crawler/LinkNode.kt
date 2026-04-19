package ro.tuiasi.pp.lab03.crawler

/**
 * Reprezintă un nod în arborele de link-uri construit de crawler.
 *
 * @property url URL-ul paginii reprezentate de acest nod
 * @property children Lista nodurilor-copil (link-urile găsite pe această pagină)
 */
data class LinkNode(
    val url: String,
    val children: MutableList<LinkNode> = mutableListOf()
)
