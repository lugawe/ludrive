package app.ludrive.adapters.out.persistence.management.jpa.cdi;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Named;

import app.ludrive.adapters.out.persistence.management.jpa.converter.JpaConverter;
import app.ludrive.adapters.out.persistence.management.jpa.repository.JpaDriveUserRepository;
import app.ludrive.adapters.out.persistence.management.jpa.repository.JpaFactory;
import app.ludrive.core.ports.out.repository.DriveUserRepository;

@ApplicationScoped
public class DriveUserRepositoryProducer {

    public DriveUserRepositoryProducer() {}

    @Produces
    public DriveUserRepository produce(@Named(JpaFactory.MANAGEMENT) JpaFactory jpaFactory, JpaConverter jpaConverter) {
        return new JpaDriveUserRepository(jpaFactory, jpaConverter);
    }
}
