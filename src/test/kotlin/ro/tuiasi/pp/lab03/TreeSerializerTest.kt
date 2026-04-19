package ro.tuiasi.pp.lab03

import ro.tuiasi.pp.lab03.crawler.LinkNode
import ro.tuiasi.pp.lab03.crawler.TreeSerializer
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class TreeSerializerTest {

    private val serializator = TreeSerializer()

    private fun construiesteArboreTest(): LinkNode {
        // Arbore:
        // https://example.com
        //   https://example.com/pagina1
        //     https://example.com/sub1
        //     https://example.com/sub2
        //   https://example.com/pagina2
        val radacina = LinkNode("https://example.com")
        val pagina1 = LinkNode("https://example.com/pagina1")
        pagina1.children.add(LinkNode("https://example.com/sub1"))
        pagina1.children.add(LinkNode("https://example.com/sub2"))
        radacina.children.add(pagina1)
        radacina.children.add(LinkNode("https://example.com/pagina2"))
        return radacina
    }

    @Test
    fun `serialize produce un sir nevid`() {
        val arbore = construiesteArboreTest()
        val rezultat = serializator.serialize(arbore)
        assertTrue(rezultat.isNotBlank(), "serialize() nu ar trebui să returneze un șir gol")
    }

    @Test
    fun `serialize contine URL-ul radacinii`() {
        val arbore = construiesteArboreTest()
        val rezultat = serializator.serialize(arbore)
        assertTrue(rezultat.contains("https://example.com"),
            "Rezultatul serializat trebuie să conțină URL-ul rădăcinii")
    }

    @Test
    fun `deserialize reconstituie radacina corecta`() {
        val arbore = construiesteArboreTest()
        val serializat = serializator.serialize(arbore)
        val reconstruit = serializator.deserialize(serializat)
        assertEquals("https://example.com", reconstruit.url)
    }

    @Test
    fun `deserialize reconstituie numarul corect de copii ai radacinii`() {
        val arbore = construiesteArboreTest()
        val serializat = serializator.serialize(arbore)
        val reconstruit = serializator.deserialize(serializat)
        assertEquals(2, reconstruit.children.size,
            "Rădăcina ar trebui să aibă 2 copii după deserializare")
    }

    @Test
    fun `deserialize reconstituie corect copiii primului nod`() {
        val arbore = construiesteArboreTest()
        val serializat = serializator.serialize(arbore)
        val reconstruit = serializator.deserialize(serializat)

        val primaPagena = reconstruit.children[0]
        assertEquals("https://example.com/pagina1", primaPagena.url)
        assertEquals(2, primaPagena.children.size)
    }

    @Test
    fun `serialize si deserialize roundtrip complet`() {
        val original = construiesteArboreTest()
        val serializat = serializator.serialize(original)
        val reconstruit = serializator.deserialize(serializat)

        // Verificare structurală completă
        assertEquals(original.url, reconstruit.url)
        assertEquals(original.children.size, reconstruit.children.size)

        val copil0Original = original.children[0]
        val copil0Reconstruit = reconstruit.children[0]
        assertEquals(copil0Original.url, copil0Reconstruit.url)
        assertEquals(copil0Original.children.size, copil0Reconstruit.children.size)
        assertEquals(copil0Original.children[0].url, copil0Reconstruit.children[0].url)
        assertEquals(copil0Original.children[1].url, copil0Reconstruit.children[1].url)

        val copil1Original = original.children[1]
        val copil1Reconstruit = reconstruit.children[1]
        assertEquals(copil1Original.url, copil1Reconstruit.url)
        assertTrue(copil1Reconstruit.children.isEmpty())
    }

    @Test
    fun `serialize si deserialize pentru nod fara copii`() {
        val nod = LinkNode("https://singur.example.com")
        val serializat = serializator.serialize(nod)
        val reconstruit = serializator.deserialize(serializat)
        assertEquals("https://singur.example.com", reconstruit.url)
        assertTrue(reconstruit.children.isEmpty())
    }
}
