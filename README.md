# kotlin.jpa
Practice JPA with Kotlin.

Requires JDK 17 or newer. Maven uses Kotlin 2.4.21 and the JPA compiler plugin to compile the original chapter source tree under `src/jpa`. Reimport `pom.xml` in the IDE to refresh its compiler and Maven classpath.

Run the integration test against a disposable PostgreSQL database named `codex_jpa_test` on localhost. The test creates and removes its own schema within that database.

```sh
mvn verify -Djpa.test.jdbc.url=jdbc:postgresql://127.0.0.1:5432/codex_jpa_test
```

The tests cover database persistence, lazy entity proxies, the computed full name, and bidirectional association setters. All chapter sources compile; the individual interactive chapter workflows are not all acceptance-tested.

The Flyway plugin includes PostgreSQL database support. Override `jdbc.url`, `jdbc.user`, and `jdbc.password` when running migrations against a separate database.
