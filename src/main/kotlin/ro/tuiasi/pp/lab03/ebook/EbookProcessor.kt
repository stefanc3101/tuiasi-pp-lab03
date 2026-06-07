package ro.tuiasi.pp.lab03.ebook

/**
 * Procesor de text pentru fișiere ebook.
 *
 * Oferă funcții de curățare și normalizare a textului extras din ebook-uri,
 * folosind expresii regulate pentru identificarea și corectarea problemelor comune.
 */
class EbookProcessor {

    /**
     * Înlocuiește orice secvență de două sau mai multe spații cu un singur spațiu.
     *
     * Exemplu: `"text  cu   spații"` → `"text cu spații"`
     *
     * @param text Textul de procesat
     * @return Textul cu spații multiple normalizate
     */
    fun removeMultipleSpaces(text: String): String {
        // Cauta 2 sau mai multe spatii consecutive (" {2,}") si le inlocuieste cu unul singur
        return text.replace(Regex(" {2,}"), " ")
    }

    /**
     * Înlocuiește orice secvență de două sau mai multe linii goale cu o singură linie goală.
     *
     * Exemplu: `"paragraf1\n\n\n\nparagraf2"` → `"paragraf1\n\nparagraf2"`
     *
     * @param text Textul de procesat
     * @return Textul cu linii goale multiple normalizate
     */
    fun removeMultipleNewlines(text: String): String {
        // Cauta 3 sau mai multe delimitatoare de linie (suporta atat Windows \r\n cat si Linux \n)
        return text.replace(Regex("(\\r?\\n){3,}"), "\n\n")
    }

    /**
     * Elimină numerele de pagină din text.
     *
     * Un număr de pagină apare pe o linie proprie, înconjurat de spații albe,
     * de exemplu: `"\n   42   \n"`.
     *
     * @param text Textul de procesat
     * @return Textul cu numerele de pagină eliminate
     */
    fun removePageNumbers(text: String): String {
        // ^ si $ reprezinta inceputul si finalul liniei in modul MULTILINE
        return text.replace(Regex("^\\s*\\d+\\s*$", RegexOption.MULTILINE), "")
    }

    /**
     * Corectează caracterele românești encodate greșit (mapare veche).
     *
     * Unele fișiere PDF/ebook encodează caracterele diacritice românești cu
     * coduri cedilă în loc de virgulă (ş→ș, ţ→ț și variantele majuscule).
     *
     * @param text Textul de procesat
     * @return Textul cu caracterele românești corectate
     */
    fun fixRomanianChars(text: String): String {
        // Aplicam succesiv replace pentru caracterele gresite
        return text.replace('ş', 'ș')
            .replace('ţ', 'ț')
            .replace('Ş', 'Ș')
            .replace('Ţ', 'Ț')
    }
}