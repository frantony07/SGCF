package Finance.QuotaFunctions;

import org.ONE.models.ENUM.Status;
import org.ONE.models.QuotaModel;
import org.ONE.services.FuncionarioServices;
import org.ONE.services.PayServices;
import org.ONE.services.QuotaServices;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Scanner;

public class PrintQuotaInformation {
    public void printEmployeeEarnings(QuotaServices quotaServices, Scanner sc) {
        FuncionarioServices funcionarioServices = new FuncionarioServices();
        PayServices payServices = new PayServices();

        System.out.println("Digite o CPF do funcionário: ");
        String cpf = sc.next();
        Long employeeID = funcionarioServices.findIdByCPF(cpf);

        if (employeeID == null) {
            System.out.println("Não foi encontrado nenhum funcionário com este CPF.");
            return;
        }

        QuotaModel activeQuota = findActiveQuotaForEmployee(quotaServices, employeeID);

        if (activeQuota == null) {
            System.out.println("Este funcionário não possui meta ativa.");
            return;
        }

        LocalDateTime startDate = activeQuota.getStartDate();
        LocalDateTime endDate = activeQuota.getEndDate();

        double earned = payServices.sumEarningsByEmployee(
                employeeID,
                Status.CONFIRMADA.name(),
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
    }

    private QuotaModel findActiveQuotaForEmployee(QuotaServices quotaServices, Long employeeID) {
        LocalDateTime now = LocalDateTime.now();

        return quotaServices.findAllQuotas().stream()
                .filter(q -> employeeID.equals(q.getIdFuncionario()))
                .filter(q -> q.getStartDate() != null && q.getEndDate() != null)
                .filter(q -> !now.isBefore(q.getStartDate()) && !now.isAfter(q.getEndDate()))
                .max(Comparator.comparing(QuotaModel::getStartDate))
                .orElse(null);
    }
}
