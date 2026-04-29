package Finance;

import Functions.PrintError;
import Functions.ValidateNumber;

import java.util.Scanner;

public class QuotaSelection {

    public void quotaCreation(Scanner sc) {
        try {
            int metaOption = 0;
            boolean quotaMenu = true;

            while (quotaMenu) {
                System.out.println("1. Meta pessoal");
                System.out.println("2. Meta da empresa");
                System.out.println("3. Mostrar metas");
                System.out.println("4. Retornar");
                metaOption = ValidateNumber.validateINT(4);

                switch (metaOption) {
                    case 1:
                        System.out.println("Insira o valor da sua meta: ");
                        sc.nextInt();


                        break;
                    case 2:
                        System.out.println("Insira o valor da meta da empresa: ");
                        sc.nextInt();

                        break;
                    case 3:
                        System.out.println("Metas ativas: ");
                        break;
                    case 4:
                        return;
                    default:
                        System.out.println("Erro! Digite um número entre 1 e 4.");
                        break;
                }
            }
        } catch (Exception err) {
            PrintError.printErro(err);
        }
    }
}
