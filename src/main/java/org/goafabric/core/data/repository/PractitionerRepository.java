package org.goafabric.core.data.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import io.quarkus.panache.common.Parameters;
import jakarta.enterprise.context.ApplicationScoped;
import org.goafabric.core.data.repository.entity.PractitionerEo;

import java.util.List;

@ApplicationScoped
public class PractitionerRepository implements PanacheRepositoryBase<PractitionerEo, String> {

    public List<PractitionerEo> findByGivenNameStartsWithIgnoreCase(String givenName) {
        return find("givenName like concat('%', :givenName, '%')",
                Parameters.with("givenName", givenName)
                        .map()).list();
    }

    public List<PractitionerEo> findByFamilyNameStartsWithIgnoreCase(String familyName) {
        return find("familyName like concat('%', :familyName, '%')",
                Parameters.with("familyName", familyName)
                        .map()).list();
    }
    
    public PractitionerEo save(PractitionerEo person) {
        persist(person);
        return person;
    }

}

