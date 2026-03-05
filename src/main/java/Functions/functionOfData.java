package Functions;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

public class functionOfData {
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

}
