package Finance;

import Functions.ValidateNumber;

import java.util.ArrayList;
import java.util.Scanner;

public class MainAccount {
    Scanner sc = new Scanner(System.in);

    public void mainPagamento() {
        int menu = 0;
        int count = 0;
        boolean mainOption = true;

        while (mainOption) {
            System.out.println("Menu do caixa: ");
            for (int i = Ledger.getPayments().size() - 1; i >= 0 && count < 5; i--) {
                System.out.println(Ledger.getPayments().get(i));
                count++;
            }

            System.out.println("1. Adicionar");
            System.out.println("2. Subtrair");
            System.out.println("3. Recebimentos");
            System.out.println("4. Metas");
            System.out.println("5. Deletar um registro");
            System.out.println("6. Atualizar um registro");
            System.out.println("7. Voltar");

            menu = new ValidateNumber().validateINT(7);

            switch (menu) {
                case 1:
                    // new Arithmetic().addMoney(Ledger.getPayments());
                    break;
                case 2:
                    // new Arithmetic().subtractMoney(Ledger.getPayments());
                    break;
                case 3:
                    // new Filters().receipts(Ledger.getPayments());
                    break;
                case 4:
                    // new QuotasFunctions().quota(Ledger.getPayments(), sc);
                    break;
                case 5:
                    // new SCRUDofRegister().deleteRegister(Ledger.getPayments(), sc);
                    break;
                case 6:
                    // new SCRUDofRegister().updateRegister(Ledger.getPayments(), sc);
                    break;
                case 7:
                    mainOption = false;
                    System.out.println("Saindo das finanças");
                    return;
                default:
                    System.out.println("Por favor digite um número entre 1 e 7.");
            }
        }
    }

    public ArrayList<Ledger> getPayments() {
        return Ledger.getPayments();
    }
}
