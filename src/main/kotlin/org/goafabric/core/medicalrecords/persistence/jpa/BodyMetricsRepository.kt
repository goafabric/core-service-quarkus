package org.goafabric.core.medicalrecords.persistence.jpa

import io.quarkus.data.hibernate.ManagedRepository
import org.goafabric.core.medicalrecords.persistence.jpa.entity.BodyMetricsEo

interface BodyMetricsRepository : ManagedRepository.CustomId<BodyMetricsEo, String> {

    fun save(bodyMetricsEo: BodyMetricsEo): BodyMetricsEo {
        return session.merge(bodyMetricsEo)
    }
}
