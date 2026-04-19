package ro.tuiasi.pp.lab03.crawler

/**
 * Web crawler care construiește un arbore de link-uri cu adâncimea 2.
 *
 * Pornind de la un URL de start, extrage toate link-urile din pagina respectivă,
 * apoi extrage link-urile din fiecare pagină găsită, păstrând doar link-urile
 * din același domeniu.
 *
 * Adâncime 2 înseamnă: URL start → link-urile sale (nivel 1) → link-urile lor (nivel 2).
 */
class WebCrawler {

    /**
     * Efectuează crawling-ul pornind de la [startUrl] și returnează rădăcina arborelui de link-uri.
     *
     * @param startUrl URL-ul de la care pornește crawling-ul
     * @return [LinkNode] reprezentând rădăcina arborelui (URL-ul de start cu copiii săi)
     */
    fun crawl(startUrl: String): LinkNode {
        // TODO("De implementat")
        // Pași de urmat:
        // 1. Extrageți domeniul din startUrl (ex: "https://example.com" → "example.com")
        //    Puteți folosi java.net.URI(startUrl).host
        // 2. Creați nodul rădăcină: LinkNode(url = startUrl)
        // 3. Folosiți OkHttp pentru a descărca HTML-ul paginii:
        //    val client = OkHttpClient()
        //    val request = Request.Builder().url(url).build()
        //    val html = client.newCall(request).execute().body?.string() ?: ""
        // 4. Folosiți jsoup pentru a extrage link-urile din HTML:
        //    val doc = Jsoup.parse(html, startUrl)
        //    val links = doc.select("a[href]").map { it.absUrl("href") }
        // 5. Filtrați link-urile pentru a păstra doar cele din același domeniu
        // 6. Mențineți un set de URL-uri vizitate pentru a evita ciclurile
        // 7. Pentru fiecare link de nivel 1, repetați procesul pentru a obține nivel 2
        // 8. Construiți arborele cu noduri copil
        TODO("De implementat: crawling cu OkHttp/jsoup, adâncime 2, același domeniu")
    }

    /**
     * Extrage domeniul (host) dintr-un URL.
     *
     * @param url URL-ul complet
     * @return Domeniul (ex: "example.com")
     */
    private fun extractDomain(url: String): String {
        // TODO("De implementat")
        // Indiciu: java.net.URI(url).host
        TODO("De implementat: extrage domeniul din URL")
    }

    /**
     * Descarcă conținutul HTML al unei pagini web.
     *
     * @param url URL-ul paginii de descărcat
     * @return Conținutul HTML ca șir de caractere, sau șir gol la eroare
     */
    private fun fetchHtml(url: String): String {
        // TODO("De implementat")
        // Indiciu: folosiți OkHttpClient cu un timeout rezonabil (ex: 10 secunde)
        // Tratați excepțiile și returnați "" la orice eroare de rețea
        TODO("De implementat: descarcă HTML-ul paginii cu OkHttp")
    }

    /**
     * Extrage toate link-urile absolute din HTML, filtrând după domeniu.
     *
     * @param html Conținutul HTML al paginii
     * @param baseUrl URL-ul de bază (folosit pentru rezolvarea link-urilor relative)
     * @param domain Domeniul permis (se păstrează doar link-urile din acest domeniu)
     * @return Lista de URL-uri absolute filtrate
     */
    private fun extractLinks(html: String, baseUrl: String, domain: String): List<String> {
        // TODO("De implementat")
        // Indiciu:
        // val doc = Jsoup.parse(html, baseUrl)
        // doc.select("a[href]")
        //   .map { it.absUrl("href") }
        //   .filter { it.contains(domain) }
        TODO("De implementat: parsează HTML-ul și extrage link-urile din același domeniu")
    }
}
