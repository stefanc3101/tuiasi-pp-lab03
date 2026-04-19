package ro.tuiasi.pp.lab03.crawler

/**
 * Serializator/deserializator pentru arborele de link-uri [LinkNode].
 *
 * Formatul de serializare folosit este bazat pe indentare:
 * - Fiecare nod apare pe o linie proprie
 * - Adâncimea nodului este indicată de numărul de caractere TAB de la începutul liniei
 * - Rădăcina are adâncimea 0 (fără TAB), copiii ei adâncimea 1 (un TAB) etc.
 *
 * Exemplu:
 * ```
 * https://example.com
 * \thttps://example.com/pagina1
 * \t\thttps://example.com/sub1
 * \thttps://example.com/pagina2
 * ```
 */
class TreeSerializer {

    /**
     * Serializează un arbore [LinkNode] într-un șir de caractere.
     *
     * @param root Rădăcina arborelui de serializat
     * @return Reprezentarea textuală a arborelui
     */
    fun serialize(root: LinkNode): String {
        // TODO("De implementat")
        // Pași de urmat:
        // 1. Creați un StringBuilder
        // 2. Definiți o funcție recursivă internă care primește un nod și adâncimea curentă
        // 3. La fiecare apel: adăugați (adâncime × '\t') + nod.url + '\n'
        // 4. Apelați recursiv pentru fiecare copil cu adâncime + 1
        // 5. Returnați conținutul StringBuilder-ului
        TODO("De implementat: serializează arborele în format bazat pe indentare TAB")
    }

    /**
     * Deserializează un șir de caractere (produs de [serialize]) într-un arbore [LinkNode].
     *
     * @param input Reprezentarea textuală a arborelui
     * @return Rădăcina arborelui reconstruit
     * @throws IllegalArgumentException dacă șirul este gol sau invalid
     */
    fun deserialize(input: String): LinkNode {
        // TODO("De implementat")
        // Pași de urmat:
        // 1. Împărțiți input-ul în linii (filtrați liniile goale)
        // 2. Pentru fiecare linie, numărați TAB-urile de la început → aceasta este adâncimea nodului
        // 3. URL-ul nodului este linia fără TAB-urile de la început (trimStart('\t'))
        // 4. Mențineți o stivă (sau listă) cu nodurile de pe fiecare nivel de adâncime
        // 5. Primul nod (adâncime 0) este rădăcina
        // 6. Un nod de adâncime N este copilul nodului de adâncime N-1 din vârful stivei
        TODO("De implementat: reconstruiește arborele din formatul bazat pe indentare TAB")
    }
}
