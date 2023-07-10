package org.goafabric.core.data.repository.extensions;

import io.quarkus.arc.Arc;
import io.quarkus.hibernate.orm.PersistenceUnitExtension;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.spi.CDI;
import jakarta.inject.Inject;
import org.eclipse.microprofile.config.ConfigProvider;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.flywaydb.core.Flyway;
import org.goafabric.core.data.extensions.HttpInterceptor;

import java.util.Arrays;
import java.util.Map;

@PersistenceUnitExtension
@RequestScoped
public class TenantResolver implements io.quarkus.hibernate.orm.runtime.tenant.TenantResolver {
    @Inject
    SchemaCreator schemaCreator;

    @ConfigProperty(name = "multi-tenancy.schema-prefix") String schemaPrefix;

    @Override
    public String getDefaultTenantId() {
        return "PUBLIC";
    }

    @Override
    public String resolveTenantId() {
        return schemaPrefix + HttpInterceptor.getTenantId();
    }

    @ApplicationScoped
    public static class SchemaCreator implements Runnable {

        @Override
        public void run() {
            Arc.container().requestContext().activate();
            if (ConfigProvider.getConfig().getValue("multi-tenancy.migration.enabled", Boolean.class)) {
                final Flyway flyway = CDI.current().select(Flyway.class).get();
                final String schemas = ConfigProvider.getConfig().getValue("multi-tenancy.tenants", String.class);
                final String schemaPrefix = ConfigProvider.getConfig().getValue("multi-tenancy.schema-prefix", String.class);
                Arrays.asList(schemas.split(",")).forEach(schema -> {
                            Flyway.configure()
                                    .configuration(flyway.getConfiguration())
                                    .schemas(schemaPrefix + schema)
                                    .defaultSchema(schemaPrefix + schema)
                                    .placeholders(Map.of("tenantId", schema))
                                    .load()
                                    .migrate();
                        }
                );
            }
        }
    }


}