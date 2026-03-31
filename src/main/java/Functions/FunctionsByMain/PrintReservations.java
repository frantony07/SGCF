package Functions.FunctionsByMain;

import Functions.PrintError;
import Functions.ValidateNumber;
import People.Cliente;
import People.Funcionario;

import java.util.ArrayList;

public class PrintReservations {
    public void printReservation(ArrayList<Cliente> clienteArrayList , ArrayList<Funcionario> funcionarioArrayList) {
        try {
            System.out.println("1. Reservas de funcionários");
            System.out.println("2. Reserva de cliente");
            int optionReservation = new ValidateNumber().validateNumber(2);
            switch (optionReservation){
                case 1:
                    for (Funcionario funcionario : funcionarioArrayList){
                        funcionario.printReservation();
                    }
                    break;
                case 2 :
                    for (Cliente cliente : clienteArrayList){
                        cliente.printReservation();
                    }
                    break;
            }
        } catch (Exception e) {
            PrintError.printErro(e);
        }
    }
}
