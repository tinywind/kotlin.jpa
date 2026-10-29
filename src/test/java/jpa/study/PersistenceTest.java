package jpa.study;

import jpa.study.dao.Member;
import org.junit.Test;
import org.hibernate.Hibernate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class PersistenceTest {
    @Test
    public void persistsTheCompiledKotlinEntityWithTheUpdatedPostgresqlDriver() throws Exception {
        String url = System.getProperty("jpa.test.jdbc.url");
        assertNotNull("Set jpa.test.jdbc.url to an isolated codex_jpa_test database", url);
        assertTrue(url.startsWith("jdbc:postgresql://127.0.0.1:"));
        assertTrue(url.endsWith("/codex_jpa_test"));
        String schema = "codex_jpa_test_" + Long.toHexString(System.nanoTime());
        Map<String, Object> properties = new HashMap<>();
        properties.put("javax.persistence.jdbc.url", url);
        properties.put("javax.persistence.jdbc.user", "postgres");
        properties.put("javax.persistence.jdbc.password", "");
        properties.put("javax.persistence.schema-generation.database.action", "create");
        properties.put("hibernate.default_schema", schema);
        properties.put("hibernate.hbm2ddl.halt_on_error", true);
        try (Connection connection = DriverManager.getConnection(url, "postgres", "");
             Statement statement = connection.createStatement()) {
            statement.executeUpdate("CREATE SCHEMA " + schema);
            try {
                EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpabook", properties);
                try {
                    EntityManager manager = factory.createEntityManager();
                    try {
                        manager.getTransaction().begin();
                        Member member = new Member();
                        member.setUsername("security-update");
                        member.setAge(11);
                        member.setFullName("Ada Lovelace");
                        manager.persist(member);
                        manager.getTransaction().commit();
                        manager.clear();
                        Member loaded = manager.getReference(Member.class, member.getId());
                        assertFalse(Hibernate.isInitialized(loaded));
                        assertEquals("security-update", loaded.getUsername());
                        assertTrue(Hibernate.isInitialized(loaded));
                        assertEquals("Ada Lovelace", loaded.getFullName());
                    } finally {
                        manager.close();
                    }
                } finally {
                    factory.close();
                }
            } finally {
                statement.executeUpdate("DROP SCHEMA " + schema + " CASCADE");
            }
        }
    }
}
