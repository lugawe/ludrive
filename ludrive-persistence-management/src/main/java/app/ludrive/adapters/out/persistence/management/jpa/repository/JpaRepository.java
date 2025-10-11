package app.ludrive.adapters.out.persistence.management.jpa.repository;

import java.util.Objects;

import jakarta.persistence.EntityManager;

public abstract class JpaRepository<T, ID> {

    private JpaFactory jpaFactory;

    public JpaRepository(JpaFactory jpaFactory) {
        this.jpaFactory = Objects.requireNonNull(jpaFactory);
    }

    public JpaRepository() {}

    public EntityManager getEntityManager() {
        return jpaFactory.getEntityManagerProvider().get();
    }

    public void create(Object o) {
        getEntityManager().persist(o);
    }

    public void update(Object o) {
        getEntityManager().merge(o);
    }

    public void delete(Object o) {
        getEntityManager().remove(o);
    }
}
