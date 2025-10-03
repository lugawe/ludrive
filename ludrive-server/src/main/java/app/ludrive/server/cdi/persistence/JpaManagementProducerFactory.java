package app.ludrive.server.cdi.persistence;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceUnit;

import app.ludrive.adapters.out.persistence.management.jpa.repository.JpaFactory;

@ApplicationScoped
public class JpaManagementProducerFactory {

    @Inject
    @PersistenceUnit(unitName = "management")
    private EntityManagerFactory entityManagerFactory;

    @Inject
    @PersistenceUnit(unitName = "management")
    private EntityManager entityManager;

    public JpaManagementProducerFactory() {}

    @Produces
    @Named(JpaFactory.BEAN_PERSISTENCE_MANAGEMENT)
    public JpaFactory produceManagementJpaFactory() {
        return new JpaFactory(entityManagerFactory, () -> entityManager);
    }
}
