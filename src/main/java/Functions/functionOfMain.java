package Functions;

import java.util.Scanner;

public class functionOfMain {
    public  void authenticateUser(){
        Scanner sc = new Scanner(System.in);
        String usernameDefine = "admin";
        String passwordDefine ="1234";

        while (true) {
            System.out.println("digite seu nome de usuario");
            String userName = sc.next();
            System.out.println("digite sua senha");
            String password = sc.next();
            if(userName.equals(usernameDefine) && password.equals(passwordDefine)) {
                return;
            }
            System.out.println("usuario ou senha incorreta, tente novamente");

        }
    }
    public void menu(){
        System.out.println("bem vindo ao menu principal");
        System.out.println("1.cadastrastro");
        System.out.println("2.agendar reserva");
        System.out.println("3.mostrar reserva");
        System.out.println("4.financas");
    }
    public void
}
