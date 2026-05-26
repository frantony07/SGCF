package Finance;

import Functions.ValidateNumber;
import org.ONE.model.entity.ENUM.Status;
import org.ONE.model.entity.PayModel;
import org.ONE.model.repositories.PayRepository;
import org.ONE.model.services.PayService;

import java.util.List;
import java.util.Scanner;

public class Payment {
    Scanner sc = new Scanner(System.in);
    PayService payServices = new PayService();
    PayRepository payRepository = new PayRepository();

    public void makePayment  (){
        List<PayModel> payModelList = payRepository.getPayModelPendent();

        if (payModelList.isEmpty()){
            System.out.println("Não existem pagamentos pendentes.");
            return;
        }

        payServices.printPayPendent();

        System.out.println("Escolha o ID do pagamento a ser realizado.");

        Long choose = ValidateNumber.validateLong(payRepository.getSize());

        PayModel payChoose = payRepository.findById(choose);

        if (payChoose.getStatus() == Status.CANCELADA){
            System.out.println("Este pagamento está cancelado.");
            return;
        }
        if (payChoose.getStatus() == Status.CONFIRMADA){
            System.out.println("Este pagamento já foi realizado.");
            return;
        }
        System.out.println("O total a ser pago é de: R$" + payChoose.getTotal_account());

        System.out.println("Deseja confirmar o pagamento?");
        System.out.println("1.Sim");
        System.out.println("2.Não");

        int confirmPayment = ValidateNumber.validateINT(2);

        if (confirmPayment == 1){
            payChoose.setStatus(Status.CONFIRMADA);
            payRepository.update(payChoose);
            System.out.println("Pagamento realizado com sucesso.");
        }
    }
}
