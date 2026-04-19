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
        // TODO("De implementat")
        // Indiciu: folosiți Regex(" {2,}") sau " +" și replace cu " "
        TODO("De implementat: înlocuiește 2+ spații consecutive cu un singur spațiu")
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
        // TODO("De implementat")
        // Indiciu: o "linie goală" înseamnă \n\n (sau \r\n\r\n pe Windows)
        // Folosiți Regex("(\r?\n){3,}") și înlocuiți cu "\n\n"
        TODO("De implementat: înlocuiește 3+ caractere newline consecutive cu exact 2")
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
        // TODO("De implementat")
        // Indiciu: un număr de pagină este un șir de cifre care apare singur pe o linie
        // Regex sugerată: Regex("^\\s*\\d+\\s*$", RegexOption.MULTILINE)
        // Înlocuiți potrivirile cu "" (șir gol)
        TODO("De implementat: elimină liniile care conțin doar un număr (număr de pagină)")
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
        // TODO("De implementat") — opțional
        // Înlocuiri necesare:
        // 'ş' (U+015F, s cu cedilă) → 'ș' (U+0219, s cu virgulă dedesubt)
        // 'ţ' (U+0163, t cu cedilă) → 'ț' (U+021B, t cu virgulă dedesubt)
        // 'Ş' → 'Ș', 'Ţ' → 'Ț'
        TODO("De implementat (opțional): corectează maparea veche a diacriticelor românești")
    }
}
