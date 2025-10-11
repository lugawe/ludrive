package app.ludrive.adapters.out.persistence.management.jpa.repository;

import java.util.Objects;

import jakarta.inject.Provider;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class JpaFactory {

    public static final String MANAGEMENT = "BEAN_PERSISTENCE_MANAGEMENT";

    private final EntityManagerFactory entityManagerFactory;
    private final Provider<EntityManager> entityManagerProvider;

    public JpaFactory(EntityManagerFactory entityManagerFactory, Provider<EntityManager> entityManagerProvider) {
        this.entityManagerFactory = Objects.requireNonNull(entityManagerFactory);
        this.entityManagerProvider = Objects.requireNonNull(entityManagerProvider);
    }

    public JpaFactory(EntityManagerFactory entityManagerFactory) {
        this(entityManagerFactory, entityManagerFactory::createEntityManager);
    }

    public final EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }

    public final Provider<EntityManager> getEntityManagerProvider() {
        return entityManagerProvider;
    }
}
