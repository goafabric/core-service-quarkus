package org.goafabric.core.organization.persistence

import io.quarkus.data.hibernate.ManagedRepository
import jakarta.data.repository.Query
import org.goafabric.core.organization.persistence.entity.RoleEo

interface RoleRepository : ManagedRepository.CustomId<RoleEo, String> {
    @Query("SELECT r FROM RoleEo r WHERE r.name LIKE CONCAT(:name, '%')")
    fun findByNameStartsWith(name: String): List<RoleEo>

    fun save(roleEo: RoleEo): RoleEo {
        return session.merge(roleEo)
    }
}
