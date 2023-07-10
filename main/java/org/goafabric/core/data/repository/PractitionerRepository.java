package org.goafabric.core.data.repository;

import org.goafabric.core.data.repository.entity.PractitionerEo;

import java.util.List;

public interface PractitionerRepository {
    List<PractitionerEo> findByGivenNameStartsWithIgnoreCase(String givenName);

    List<PractitionerEo> findByFamilyNameStartsWithIgnoreCase(String familyName);

    void deleteById(String id);

    PractitionerEo save(PractitionerEo map);

    PractitionerEo findById(String id);
}

