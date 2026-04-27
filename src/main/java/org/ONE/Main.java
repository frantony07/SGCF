package org.ONE;

import Functions.Authenticate;
import Functions.functionOfMain;
import org.ONE.config.FlyWayconfig;
import org.ONE.models.*;


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        FlyWayconfig.migrate();
        User user = new Authenticate().authenticateUser();
        new functionOfMain().menu(user);
    }
}
