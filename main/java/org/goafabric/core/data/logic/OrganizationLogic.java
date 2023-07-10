package org.goafabric.core.data.logic;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.goafabric.core.data.controller.vo.Organization;
import org.goafabric.core.data.logic.mapper.OrganizationMapper;
import org.goafabric.core.data.repository.OrganizationRepository;

import java.util.List;

@ApplicationScoped
@Transactional
public class OrganizationLogic {
    private final OrganizationMapper organizationMapper;

    private final OrganizationRepository organizationRepository;

    public OrganizationLogic(OrganizationMapper organizationMapper, OrganizationRepository organizationRepository) {
        this.organizationMapper = organizationMapper;
        this.organizationRepository = organizationRepository;
    }

    public Organization getById(String id) {
        return organizationMapper.map(
                organizationRepository.findById(id));
    }

    public void deleteById(String id) {
        organizationRepository.deleteById(id);
    }

    public List<Organization> findByName(String name) {
        return organizationMapper.map(
                organizationRepository.findByNameStartsWithIgnoreCase(name));
    }

    public Organization save(Organization organization) {
        return organizationMapper.map(organizationRepository.save(
                organizationMapper.map(organization)));
    }


}
