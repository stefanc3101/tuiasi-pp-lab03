package ro.tuiasi.pp.lab03.crawler

import okhttp3.OkHttpClient
import okhttp3.Request
import org.jsoup.Jsoup
import java.net.URI
import java.time.Duration

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
        val domain = extractDomain(startUrl)
        val root = LinkNode(startUrl)

        // Tinem evidenta link-urilor vizitate pentru a nu intra intr-o bucla infinita
        val visited = mutableSetOf<String>()
        visited.add(startUrl)

        // Nivel 1: Procesam pagina de start
        val startHtml = fetchHtml(startUrl)
        val level1Links = extractLinks(startHtml, startUrl, domain)
            .distinct()
            .filter { it !in visited }

        for (link1 in level1Links) {
            visited.add(link1)
            val child1 = LinkNode(link1)
            root.children.add(child1)

            // Nivel 2: Procesam fiecare pagina gasita la nivelul 1
            val html1 = fetchHtml(link1)
            val level2Links = extractLinks(html1, link1, domain)
                .distinct()
                .filter { it !in visited }

            for (link2 in level2Links) {
                visited.add(link2)
                val child2 = LinkNode(link2)
                child1.children.add(child2)
            }
        }

        return root
    }

    /**
     * Extrage domeniul (host) dintr-un URL.
     *
     * @param url URL-ul complet
     * @return Domeniul (ex: "example.com")
     */
    private fun extractDomain(url: String): String {
        return try {
            URI(url).host ?: ""
        } catch (e: Exception) {
            ""
        }
    }

    /**
     * Descarcă conținutul HTML al unei pagini web.
     *
     * @param url URL-ul paginii de descărcat
     * @return Conținutul HTML ca șir de caractere, sau șir gol la eroare
     */
    private fun fetchHtml(url: String): String {
        return try {
            // Instantiem un client OkHttp cu un timeout setat la 10 secunde
            val client = OkHttpClient.Builder()
                .callTimeout(Duration.ofSeconds(10))
                .build()

            val request = Request.Builder().url(url).build()

            // Folosim "use" pentru a inchide automat resursa (response) la final
            client.newCall(request).execute().use { response ->
                response.body?.string() ?: ""
            }
        } catch (e: Exception) {
            // Daca pica internetul sau URL-ul e invalid, returnam sir gol
            ""
        }
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
        if (html.isEmpty()) return emptyList()

        val doc = Jsoup.parse(html, baseUrl)
        return doc.select("a[href]")
            .map { it.absUrl("href") }
            // Pastram doar link-urile al caror domeniu il contine pe cel cautat
            .filter { it.contains(domain) }
    }
}