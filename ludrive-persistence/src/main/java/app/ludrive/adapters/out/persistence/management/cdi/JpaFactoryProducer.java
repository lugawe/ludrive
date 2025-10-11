package app.ludrive.adapters.out.persistence.management.cdi;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceUnit;

import app.ludrive.adapters.out.persistence.management.jpa.repository.JpaFactory;

@ApplicationScoped
public class JpaFactoryProducer {

    @Inject
    @PersistenceUnit(unitName = "management")
    private EntityManagerFactory entityManagerFactory;

    @Inject
    @PersistenceUnit(unitName = "management")
    private EntityManager entityManager;

    public JpaFactoryProducer() {}

    @Produces
    @Named(JpaFactory.MANAGEMENT)
    public JpaFactory produce() {
        return new JpaFactory(entityManagerFactory, () -> entityManager);
    }
}
