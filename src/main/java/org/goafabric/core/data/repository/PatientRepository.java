package org.goafabric.core.data.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import io.quarkus.panache.common.Parameters;
import jakarta.enterprise.context.ApplicationScoped;
import org.goafabric.core.data.repository.entity.PatientEo;

import java.util.List;

@ApplicationScoped
public class PatientRepository implements PanacheRepositoryBase<PatientEo, String> {

    public List<PatientEo> findByGivenNameStartsWithIgnoreCase(String givenName) {
        return find("givenName like concat('%', :givenName, '%')",
                Parameters.with("givenName", givenName)
                        .map()).list();
    }

    public List<PatientEo> findByFamilyNameStartsWithIgnoreCase(String familyName) {
        return find("familyName like concat('%', :familyName, '%')",
                Parameters.with("familyName", familyName)
                        .map()).list();
    }

    public PatientEo save(PatientEo person) {
        persist(person);
        return person;
    }

}

