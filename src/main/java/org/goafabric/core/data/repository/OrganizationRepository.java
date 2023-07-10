package org.goafabric.core.data.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import org.goafabric.core.data.repository.entity.OrganizationEo;

import java.util.List;

@ApplicationScoped
public class OrganizationRepository implements PanacheRepositoryBase<OrganizationEo, String> {

    public List<OrganizationEo> findByNameStartsWithIgnoreCase(String name) {
        return find("name", name).list();
    }

    public OrganizationEo save(OrganizationEo person) {
        persist(person);
        return person;
    }

}

