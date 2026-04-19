package ro.tuiasi.pp.lab03

import ro.tuiasi.pp.lab03.rss.RssParser
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class RssParserTest {

    private val parser = RssParser()

    private val xmlSimplу = """
        <?xml version="1.0" encoding="UTF-8"?>
        <rss version="2.0">
          <channel>
            <title>Știri Tehnice</title>
            <link>https://example.com</link>
            <description>Cele mai noi știri din tehnologie</description>
            <item>
              <title>Articol despre Kotlin</title>
              <link>https://example.com/kotlin</link>
              <description>Kotlin este un limbaj modern</description>
            </item>
            <item>
              <title>Articol despre Java</title>
              <link>https://example.com/java</link>
              <description>Java rămâne relevant</description>
            </item>
          </channel>
        </rss>
    """.trimIndent()

    private val xmlFaraIteme = """
        <?xml version="1.0" encoding="UTF-8"?>
        <rss version="2.0">
          <channel>
            <title>Canal Gol</title>
            <link>https://gol.example.com</link>
            <description>Canal fără articole</description>
          </channel>
        </rss>
    """.trimIndent()

    @Test
    fun `parse returneaza titlul corect al canalului`() {
        val canal = parser.parse(xmlSimplу)
        assertEquals("Știri Tehnice", canal.title)
    }

    @Test
    fun `parse returneaza link-ul corect al canalului`() {
        val canal = parser.parse(xmlSimplу)
        assertEquals("https://example.com", canal.link)
    }

    @Test
    fun `parse returneaza descrierea corecta a canalului`() {
        val canal = parser.parse(xmlSimplу)
        assertEquals("Cele mai noi știri din tehnologie", canal.description)
    }

    @Test
    fun `parse returneaza numarul corect de articole`() {
        val canal = parser.parse(xmlSimplу)
        assertEquals(2, canal.items.size)
    }

    @Test
    fun `parse populeaza corect primul articol`() {
        val canal = parser.parse(xmlSimplу)
        val item = canal.items[0]
        assertEquals("Articol despre Kotlin", item.title)
        assertEquals("https://example.com/kotlin", item.link)
        assertEquals("Kotlin este un limbaj modern", item.description)
    }

    @Test
    fun `parse populeaza corect al doilea articol`() {
        val canal = parser.parse(xmlSimplу)
        val item = canal.items[1]
        assertEquals("Articol despre Java", item.title)
        assertEquals("https://example.com/java", item.link)
    }

    @Test
    fun `parse returneaza lista goala pentru canal fara articole`() {
        val canal = parser.parse(xmlFaraIteme)
        assertEquals("Canal Gol", canal.title)
        assertTrue(canal.items.isEmpty())
    }

    @Test
    fun `parse un singur articol`() {
        val xml = """
            <rss><channel>
              <title>T</title><link>https://t.com</link><description>D</description>
              <item>
                <title>Item1</title>
                <link>https://t.com/1</link>
                <description>Desc1</description>
              </item>
            </channel></rss>
        """.trimIndent()
        val canal = parser.parse(xml)
        assertEquals(1, canal.items.size)
        assertEquals("Item1", canal.items[0].title)
    }
}
