package Functions;

import java.util.Scanner;

public class Authenticate {
    public  void authenticateUser(){
        Scanner sc = new Scanner(System.in);
        String usernameDefine = "admin";
        String passwordDefine ="1234";


        while (true) {
            System.out.println("Digite seu nome de usuário");
            String userName = sc.next();
            System.out.println("Digite sua senha");
            String password = sc.next();
            if(userName.equals(usernameDefine) && password.equals(passwordDefine)) {
                return;
            }
            System.out.println("Usuário ou senha incorreta, tente novamente");

        }
    }
}
