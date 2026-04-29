package Finance;

import Functions.PrintError;
import Functions.ValidateNumber;
import org.ONE.models.QuotaModel;
import org.ONE.services.FuncionarioServices;
import org.ONE.services.QuotaServices;

import java.time.LocalDateTime;
import java.util.Scanner;

public class QuotaSelection {
    QuotaServices quotaServices = new QuotaServices();
    FuncionarioServices funcionarioServices = new FuncionarioServices();

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
                        createEmployeeQuota(quotaServices, funcionarioServices, sc);
                        break;
                    case 2:
                        System.out.println("Insira o valor da meta da empresa: ");
                        while (!sc.hasNextDouble()) {
                            System.out.println("Digite um valor válido.");
                            sc.next();
                        }
                        double companyTargetValue = sc.nextDouble();

                        System.out.println("Valor da meta criada: " + companyTargetValue);
                        LocalDateTime companyInitialDate = LocalDateTime.now();
                        LocalDateTime companyFinalDate = companyInitialDate.plusDays(30);
                        System.out.println("A data limite para a meta é de 30 dias em: " + companyFinalDate);

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

    public void createEmployeeQuota(QuotaServices quotaServices, FuncionarioServices funcionarioServices, Scanner sc) {
        System.out.println("Digite o CPF do funcionário: ");
        String cpf = sc.next();
        Long employeeID = funcionarioServices.findIdByCPF(cpf);

        if (employeeID == null) {
            System.out.println("Não foi encontrado nenhum funcionário com este CPF.");
            return;
        }

        System.out.println("Insira o valor da sua meta: ");
        while (!sc.hasNextDouble()) {
            System.out.println("Digite um valor válido.");
            sc.next();
        }
        double employeeTargetValue = sc.nextDouble();

        System.out.println("Valor da meta criada: " + employeeTargetValue);
        LocalDateTime employeeInitialDate = LocalDateTime.now();
        LocalDateTime employeeFinalDate = employeeInitialDate.plusDays(30);
        System.out.println("A data limite para a meta é de 30 dias em: " + employeeFinalDate);

        quotaServices.createQuota(
                new QuotaModel(
                        employeeInitialDate,
                        employeeFinalDate,
                        employeeTargetValue,
                        employeeID
                )
        );
    }

}
