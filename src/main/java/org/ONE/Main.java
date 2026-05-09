package org.ONE;

import View.MainScreens;
import org.ONE.config.FlyWayconfig;

public class Main {
    public static void main(String[] args) {
        FlyWayconfig.migrate();
//        User user = new Authenticate().authenticateUser();
//        new functionOfMain().menu(user);
        MainScreens mainScreens = new MainScreens();
    }
}
