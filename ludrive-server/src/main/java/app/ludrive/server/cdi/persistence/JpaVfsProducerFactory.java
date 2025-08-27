package app.ludrive.server.cdi.persistence;

import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceContext;

import app.ludrive.adapters.out.persistence.jpa.JpaFactory;

@RequestScoped
public class JpaVfsProducerFactory {

    @Inject
    @PersistenceContext(unitName = "vfs")
    private EntityManagerFactory entityManagerFactory;

    @Inject
    @PersistenceContext(unitName = "vfs")
    private EntityManager entityManager;

    public JpaVfsProducerFactory() {}

    @Produces
    @Named(JpaFactory.BEAN_PERSISTENCE_VFS)
    public JpaFactory produceVfsJpaFactory() {
        return new JpaFactory(entityManagerFactory, () -> entityManager);
    }
}
