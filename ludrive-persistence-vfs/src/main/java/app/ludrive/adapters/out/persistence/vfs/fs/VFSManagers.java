package app.ludrive.adapters.out.persistence.vfs.fs;

import java.util.UUID;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import app.ludrive.core.domain.management.Entry;
import app.ludrive.core.ports.out.repository.EntryRepository;

@ApplicationScoped
public class VFSManagers {

    private final EntryRepository entryRepository;

    @Inject
    public VFSManagers(EntryRepository entryRepository) {
        this.entryRepository = entryRepository;
    }

    public VFSManager create(UUID entryId) throws Exception {

        Entry entry = entryRepository.getEntry(null, entryId);

        return new VFSManager(entry);
    }
}
