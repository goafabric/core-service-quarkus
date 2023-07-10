package org.goafabric.core.data.repository;

import org.goafabric.core.data.repository.entity.PatientEo;

import java.util.List;

public interface PatientRepository  {
    List<PatientEo> findByGivenNameStartsWithIgnoreCase(String givenName);

    List<PatientEo> findByFamilyNameStartsWithIgnoreCase(String familyName);

    void deleteById(String id);

    PatientEo save(PatientEo map);

    PatientEo findById(String id);
}

