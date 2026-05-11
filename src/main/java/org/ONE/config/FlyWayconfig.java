package org.ONE.config;

import org.flywaydb.core.Flyway;

public class FlyWayconfig {
    public static void migrate(){
        Flyway flyway = Flyway.configure().
                dataSource(
                        "jdbc:postgresql://localhost:5432/SGCF",
                        "frantony07",
                        "santi14072005"
                )
                .baselineOnMigrate(true).load();
        flyway.repair();
        flyway.migrate();
    }

}
