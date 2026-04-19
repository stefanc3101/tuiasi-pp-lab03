package ro.tuiasi.pp.lab03

import ro.tuiasi.pp.lab03.ebook.EbookProcessor
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class EbookProcessorTest {

    private val procesor = EbookProcessor()

    // ----- removeMultipleSpaces -----

    @Test
    fun `removeMultipleSpaces inlocuieste doua spatii cu unul`() {
        val rezultat = procesor.removeMultipleSpaces("text  dublu")
        assertEquals("text dublu", rezultat)
    }

    @Test
    fun `removeMultipleSpaces inlocuieste trei spatii cu unul`() {
        val rezultat = procesor.removeMultipleSpaces("text   triplu")
        assertEquals("text triplu", rezultat)
    }

    @Test
    fun `removeMultipleSpaces nu modifica textul fara spatii multiple`() {
        val text = "text normal fara probleme"
        val rezultat = procesor.removeMultipleSpaces(text)
        assertEquals(text, rezultat)
    }

    @Test
    fun `removeMultipleSpaces proceseaza mai multe grupuri de spatii`() {
        val rezultat = procesor.removeMultipleSpaces("a  b   c    d")
        assertEquals("a b c d", rezultat)
    }

    @Test
    fun `removeMultipleSpaces pastreaza spatiul unic`() {
        val rezultat = procesor.removeMultipleSpaces("a b c")
        assertEquals("a b c", rezultat)
    }

    // ----- removeMultipleNewlines -----

    @Test
    fun `removeMultipleNewlines inlocuieste trei newline-uri cu doua`() {
        val rezultat = procesor.removeMultipleNewlines("para1\n\n\npara2")
        assertEquals("para1\n\npara2", rezultat)
    }

    @Test
    fun `removeMultipleNewlines inlocuieste patru newline-uri cu doua`() {
        val rezultat = procesor.removeMultipleNewlines("para1\n\n\n\npara2")
        assertEquals("para1\n\npara2", rezultat)
    }

    @Test
    fun `removeMultipleNewlines nu modifica doua newline-uri`() {
        val text = "para1\n\npara2"
        val rezultat = procesor.removeMultipleNewlines(text)
        assertEquals(text, rezultat)
    }

    @Test
    fun `removeMultipleNewlines proceseaza mai multe grupuri`() {
        val rezultat = procesor.removeMultipleNewlines("a\n\n\nb\n\n\n\nc")
        assertEquals("a\n\nb\n\nc", rezultat)
    }

    // ----- removePageNumbers -----

    @Test
    fun `removePageNumbers elimina numarul de pagina pe linie proprie`() {
        val text = "Capitol 1\n   42   \nContinuare text"
        val rezultat = procesor.removePageNumbers(text)
        assertFalse(rezultat.contains("42"),
            "Numărul de pagină 42 ar trebui eliminat, dar a rămas în: $rezultat")
    }

    @Test
    fun `removePageNumbers elimina numere de pagina multiple`() {
        val text = "Text\n  1  \nAlt text\n  123  \nFinal"
        val rezultat = procesor.removePageNumbers(text)
        assertFalse(rezultat.lines().any { it.trim() == "1" || it.trim() == "123" })
    }

    @Test
    fun `removePageNumbers pastreaza numerele din text normal`() {
        val text = "Sunt 42 de mere în coș."
        val rezultat = procesor.removePageNumbers(text)
        assertTrue(rezultat.contains("42"),
            "Numărul 42 din mijlocul propoziției nu ar trebui eliminat")
    }

    @Test
    fun `removePageNumbers pastreaza text fara numere de pagina`() {
        val text = "Primul paragraf.\n\nAl doilea paragraf."
        val rezultat = procesor.removePageNumbers(text)
        assertTrue(rezultat.contains("Primul paragraf."))
        assertTrue(rezultat.contains("Al doilea paragraf."))
    }
}
