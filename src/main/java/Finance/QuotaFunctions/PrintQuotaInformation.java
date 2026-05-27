package Finance.QuotaFunctions;

import org.ONE.model.entity.ENUM.Status;
import org.ONE.model.entity.QuotaModel;
import org.ONE.model.services.FuncionarioService;
import org.ONE.model.services.PayService;
import org.ONE.model.services.impl.QuotaServiceImpl;

import java.time.LocalDate;
import java.util.Scanner;

public class PrintQuotaInformation {
    Scanner sc = new Scanner(System.in);
    QuotaServiceImpl quotaServices = new QuotaServiceImpl();

    public void printEmployeeEarnings() {
        FuncionarioService funcionarioServices = new FuncionarioService();
        PayService payServices = new PayService();

        System.out.println("Digite o CPF do funcionário: ");
        String cpf = sc.next();
        Long employeeID = funcionarioServices.findIdByCPF(cpf);

        if (employeeID == null) {
            System.out.println("Não foi encontrado nenhum funcionário com este CPF.");
            return;
        }

        QuotaModel activeQuota = quotaServices.findActiveQuotaById(employeeID);
        if (activeQuota == null) {
            System.out.println("Este funcionário não possui meta ativa.");
            return;
        }

        LocalDate startDate = activeQuota.getStartDate();
        LocalDate endDate = activeQuota.getEndDate();

        double earned = payServices.sumEarningsByEmployee(
                employeeID,
                Status.CONFIRMADA,
                startDate,
                endDate
        );

        double target = activeQuota.getTargetValue();
        double progress = target <= 0 ? 0 : (earned / target) * 100.0;
        double remaining = Math.max(target - earned, 0);

        System.out.printf("Período: %s  →  %s%n", startDate, endDate);
        System.out.printf("Total faturado (CONFIRMADA): R$ %.2f%n", earned);
        System.out.printf("Meta: R$ %.2f%n", target);
        System.out.printf("Progresso: %.2f%%%n", progress);
        System.out.printf("Faltam: R$ %.2f%n", remaining);

        if (target <= earned) {
            int newQuota = 0;
            System.out.println("Meta completa, parabéns!");

            while (!sc.hasNextInt()) {
                System.out.println("Você gostaria de criar uma nova meta?");
                System.out.println("1. Sim");
                System.out.println("2. Não");
                sc.next();
            }
            newQuota = sc.nextInt();

            switch (newQuota) {
                case 1:
                    new CreateQuotas().updateEmployeeQuota();
                    return;
                case 2:
                    System.out.println("Retornando ao menu principal...");
                    return;
                default:
                    System.out.println("Digite um número entre 1 e 2!");

            }
        }
    }

    public void printCompanyQuota() {
        PayService payServices = new PayService();

        QuotaModel activeQuota = quotaServices.findActiveQuotaById(null);

        if (activeQuota == null) {
            System.out.println("A companhia não possui meta ativa.");
            return;
        }

        LocalDate startDate = activeQuota.getStartDate();
        LocalDate endDate = activeQuota.getEndDate();

        double earned = payServices.sumEarningsForCompany(
                Status.CONFIRMADA,
                startDate,
                endDate
        );

        double target = activeQuota.getTargetValue();
        double progress = target <= 0 ? 0 : (earned / target) * 100.0;
        double remaining = Math.max(target - earned, 0);

        System.out.printf("Período: %s  →  %s%n", startDate, endDate);
        System.out.printf("Total faturado (CONFIRMADA): R$ %.2f%n", earned);
        System.out.printf("Meta: R$ %.2f%n", target);
        System.out.printf("Progresso: %.2f%%%n", progress);
        System.out.printf("Faltam: R$ %.2f%n", remaining);

        if (target <= earned) {
            int newQuota = 0;
            System.out.println("Meta completa, parabéns!");

            while (!sc.hasNextInt()) {
                System.out.println("Você gostaria de criar uma nova meta?");
                System.out.println("1. Sim");
                System.out.println("2. Não");
                sc.next();
            }
            newQuota = sc.nextInt();

            switch (newQuota) {
                case 1:
                    new CreateQuotas().updateCompanyQuota();
                    return;
                case 2:
                    System.out.println("Retornando ao menu principal...");
                    return;
                default:
                    System.out.println("Digite um número entre 1 e 2!");
            }
        }
    }
}
