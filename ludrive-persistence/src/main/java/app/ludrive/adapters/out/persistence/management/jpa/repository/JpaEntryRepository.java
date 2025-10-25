package app.ludrive.adapters.out.persistence.management.jpa.repository;

import java.util.Objects;
import java.util.UUID;
import java.util.stream.Stream;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import app.ludrive.adapters.out.persistence.management.jpa.converter.JpaConverter;
import app.ludrive.adapters.out.persistence.management.jpa.entity.JpaDriveUser;
import app.ludrive.adapters.out.persistence.management.jpa.entity.JpaEntry;
import app.ludrive.core.domain.management.Entry;
import app.ludrive.core.domain.management.auth.DriveUser;
import app.ludrive.core.exception.DomainExceptions;
import app.ludrive.core.ports.out.repository.EntryRepository;

@ApplicationScoped
public class JpaEntryRepository extends JpaRepository<JpaEntry, UUID> implements EntryRepository {

    protected final JpaConverter jpaConverter;

    @Inject
    public JpaEntryRepository(@Named(JpaFactory.MANAGEMENT) JpaFactory jpaFactory, JpaConverter jpaConverter) {
        super(jpaFactory);
        this.jpaConverter = Objects.requireNonNull(jpaConverter);
    }

    protected JpaEntry getEntryById(DriveUser driveUser, UUID entryId) {
        JpaEntry result = getEntityManager()
                .createQuery("from JpaEntry where id = :id and owner.id = :owner_id", JpaEntry.class)
                .setParameter("id", entryId)
                .setParameter("owner_id", driveUser.getId())
                .getSingleResultOrNull();
        if (result == null) {
            throw DomainExceptions.createEntryNotFound(entryId);
        }
        return result;
    }

    @Override
    @Transactional
    public Entry createEntry(DriveUser driveUser, Entry entry) {

        JpaDriveUser jpaDriveUser = jpaConverter.toJpaDriveUser(driveUser);

        JpaEntry jpaEntry = jpaConverter.toJpaEntry(entry);
        jpaEntry.setOwner(jpaDriveUser);

        create(jpaEntry);

        return jpaConverter.toEntry(jpaEntry);
    }

    @Override
    @Transactional
    public Stream<Entry> getEntries(DriveUser driveUser) {

        EntityManager entityManager = getEntityManager();

        Stream<JpaEntry> result = entityManager
                .createQuery("from JpaEntry where owner.id = :owner_id", JpaEntry.class)
                .setParameter("owner_id", driveUser.getId())
                .getResultStream();

        return result.map(jpaConverter::toEntry).toList().stream();
    }

    @Override
    @Transactional
    public Entry getEntry(DriveUser driveUser, UUID entryId) {

        JpaEntry jpaEntry = getEntryById(driveUser, entryId);

        return jpaConverter.toEntry(jpaEntry);
    }

    @Override
    @Transactional
    public Entry updateEntry(DriveUser driveUser, UUID entryId, Entry updatedEntry) {

        JpaEntry jpaEntry = getEntryById(driveUser, entryId);

        jpaConverter.updateJpaEntry(jpaEntry, updatedEntry);

        update(jpaEntry);

        return jpaConverter.toEntry(jpaEntry);
    }

    @Override
    @Transactional
    public Entry deleteEntry(DriveUser driveUser, UUID entryId) {

        JpaEntry jpaEntry = getEntryById(driveUser, entryId);

        delete(jpaEntry);

        return jpaConverter.toEntry(jpaEntry);
    }
}
