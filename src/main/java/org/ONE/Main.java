package org.ONE;

import Functions.Authenticate;
import Functions.functionOfMain;
import org.ONE.config.FlyWayconfig;
import org.ONE.models.*;

public class Main {
    public static void main(String[] args) {
        FlyWayconfig.migrate();
        User user = new Authenticate().authenticateUser();
        new functionOfMain().menu(user);
    }
}
