package Finance;

import Functions.ValidateNumber;
import org.ONE.models.ENUM.Status;
import org.ONE.models.PayModel;
import org.ONE.repositories.PayRepository;
import org.ONE.services.PayServices;

import java.util.List;
import java.util.Scanner;

public class Payment {
    Scanner sc = new Scanner(System.in);
    PayServices payServices = new PayServices();
    PayRepository payRepository = new PayRepository();

    public void makePayment  (){
        payServices.printPayPendent();
        List<PayModel> payModelList = payRepository.getPayModelPendent();

        if (payModelList.isEmpty()){
            System.out.println("nao existem pagos pendentes");
        }

        System.out.println("escolhe o  id pagamento a ser realizado");

        Long choose = ValidateNumber.validateLong(payRepository.getSize());

        PayModel payChoose = payRepository.findById(choose);

        if (payChoose.getStatus() == Status.cancelada){
            System.out.println("este pagamento esta cancelado");
            return;
        }
        if (payChoose.getStatus() == Status.confirmada){
            System.out.println("este pagamento ja foi realizado");
            return;
        }
        System.out.println("o total a ser pago e = R$" + payChoose.getTotal_account());

        System.out.println("deseja confirmar o pagamento?");
        System.out.println("1.Sim");
        System.out.println("2.Não");

        int confirmatePayment = ValidateNumber.validateINT(2);

        if (confirmatePayment == 1){
            payChoose.setStatus(Status.confirmada);
            payRepository.update(payChoose);
            System.out.println("pagamento realizado com sucesso.");
        }



    }
}
