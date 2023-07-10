package org.goafabric.core.data.logic;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.goafabric.core.data.controller.vo.Practitioner;
import org.goafabric.core.data.logic.mapper.PractitionerMapper;
import org.goafabric.core.data.repository.PractitionerRepository;

import java.util.List;

@ApplicationScoped
@Transactional
public class PractitionerLogic {
    private final PractitionerMapper practitionerMapper;

    private final PractitionerRepository practitionerRepository;

    public PractitionerLogic(PractitionerMapper practitionerMapper, PractitionerRepository practitionerRepository) {
        this.practitionerMapper = practitionerMapper;
        this.practitionerRepository = practitionerRepository;
    }

    public Practitioner getById(String id) {
        return practitionerMapper.map(
                practitionerRepository.findById(id));
    }

    public void deleteById(String id) {
        practitionerRepository.deleteById(id);
    }

    public List<Practitioner> findByGivenName(String givenName) {
        return practitionerMapper.map(
                practitionerRepository.findByGivenNameStartsWithIgnoreCase(givenName));
    }

    public List<Practitioner> findByFamilyName(String familyName) {
        return practitionerMapper.map(
                practitionerRepository.findByFamilyNameStartsWithIgnoreCase(familyName));
    }

    public Practitioner save(Practitioner practitioner) {
        return practitionerMapper.map(practitionerRepository.save(
                practitionerMapper.map(practitioner)));
    }

}
