package app.ludrive.adapters.out.persistence.vfs.jpa.repository;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import app.ludrive.adapters.out.persistence.jpa.JpaFactory;
import app.ludrive.adapters.out.persistence.jpa.JpaRepository;
import app.ludrive.adapters.out.persistence.vfs.jpa.entity.JpaEntryItem;
import app.ludrive.adapters.out.persistence.vfs.jpa.entity.JpaEntryItemId;

@RequestScoped
public class JpaEntryItemRepository extends JpaRepository<JpaEntryItem, JpaEntryItemId> {

    @Inject
    public JpaEntryItemRepository(@Named(JpaFactory.BEAN_PERSISTENCE_VFS) JpaFactory jpaFactory) {
        super(jpaFactory);
    }
}
