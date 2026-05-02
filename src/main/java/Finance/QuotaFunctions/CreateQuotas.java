package Finance.QuotaFunctions;

import org.ONE.models.QuotaModel;
import org.ONE.services.FuncionarioServices;
import org.ONE.services.QuotaServices;

import java.time.LocalDate;
import java.util.Scanner;

public class CreateQuotas {
    public void createEmployeeQuota(QuotaServices quotaServices, Scanner sc) {
        FuncionarioServices funcionarioServices = new FuncionarioServices();

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
        LocalDate employeeInitialDate = LocalDate.now();
        LocalDate employeeFinalDate = employeeInitialDate.plusDays(30);
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

    public void createCompanyQuota(QuotaServices quotaServices, Scanner sc) {
        System.out.println("Insira o valor da meta da empresa: ");
        while (!sc.hasNextDouble()) {
            System.out.println("Digite um valor válido.");
            sc.next();
        }
        double companyTargetValue = sc.nextDouble();

        System.out.println("Valor da meta criada: " + companyTargetValue);
        LocalDate companyInitialDate = LocalDate.now();
        LocalDate companyFinalDate = companyInitialDate.plusDays(30);
        System.out.println("A data limite para a meta é de 30 dias em: " + companyFinalDate);

        quotaServices.createQuota(
                new QuotaModel(
                        companyInitialDate,
                        companyFinalDate,
                        companyTargetValue
                )
        );
    }

    public void updateEmployeeQuota(QuotaServices quotaServices, Scanner sc) {
        FuncionarioServices funcionarioServices = new FuncionarioServices();

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

        System.out.println("Valor da meta atualizada: " + employeeTargetValue);
        LocalDate employeeInitialDate = LocalDate.now();
        LocalDate employeeFinalDate = employeeInitialDate.plusDays(30);
        System.out.println("A data limite para a meta é de 30 dias em: " + employeeFinalDate);

        quotaServices.updateQuota(
                new QuotaModel(
                        employeeInitialDate,
                        employeeFinalDate,
                        employeeTargetValue,
                        employeeID
                )
        );
    }

    public void updateCompanyQuota(QuotaServices quotaServices, Scanner sc) {
        System.out.println("Insira o valor da sua meta: ");
        while (!sc.hasNextDouble()) {
            System.out.println("Digite um valor válido.");
            sc.next();
        }
        double companyTargetValue = sc.nextDouble();

        System.out.println("Valor da meta atualizada: " + companyTargetValue);
        LocalDate companyInitialDate = LocalDate.now();
        LocalDate companyFinalDate = companyInitialDate.plusDays(30);
        System.out.println("A data limite para a meta é de 30 dias em: " + companyFinalDate);

        quotaServices.updateQuota(
                new QuotaModel(
                        companyInitialDate,
                        companyFinalDate,
                        companyTargetValue
                )
        );
    }
}
