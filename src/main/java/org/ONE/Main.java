package org.ONE;

import View.MainScreens;
import jakarta.persistence.EntityManager;
import org.ONE.config.FlyWayconfig;
import org.ONE.repositories.CustomizerFactory;

public class Main {
    public static void main(String[] args) {
        FlyWayconfig.migrate();
         EntityManager entityManager = CustomizerFactory.getEntityManager();
         entityManager.close();
//        User user = new Authenticate().authenticateUser();
//        new functionOfMain().menu(user);
        MainScreens mainScreens = new MainScreens();
    }
}
