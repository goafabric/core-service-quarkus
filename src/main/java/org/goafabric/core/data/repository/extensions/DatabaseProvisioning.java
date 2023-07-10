package org.goafabric.core.data.repository.extensions;

import io.quarkus.arc.Unremovable;
import io.quarkus.runtime.Quarkus;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.spi.CDI;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.goafabric.core.data.controller.vo.*;
import org.goafabric.core.data.controller.vo.types.AddressUse;
import org.goafabric.core.data.controller.vo.types.ContactPointSystem;
import org.goafabric.core.data.extensions.HttpInterceptor;
import org.goafabric.core.data.logic.OrganizationLogic;
import org.goafabric.core.data.logic.PatientLogic;
import org.goafabric.core.data.logic.PractitionerLogic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

@ApplicationScoped
@Unremovable
public class DatabaseProvisioning implements Runnable {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final String goals;

    private final Integer demoDataSize;

    private final String tenants;


    public DatabaseProvisioning(@ConfigProperty(name = "database.provisioning.goals", defaultValue = " ") String goals,
                                @ConfigProperty(name = "demo-data.size", defaultValue = "20") Integer demoDataSize,
                                @ConfigProperty(name = "multi-tenancy.tenants") String tenants) {
        this.goals = goals;
        this.demoDataSize = demoDataSize;
        this.tenants = tenants;

    }


    public void run() {
        if (goals.contains("-import-demo-data")) {
            log.info("Importing demo data ...");
            importDemoData();
        }

        if (goals.contains("-terminate")) {
            log.info("Terminating app ...");
            Quarkus.asyncExit();
        }
    }

    private void importDemoData() {
        Arrays.asList(tenants.split(",")).forEach(tenant -> {
            setTenantId(tenant);
            if (CDI.current().select(PatientLogic.class).get().findByFamilyName("").isEmpty()) {
                insertData();
            }
        });
    }

    private void insertData() {
        createPatients();
        createPractitioners();
        createOrganizations();
        //createArchiveFiles();
    }

    private void createPatients() {
        //Faker faker = new Faker();
        IntStream.range(0, demoDataSize).forEach(i ->
            CDI.current().select(PatientLogic.class).get().save(
                    createPatient("Bart", "Simpson",
                            createAddress("Springfield"),
                            createContactPoint("555-520"))
            )
        );
    }

    private void createPractitioners() {
        CDI.current().select(PractitionerLogic.class).get().save(
                createPractitioner("Dr. Julius", "Hibbert",
                        createAddress("Commonstreet 345"),
                        createContactPoint("555-520")
                )
        );

        CDI.current().select(PractitionerLogic.class).get().save(
                createPractitioner("Dr. Marvin", "Monroe",
                        createAddress("Psychstreet 104"),
                        createContactPoint("555-525")
                )
        );

        CDI.current().select(PractitionerLogic.class).get().save(
                createPractitioner("Dr. Nick", "Riveria",
                        createAddress("Nickstreet 221"),
                        createContactPoint("555-501")
                )
        );
    }

    private void createOrganizations() {
        CDI.current().select(OrganizationLogic.class).get().save(
                createOrganization("Practice Dr Hibbert",
                        createAddress("Hibbertstreet 4"),
                        createContactPoint("555-501")
                )
        );

        CDI.current().select(OrganizationLogic.class).get().save(
                createOrganization("Practice Dr Nick",
                        createAddress("Nickstreet 54"),
                        createContactPoint("555-501")
                )
        );
    }

    public static Patient createPatient(String givenName, String familyName, List<Address> addresses, List<ContactPoint> contactPoints) {
        return new Patient(null, givenName, familyName, "male", LocalDate.of(2020, 1, 8),
                addresses, contactPoints
        );
    }

    public static Practitioner createPractitioner(String givenName, String familyName, List<Address> addresses, List<ContactPoint> contactPoints) {
        return new Practitioner(null, givenName, familyName, "male", LocalDate.of(2020, 1, 8),
                addresses, contactPoints
        );
    }

    public static Organization createOrganization(String name, List<Address> addresses, List<ContactPoint> contactPoints) {
        return new Organization(null, name, addresses, contactPoints);
    }

    public static List<Address> createAddress(String street) {
        return Collections.singletonList(
                new Address(null, AddressUse.HOME.getValue(),street, "Springfield " + HttpInterceptor.getTenantId()
                        , "555", "Florida", "US"));
    }

    public static List<ContactPoint> createContactPoint(String phone) {
        return Collections.singletonList(new ContactPoint(null, AddressUse.HOME.getValue(), ContactPointSystem.PHONE.getValue(), phone));
    }


    public static void setTenantId(String tenantId) {
        HttpInterceptor.setTenantId(tenantId);
    }

}
