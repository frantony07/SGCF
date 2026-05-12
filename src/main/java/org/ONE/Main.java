package org.ONE;
import View.LoginScreens.Login;
import jakarta.persistence.EntityManager;
import org.ONE.config.FlyWayconfig;
import org.ONE.repositories.CustomizerFactory;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
         FlyWayconfig.migrate();
         EntityManager entityManager = CustomizerFactory.getEntityManager();
         entityManager.close();

         SwingUtilities.invokeLater(Login::new);
    }
}
