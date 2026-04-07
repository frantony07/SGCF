package org.ONE.config;

import org.flywaydb.core.Flyway;

public class FlyWayconfig {
    public static void migrate(){
        Flyway flyway = Flyway.configure().
                dataSource(
                        "jdbc:postgresql://localhost:5432/SGCF",
                        "postgres",
                        "leninha29"
                )
                .baselineOnMigrate(true).load();
        flyway.migrate();
    }

}
