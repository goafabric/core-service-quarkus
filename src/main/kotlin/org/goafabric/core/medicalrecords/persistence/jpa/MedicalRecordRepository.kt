package org.goafabric.core.medicalrecords.persistence.jpa

import io.quarkus.data.hibernate.ManagedRepository
import jakarta.data.repository.Query
import org.goafabric.core.medicalrecords.persistence.jpa.entity.MedicalRecordEo

interface MedicalRecordRepository : ManagedRepository.CustomId<MedicalRecordEo, String> {

    @Query("SELECT m FROM MedicalRecordEo m WHERE m.specialization = :specialization")
    fun findBySpecialization(specialization: String): MedicalRecordEo

    fun save(medicalRecordEo: MedicalRecordEo): MedicalRecordEo {
        return session.merge(medicalRecordEo)
    }
}
