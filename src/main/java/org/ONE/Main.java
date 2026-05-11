package org.ONE;

import Functions.Authenticate;
import Functions.functionOfMain;
import View.MainScreens;
import jakarta.persistence.EntityManager;
import org.ONE.config.FlyWayconfig;
import org.ONE.models.User;
import org.ONE.repositories.CustomizerFactory;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        FlyWayconfig.migrate();
         EntityManager entityManager = CustomizerFactory.getEntityManager();
         entityManager.close();
//       User user = new Authenticate().authenticateUser();
//       new functionOfMain().menu(user);
        SwingUtilities.invokeLater(MainScreens::new);
    }
}
