package app.ludrive.adapters.out.persistence.management.jpa.repository;

import java.util.UUID;
import java.util.stream.Stream;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import app.ludrive.adapters.out.persistence.jpa.JpaFactory;
import app.ludrive.adapters.out.persistence.jpa.JpaRepository;
import app.ludrive.adapters.out.persistence.management.jpa.converter.JpaConverter;
import app.ludrive.adapters.out.persistence.management.jpa.entity.JpaDriveUser;
import app.ludrive.adapters.out.persistence.management.jpa.entity.JpaEntry;
import app.ludrive.core.domain.management.Entry;
import app.ludrive.core.domain.management.auth.AuthIdentity;
import app.ludrive.core.exception.NotFoundException;
import app.ludrive.core.ports.out.repository.EntryRepository;

@ApplicationScoped
public class JpaEntryRepository extends JpaRepository<JpaEntry, UUID> implements EntryRepository {

    @Inject
    public JpaEntryRepository(@Named(JpaFactory.BEAN_PERSISTENCE_MANAGEMENT) JpaFactory jpaFactory) {
        super(jpaFactory);
    }

    protected NotFoundException createNotFoundException(UUID entryId) {
        return new NotFoundException(String.format("entry not found: %s", entryId));
    }

    protected JpaEntry getEntryById(AuthIdentity identity, UUID entryId) {

        JpaDriveUser jpaDriveUser = JpaConverter.resolveAuthIdentity(identity);

        Object result = getEntityManager()
                .createQuery("from JpaEntry where id = :id and owner.id = :owner_id")
                .setParameter("id", entryId)
                .setParameter("owner_id", jpaDriveUser.getId())
                .getSingleResultOrNull();
        if (!(result instanceof JpaEntry jpaEntry)) {
            throw createNotFoundException(entryId);
        }
        return jpaEntry;
    }

    @Override
    @Transactional
    public Entry createEntry(AuthIdentity identity, Entry entry) {

        JpaDriveUser jpaDriveUser = JpaConverter.resolveAuthIdentity(identity);

        JpaEntry jpaEntry = JpaConverter.toJpaEntry(entry);
        jpaEntry.setOwner(jpaDriveUser);

        create(jpaEntry);

        return JpaConverter.toEntry(jpaEntry);
    }

    @Override
    @Transactional
    public Stream<Entry> getEntries(AuthIdentity identity) {

        JpaDriveUser jpaDriveUser = JpaConverter.resolveAuthIdentity(identity);

        EntityManager entityManager = getEntityManager();

        Stream<?> result = entityManager
                .createQuery("from JpaEntry where owner.id = :owner_id")
                .setParameter("owner_id", jpaDriveUser.getId())
                .getResultStream();

        return result.map(o -> (JpaEntry) o).map(JpaConverter::toEntry).toList().stream();
    }

    @Override
    @Transactional
    public Entry getEntry(AuthIdentity identity, UUID entryId) {

        JpaEntry jpaEntry = getEntryById(identity, entryId);

        return JpaConverter.toEntry(jpaEntry);
    }

    @Override
    @Transactional
    public Entry updateEntry(AuthIdentity identity, UUID entryId, Entry entry) {

        JpaEntry jpaEntry = getEntryById(identity, entryId);

        JpaConverter.updateJpaEntry(jpaEntry, entry);

        update(jpaEntry);

        return JpaConverter.toEntry(jpaEntry);
    }

    @Override
    @Transactional
    public UUID deleteEntry(AuthIdentity identity, UUID entryId) {

        JpaEntry jpaEntry = getEntryById(identity, entryId);

        delete(jpaEntry);

        return entryId;
    }
}
