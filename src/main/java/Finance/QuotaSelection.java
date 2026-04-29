package Finance;

import Finance.QuotaFunctions.CreateQuotas;
import Finance.QuotaFunctions.PrintQuotaInformation;
import Functions.PrintError;
import Functions.ValidateNumber;
import org.ONE.services.QuotaServices;

import java.util.Scanner;

public class QuotaSelection {
    QuotaServices quotaServices = new QuotaServices();

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
                        new CreateQuotas().createEmployeeQuota(quotaServices, sc);
                        break;
                    case 2:
                        new CreateQuotas().createCompanyQuota(quotaServices, sc);
                        break;
                    case 3:
                        new PrintQuotaInformation().printEmployeeEarnings(quotaServices, sc);
                        break;
                    case 4:
                        System.out.println("Retornando ao menu financeiro...");
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
