package org.goafabric.core.data.repository;

import org.goafabric.core.data.repository.entity.OrganizationEo;

import java.util.List;

public interface OrganizationRepository  {
    List<OrganizationEo> findByNameStartsWithIgnoreCase(String name);

    void deleteById(String id);

    OrganizationEo save(OrganizationEo map);

    OrganizationEo findById(String id);
}

