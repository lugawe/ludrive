package app.ludrive.adapters.out.persistence.management.jpa.repository;

import java.util.Objects;
import java.util.UUID;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.transaction.Transactional;

import app.ludrive.adapters.out.persistence.management.jpa.converter.JpaConverter;
import app.ludrive.adapters.out.persistence.management.jpa.entity.JpaDriveUser;
import app.ludrive.core.domain.management.auth.DriveUser;
import app.ludrive.core.exception.NotFoundException;
import app.ludrive.core.ports.out.repository.DriveUserRepository;

@ApplicationScoped
public class JpaDriveUserRepository extends JpaRepository<JpaDriveUser, UUID> implements DriveUserRepository {

    protected final JpaConverter jpaConverter;

    @Inject
    public JpaDriveUserRepository(@Named(JpaFactory.MANAGEMENT) JpaFactory jpaFactory, JpaConverter jpaConverter) {
        super(jpaFactory);
        this.jpaConverter = Objects.requireNonNull(jpaConverter);
    }

    protected NotFoundException createNotFoundException(UUID driveUserId) {
        return new NotFoundException(String.format("drive user not found: %s", driveUserId));
    }

    protected JpaDriveUser getDriveUserById(UUID driveUserId) {
        JpaDriveUser result = getEntityManager()
                .createQuery("from JpaDriveUser where id = :id", JpaDriveUser.class)
                .setParameter("id", driveUserId)
                .getSingleResultOrNull();
        if (result == null) {
            throw createNotFoundException(driveUserId);
        }
        return result;
    }

    @Override
    @Transactional
    public DriveUser createDriveUser(DriveUser driveUser) {

        JpaDriveUser jpaDriveUser = jpaConverter.toJpaDriveUser(driveUser);

        create(jpaDriveUser);

        return jpaConverter.toDriveUser(jpaDriveUser);
    }

    @Override
    @Transactional
    public DriveUser getDriveUser(UUID driveUserId) {

        JpaDriveUser jpaDriveUser = getDriveUserById(driveUserId);

        return jpaConverter.toDriveUser(jpaDriveUser);
    }

    @Override
    @Transactional
    public DriveUser updateDriveUser(UUID driveUserId, DriveUser driveUser) {

        JpaDriveUser jpaDriveUser = getDriveUserById(driveUserId);

        jpaConverter.updateJpaDriveUser(jpaDriveUser, driveUser);

        update(jpaDriveUser);

        return jpaConverter.toDriveUser(jpaDriveUser);
    }

    @Override
    @Transactional
    public DriveUser deleteDriveUser(UUID driveUserId) {

        JpaDriveUser jpaDriveUser = getDriveUserById(driveUserId);

        delete(jpaDriveUser);

        return jpaConverter.toDriveUser(jpaDriveUser);
    }
}
