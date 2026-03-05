package Functions;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;
import Tour.Passeio;

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
        Scanner sc = new Scanner(System.in);
        int opcaoMenu = 0;
        ArrayList<Passeio> passeio = new ArrayList<>();


        System.out.println("bem vindo ao menu principal");
        System.out.println("1.cadastrastro");
        System.out.println("2.agendar reserva");
        System.out.println("3.mostrar reserva");
        System.out.println("4.financas");
        opcaoMenu = sc.nextInt();

        switch(opcaoMenu){
            case 1:

                break;

            case 2:
                Object ArrayList;
                agendarReservas(passeio);
                break;

            case 3:
                break;

            case 4:
                break;

            default:
                System.out.println("Opção inválida, digite as opções existentes no menu");
                break;
        }
    }

    public void agendarReservas(ArrayList<Passeio> passeios) {
        Scanner sc = new Scanner(System.in);
        int passeioEscolhido = 0;

        System.out.println("--- Passeios disponiveis ---");
        for (Passeio passeio: passeios) {
            passeio.printInformationOfTour();
        }
        System.out.println("Escolha um passeio");
        passeioEscolhido = sc.nextInt();

    }
    public void register(){
        Scanner sc = new Scanner(System.in);
        boolean booleanMain = true;
        while (booleanMain){
            try {
            System.out.println("1.criar novo funcionario");
            System.out.println("2.criar novo cliente");
            System.out.println("3.criar novo passeio");
            String opcaoMenu = sc.next();

            switch ()

            }catch (Exception e){
                System.out.println("erro " + e);
            }

        }

    }

}
