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
 * [https://example.com](https://example.com)
 * \t[https://example.com/pagina1](https://example.com/pagina1)
 * \t\t[https://example.com/sub1](https://example.com/sub1)
 * \t[https://example.com/pagina2](https://example.com/pagina2)
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
        // Folosim StringBuilder pentru a construi eficient string-ul final
        val builder = java.lang.StringBuilder()

        // Definim o functie recursiva locala pentru a parcurge arborele
        fun traverse(node: LinkNode, depth: Int) {
            // Adaugam tab-urile corespunzatoare adancimii, urmate de URL si linie noua
            builder.append("\t".repeat(depth)).append(node.url).append("\n")
            // Apelam recursiv pentru fiecare copil, crescand adancimea cu 1
            for (child in node.children) {
                traverse(child, depth + 1)
            }
        }

        traverse(root, 0)
        return builder.toString()
    }

    /**
     * Deserializează un șir de caractere (produs de [serialize]) într-un arbore [LinkNode].
     *
     * @param input Reprezentarea textuală a arborelui
     * @return Rădăcina arborelui reconstruit
     * @throws IllegalArgumentException dacă șirul este gol sau invalid
     */
    fun deserialize(input: String): LinkNode {
        // Impartim textul in linii si le ignoram pe cele complet goale
        val lines = input.lines().filter { it.isNotEmpty() }
        if (lines.isEmpty()) {
            throw IllegalArgumentException("Input-ul nu poate fi gol")
        }

        // Prima linie este mereu radacina (adancime 0)
        val rootUrl = lines[0].trimStart('\t')
        val root = LinkNode(rootUrl)

        // Folosim un map pentru a tine minte ultimul nod vazut la fiecare nivel de adancime
        val lastNodeAtDepth = mutableMapOf<Int, LinkNode>()
        lastNodeAtDepth[0] = root

        for (i in 1 until lines.size) {
            val line = lines[i]
            // Numaram tab-urile pentru a afla adancimea curenta
            val depth = line.takeWhile { it == '\t' }.length
            val url = line.trimStart('\t')
            val node = LinkNode(url)

            // Salvam nodul curent la adancimea sa
            lastNodeAtDepth[depth] = node

            // Il adaugam ca si copil la ultimul nod de pe nivelul superior (parintele)
            lastNodeAtDepth[depth - 1]?.children?.add(node)
        }

        return root
    }
}