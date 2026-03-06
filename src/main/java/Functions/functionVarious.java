package Functions;

import People.Language;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class functionVarious {
    Scanner sc = new Scanner(System.in);
    public LocalDate writeData() {
        LocalDate data;
        try {
            System.out.println("Digite a data da reserva com o seguinte formato (2023-12-25)");
            String dataInString = sc.next();
            data = LocalDate.parse(dataInString);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return data;
    }
    public int validateNumber(int limitOfNumber){
        Scanner sc = new Scanner(System.in);
        boolean accept = true;

        while (accept){
            try{
                System.out.println("Digite um número de 1 até " + limitOfNumber);
                int number = sc.nextInt();

                if (number >= 1 && number <= limitOfNumber){
                    return number;
                }

                System.out.println("Por favor digite um número válido.");

            } catch (InputMismatchException e){
                System.out.println("Erro! Por favor digite um número válido.");
                sc.next();
            }
        }

        return 0;
    }
    public void selectLanguageMain(ArrayList<Language> languages) {

        Scanner sc = new Scanner(System.in);
        int optionMain = 0;

        while (true) {
            try {

                System.out.println("O funcionario fala alguma lingua estrangeira?");
                System.out.println("1. Sim");
                System.out.println("2. Não");

                optionMain = sc.nextInt();

                if (optionMain == 1) {
                    selectLanguageNotUsing(languages);
                    break;
                }

                if (optionMain == 2) {
                    languages.add(Language.PORTUGUESE);
                    break;
                }

                System.out.println("Opção inválida.");

            } catch (Exception e) {

                System.out.println("Erro: digite apenas números.");
                sc.nextLine(); // limpa o buffer

            }
        }
    }
    public void selectLanguageNotUsing(ArrayList<Language> languages){
        Scanner sc = new Scanner(System.in);
        System.out.println("escolhe a lingua que o funcionario fala");
        System.out.println("1." + Language.ENGLISH);
        System.out.println("2." + Language.SPANISH);
        System.out.println("3.o funcionario fala as duas linguas");
        int optionMain = sc.nextInt();
        switch (optionMain){
            case 1:
                languages.add(Language.ENGLISH);
                break;
            case 2:
                languages.add(Language.SPANISH);
                break;
            case 3:
                languages.add(Language.SPANISH);
                languages.add(Language.ENGLISH);
                break;
            default:
                System.out.println("opçao invalida");
        }
    }

}
