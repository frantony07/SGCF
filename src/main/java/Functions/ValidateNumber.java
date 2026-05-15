package Functions;

import java.util.Scanner;

public class ValidateNumber {
    public static int validateINT(int limitOfNumber){
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

    public static long validateLong(long limitOfNumber){
        Scanner sc = new Scanner(System.in);

        while (true){
            try{
                System.out.println("Digite um número de 1 até " + limitOfNumber);
                long number = sc.nextLong();

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

    public static float validateFloat() {
        Scanner sc = new Scanner(System.in);

        while(true){
         try {
            while (!sc.hasNextFloat()) {
                System.out.println("Valor nao valido, digite um valor float");
                sc.next();
            }
             float value = sc.nextFloat();
             return value;
    } catch (Exception e) {
             PrintError.printErro(e);
             sc.next();
         }
     }
    }

}
