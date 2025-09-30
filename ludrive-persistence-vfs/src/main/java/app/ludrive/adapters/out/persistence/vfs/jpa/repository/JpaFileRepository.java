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
import app.ludrive.adapters.out.persistence.vfs.jpa.entity.JpaFile;
import app.ludrive.core.domain.management.auth.AuthIdentity;
import app.ludrive.core.domain.vfs.File;
import app.ludrive.core.exception.NotFoundException;
import app.ludrive.core.ports.out.repository.FileRepository;

@RequestScoped
public class JpaFileRepository extends JpaRepository<JpaFile, UUID> implements FileRepository {

    protected final JpaConverter jpaConverter;

    @Inject
    public JpaFileRepository(@Named(JpaFactory.BEAN_PERSISTENCE_VFS) JpaFactory jpaFactory, JpaConverter jpaConverter) {
        super(jpaFactory);
        this.jpaConverter = jpaConverter;
    }

    protected NotFoundException createNotFoundException(UUID entryId, String path) {
        return new NotFoundException(String.format("file not found in %s path %s", entryId, path));
    }

    protected JpaFile getFileByEntryAndPath(UUID entryId, String path) {
        Object result = getEntityManager()
                .createQuery("from JpaFile where entry_id = :entry_id and path = :path")
                .setParameter("entry_id", entryId)
                .setParameter("path", path)
                .getSingleResultOrNull();
        if (!(result instanceof JpaFile jpaFile)) {
            throw createNotFoundException(entryId, path);
        }
        return jpaFile;
    }

    protected Stream<JpaFile> getFilesByEntryAndParentPath(UUID entryId, String parentPath) {
        Stream<?> result = getEntityManager()
                .createQuery("from JpaFile where entry_id = :entry_id and parent_path = :parent_path")
                .setParameter("entry_id", entryId)
                .setParameter("parent_path", parentPath)
                .getResultStream();
        return result.filter(e -> e instanceof JpaFile).map(e -> (JpaFile) e);
    }

    @Override
    @Transactional
    public File createFile(AuthIdentity identity, UUID entryId, File file) {

        JpaFile jpaFile = jpaConverter.toJpaFile(file);

        create(jpaFile);

        return jpaConverter.toFile(jpaFile);
    }

    @Override
    @Transactional
    public Stream<File> getFiles(AuthIdentity identity, UUID entryId, String path) {

        Stream<JpaFile> jpaFileStream = getFilesByEntryAndParentPath(entryId, path);

        return jpaFileStream.map(jpaConverter::toFile).toList().stream();
    }

    @Override
    @Transactional
    public File getFile(AuthIdentity identity, UUID entryId, String path) {

        JpaFile jpaFile = getFileByEntryAndPath(entryId, path);

        return jpaConverter.toFile(jpaFile);
    }

    @Override
    @Transactional
    public File updateFile(AuthIdentity identity, UUID entryId, String path, File file) {

        JpaFile jpaFile = getFileByEntryAndPath(entryId, path);

        jpaConverter.updateJpaFile(jpaFile, file);

        update(jpaFile);

        return jpaConverter.toFile(jpaFile);
    }

    @Override
    @Transactional
    public File deleteFile(AuthIdentity identity, UUID entryId, String path) {

        JpaFile jpaFile = getFileByEntryAndPath(entryId, path);

        delete(jpaFile);

        return jpaConverter.toFile(jpaFile);
    }
}
