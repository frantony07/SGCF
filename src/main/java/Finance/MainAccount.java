package Finance;

import Functions.PrintError;
import Functions.ValidateNumber;
import org.ONE.models.ENUM.Status;
import org.ONE.models.PayModel;
import org.ONE.models.Reservations;
import org.ONE.services.PayServices;
import org.ONE.services.ReservationsServices;
import java.util.Scanner;

public class MainAccount {
    Scanner sc = new Scanner(System.in);
    private final ReservationsServices reservationsServices = new ReservationsServices();

    public void mainPagamento() {
        int menu = 0;
        int count = 0;

        try {
            while (true) {
                System.out.println("Menu do caixa: ");
                new PayServices().quickGetPay();

                System.out.println("1. Recibos de tours confirmados");
                System.out.println("2. Histórico de tours pendentes");
                System.out.println("3. Histórico de tours cancelados");
                System.out.println("4. Metas");
                System.out.println("5. Mostrar todas as reservas");
                System.out.println("6. Realizar pagamento");
                System.out.println("7. Voltar");

                menu = ValidateNumber.validateINT(6);

                switch (menu) {
                    case 1:
                        reservationsServices.getReservationsConfirmate().forEach(System.out::println);
                        break;
                    case 2:
                        reservationsServices.printReceipt(Status.PENDENTE.name());
                        break;
                    case 3:
                        reservationsServices.printReceipt(Status.CANCELADA.name());
                        break;
                    case 4:
                        new QuotaSelection().quotaCreation();
                        break;
                    case 5:
                        reservationsServices.getReservationsWithPaymentStatus().forEach(row -> {
                            Object[] col = (Object[]) row;
                            Reservations r = (Reservations) col[0];
                            PayModel p = (PayModel) col[1];

                            System.out.println(
                                    "Reserva ID: " + (r != null ? r.getId() : "N/A") +
                                            " | Data: " + (r != null ? r.getDate() : "----------") +
                                            " | Total: R$ " + p.getTotal_account() +
                                            " | Status: " + p.getStatus()
                            );
                        });
                        break;

                    case 6 :
                            new Payment().makePayment();
                        break;
                    case 7:
                        System.out.println("Saindo das finanças");
                        return;
                    default:
                        System.out.println("Por favor digite um número entre 1 e 6.");
                        break;
                }
            }
        } catch (Exception err) {
            PrintError.printErro(err);
        }
    }
}
