# Lab 03 — Kotlin ADT (OkHttp + jsoup)

Laborator de programare în Kotlin cu accent pe tipuri algebrice de date și procesarea datelor web.

## Structura proiectului

```
lab03/
├── src/
│   ├── main/kotlin/ro/tuiasi/pp/lab03/
│   │   ├── rss/          # Tema 1: Parser RSS
│   │   ├── ebook/        # Tema 2: Procesor ebook
│   │   └── crawler/      # Tema 3: Web crawler
│   └── test/kotlin/ro/tuiasi/pp/lab03/
│       ├── RssParserTest.kt
│       ├── EbookProcessorTest.kt
│       └── TreeSerializerTest.kt
├── .github/workflows/classroom.yml
├── pom.xml
├── ASSIGNMENT.md
└── README.md
```

## Tehnologii folosite

- **Kotlin** 2.0.21
- **OkHttp** 4.12.0 — cereri HTTP
- **jsoup** 1.18.1 — parsare HTML/XML
- **JUnit 5** — testare

## Cum rulezi

```bash
# Compilare și teste
mvn -B test

# Compilare fără teste
mvn -B compile
```

## Cerințe sistem

- JDK 21 (Temurin recomandat)
- Maven 3.8+

## Citește mai mult

Vezi [ASSIGNMENT.md](ASSIGNMENT.md) pentru cerințele complete ale temelor.
