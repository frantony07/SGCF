package org.ONE;
import View.LoginScreens.Login;
import jakarta.persistence.EntityManager;
import org.ONE.config.FlyWayconfig;
import org.ONE.repositories.CustomizerFactory;

import javax.swing.*;
import javax.swing.UIDefaults;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
            UIDefaults defaults = UIManager.getLookAndFeelDefaults();
            defaults.put("nimbusBase",            new Color(0x1A6B8A));
            defaults.put("nimbusBlueGrey",        new Color(0x4A7B9B));
            defaults.put("control",               new Color(0xEFF5FA));
            defaults.put("nimbusLightBackground", new Color(0xFFFFFF));
        } catch (Exception ignored) {}

         FlyWayconfig.migrate();
         EntityManager entityManager = CustomizerFactory.getEntityManager();
         entityManager.close();

         SwingUtilities.invokeLater(Login::new);
    }
}
