package app.ludrive.adapters.out.persistence.vfs.jpa.repository;

import java.util.UUID;
import java.util.stream.Stream;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.transaction.Transactional;

import app.ludrive.adapters.out.persistence.jpa.JpaFactory;
import app.ludrive.adapters.out.persistence.jpa.JpaRepository;
import app.ludrive.adapters.out.persistence.vfs.jpa.converter.JpaConverter;
import app.ludrive.adapters.out.persistence.vfs.jpa.entity.JpaDirectory;
import app.ludrive.core.domain.management.auth.AuthIdentity;
import app.ludrive.core.domain.vfs.Directory;
import app.ludrive.core.exception.NotFoundException;
import app.ludrive.core.ports.out.repository.DirectoryRepository;

@RequestScoped
public class JpaDirectoryRepository extends JpaRepository<JpaDirectory, UUID> implements DirectoryRepository {

    protected final JpaConverter jpaConverter;

    @Inject
    public JpaDirectoryRepository(
            @Named(JpaFactory.BEAN_PERSISTENCE_VFS) JpaFactory jpaFactory, JpaConverter jpaConverter) {
        super(jpaFactory);
        this.jpaConverter = jpaConverter;
    }

    protected NotFoundException createNotFoundException(UUID entryId, String path) {
        return new NotFoundException(String.format("directory not found in %s path %s", entryId, path));
    }

    protected JpaDirectory getDirectoryByEntryAndPath(UUID entryId, String path) {
        Object result = getEntityManager()
                .createQuery("from JpaDirectory where entry_id = :entry_id and path = :path")
                .setParameter("entry_id", entryId)
                .setParameter("path", path)
                .getSingleResultOrNull();
        if (!(result instanceof JpaDirectory jpaDirectory)) {
            throw createNotFoundException(entryId, path);
        }
        return jpaDirectory;
    }

    protected Stream<JpaDirectory> getDirectoriesByEntryAndParentPath(UUID entryId, String parentPath) {
        Stream<?> result = getEntityManager()
                .createQuery("from JpaDirectory where entry_id = :entry_id and parent_path = :parent_path")
                .setParameter("entry_id", entryId)
                .setParameter("parent_path", parentPath)
                .getResultStream();
        return result.filter(e -> e instanceof JpaDirectory).map(e -> (JpaDirectory) e);
    }

    @Override
    @Transactional
    public Directory createDirectory(AuthIdentity identity, UUID entryId, Directory directory) {

        JpaDirectory jpaDirectory = jpaConverter.toJpaDirectory(directory);

        create(jpaDirectory);

        return jpaConverter.toDirectory(jpaDirectory);
    }

    @Override
    @Transactional
    public Stream<Directory> getDirectories(AuthIdentity identity, UUID entryId, String path) {

        Stream<JpaDirectory> jpaDirectoryStream = getDirectoriesByEntryAndParentPath(entryId, path);

        return jpaDirectoryStream.map(jpaConverter::toDirectory).toList().stream();
    }

    @Override
    @Transactional
    public Directory getDirectory(AuthIdentity identity, UUID entryId, String path) {

        JpaDirectory jpaDirectory = getDirectoryByEntryAndPath(entryId, path);

        return jpaConverter.toDirectory(jpaDirectory);
    }

    @Override
    @Transactional
    public Directory updateDirectory(AuthIdentity identity, UUID entryId, String path, Directory directory) {

        JpaDirectory jpaDirectory = getDirectoryByEntryAndPath(entryId, path);

        jpaConverter.updateJpaDirectory(jpaDirectory, directory);

        update(jpaDirectory);

        return jpaConverter.toDirectory(jpaDirectory);
    }

    @Override
    @Transactional
    public Directory deleteDirectory(AuthIdentity identity, UUID entryId, String path) {

        JpaDirectory jpaDirectory = getDirectoryByEntryAndPath(entryId, path);

        delete(jpaDirectory);

        return jpaConverter.toDirectory(jpaDirectory);
    }
}
