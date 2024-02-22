package org.api.springf1.repository;

import org.api.springf1.model.Constructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Testcontainers
@DataJpaTest
public class ConstructorRepositoryTest {
    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15.5");
    @Autowired
    ConstructorRepository constructorRepo;

    Constructor constructor;
    Constructor constructor2;

    @BeforeEach
    void initialize(){
        constructor = Constructor
                .builder().ref("JMS").name("JambosSinFronteras").drivers(null)
                .nationality("Spain").url("https://es.memedroid.com").build();
        constructor2 = Constructor
                .builder().ref("AMS").name("AurelianoMotores").drivers(null)
                .nationality("Spain").url("https://es.memedroid.com").build();
    }

    @Test
    void constructorRepository_findAll_returnsMultipleConstructors(){
        constructorRepo.save(constructor);
        constructorRepo.save(constructor2);

        assertEquals(2, constructorRepo.findAll().size());
    }

    @Test
    void constructorRepository_findByRefIgnoreCase_returnsNotNull(){
        constructorRepo.save(constructor);

        assertNotNull(constructorRepo.findByRefIgnoreCase("JMS"));
    }

    @Test
    void constructorRepository_save_updateConstructor_returnsNotNull(){
        constructorRepo.save(constructor);
        constructor.setName("Cornellá");

        assertNotNull(constructorRepo.save(constructor));
    }

    @Test
    void constructorRepository_delete_returnsNull(){
        constructorRepo.save(constructor);
        constructorRepo.delete(constructor);

        assertEquals(0, constructorRepo.findAll().size());
    }
}