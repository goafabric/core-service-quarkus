package org.goafabric.core.data.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import org.goafabric.core.data.repository.entity.PatientEo;

import java.util.List;

@ApplicationScoped
public class PatientRepository implements PanacheRepositoryBase<PatientEo, String> {

    public List<PatientEo> findByGivenNameStartsWithIgnoreCase(String givenName) {
        return find("givenName", givenName).list();
    }

    public List<PatientEo> findByFamilyNameStartsWithIgnoreCase(String familyName) {
        return find("familyName", familyName).list();
    }

    public PatientEo save(PatientEo person) {
        persist(person);
        return person;
    }

}

