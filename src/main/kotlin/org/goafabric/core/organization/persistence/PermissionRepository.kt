package org.goafabric.core.organization.persistence

import io.quarkus.data.hibernate.ManagedRepository
import jakarta.data.repository.Query
import org.goafabric.core.organization.persistence.entity.PermissionEo

interface PermissionRepository : ManagedRepository.CustomId<PermissionEo, String> {
    @Query("SELECT p FROM PermissionEo p")
    fun findAllPermissions(): List<PermissionEo>

    fun save(permissionEo: PermissionEo): PermissionEo {
        return session.merge(permissionEo)
    }

    fun saveAll(permissions: List<PermissionEo>): List<PermissionEo> {
        return permissions.map { session.merge(it) }
    }
}
