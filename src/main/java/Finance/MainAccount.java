package Finance;

import Functions.ValidateNumber;
import org.ONE.models.ENUM.Status;
import org.ONE.services.PayServices;
import org.ONE.services.ReservationsServices;
import java.util.Scanner;

public class MainAccount {
    Scanner sc = new Scanner(System.in);
    private final ReservationsServices reservationsServices = new ReservationsServices();

    public void mainPagamento() {
        int menu = 0;
        int count = 0;
        boolean mainOption = true;

        while (mainOption) {
            System.out.println("Menu do caixa: ");
            new PayServices().quickGetPay();

            System.out.println("1. Recibos de tours confirmados");
            System.out.println("2. Histórico de tours pendentes");
            System.out.println("3. Histórico de tours cancelados ");
            System.out.println("4. Metas");
            System.out.println("0. Voltar");

            menu = new ValidateNumber().validateINT(7);

            switch (menu) {
                case 1:
                    reservationsServices.printReceipt(Status.CONFIRMADA.name());
                break;
                case 2:
                    reservationsServices.printReceipt(Status.PENDENTE.name());
                break;
                case 3:
                    reservationsServices.printReceipt(Status.CANCELADA.name());
                break;
                case 4:
                    // To be added
                    System.out.println("To be added");
                break;
                case 0:
                    mainOption = false;
                    System.out.println("Saindo das finanças");
                    return;
                default:
                    System.out.println("Por favor digite um número entre 1 e 7.");
            }
        }
    }
}
