# kotlin.jpa
Practice JPA with Kotlin.

The sources use Kotlin 0.12 syntax and compile with the matching legacy compiler on JDK 8. The Maven build compiles the original `src/jpa` tree and packages the classes and runtime dependencies in a WAR.

Run the integration test against a disposable PostgreSQL database named `codex_jpa_test` on localhost. The test creates and removes its own schema within that database.

```sh
mvn verify -Djpa.test.jdbc.url=jdbc:postgresql://127.0.0.1:5432/codex_jpa_test
```

Flyway 13 requires JDK 17 or newer. Its PostgreSQL database support module is included in the plugin dependencies. Override `jdbc.url`, `jdbc.user`, and `jdbc.password` when running migrations against a separate database.
