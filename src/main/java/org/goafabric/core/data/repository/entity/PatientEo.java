package org.goafabric.core.data.repository.entity;

import jakarta.persistence.*;
import org.goafabric.core.data.repository.extensions.AuditListener;

import java.time.LocalDate;

@Entity
@Table(name = "patient")
@EntityListeners(AuditListener.class)
public class PatientEo {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    public String id;

    //@TenantId
    public String orgunitId;

    public String givenName;

    public String familyName;

    public String gender;

    public LocalDate birthDate;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    public AddressEo address;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "contact_point_id", referencedColumnName = "id")
    public ContactPointEo contactPoint;

    @Version //optimistic locking
    public Long version;

}
