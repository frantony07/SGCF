package Functions.FunctionsByMain;

import Functions.PrintError;
import Functions.ValidateNumber;
import People.Cliente;
import People.Funcionario;
import Tour.Passeio;

import java.util.ArrayList;

public class Recorde {
    public void displayRecorde(ArrayList<Cliente> clienteArrayList , ArrayList<Funcionario> funcionarioArrayList, ArrayList<Passeio> passeioArrayList){
        try {
            System.out.println("1. Mostrar funcionarios");
            System.out.println("2. Mostrar clientes");
            System.out.println("3. Mostrar passeios");
            System.out.println("4. Voltar ao menu");
            int option = new ValidateNumber().validateNumber(4);
            switch (option){
                case 1:
                    if (funcionarioArrayList == null ||funcionarioArrayList.isEmpty()){
                        System.out.println("Não foram encontrados funcionários ativos");
                        break;
                    }
                    for (Funcionario funcionario : funcionarioArrayList){
                        funcionario.printInformation();
                    }
                    break;
                case 2:
                    if (clienteArrayList == null ||clienteArrayList.isEmpty()){
                        System.out.println("Não foram encontrados clientes ativos");
                        break;
                    }
                    for (Cliente cliente : clienteArrayList){
                        cliente.printInformation();
                    }
                    break;
                case 3 :
                    if (passeioArrayList == null ||passeioArrayList.isEmpty()){
                        System.out.println("Não foram encontrados passeios ativos");
                        break;
                    }
                    for (Passeio passeio :passeioArrayList){
                        passeio.printInformationOfTour();
                    }

                    break;
                case 4:
                    System.out.println("Voltando ao menu");
                    return;

                default:
                    System.out.println("Opção invalida, voltando ao menu principal");
                    break;
            }

        } catch (Exception e) {
            PrintError.printErro(e);
        }
    }
}
