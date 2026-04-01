package Functions;

import java.util.Scanner;

public class ValidateNumber {
    public int validateNumber(int limitOfNumber){
        Scanner sc = new Scanner(System.in);

        while (true){
            try{
                System.out.println("Digite um número de 1 até " + limitOfNumber);
                int number = sc.nextInt();

                if (number >= 1 && number <= limitOfNumber){
                    return number;
                }

                System.out.println("Por favor digite um número válido.");

            } catch (Exception e){
                PrintError.printErro(e);
                sc.next();
            }
        }
    }
}
