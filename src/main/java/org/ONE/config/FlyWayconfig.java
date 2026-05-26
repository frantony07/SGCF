package org.ONE.config;

import org.flywaydb.core.Flyway;

public class FlyWayconfig {
    public static void migrate(){
        Flyway flyway = Flyway.configure().
                dataSource(
                        "jdbc:postgresql://localhost:8001/postgres",
                        "postgres",
                        "postgres"
                )
                .baselineOnMigrate(true).load();
        flyway.repair();
        flyway.migrate();
    }
}
