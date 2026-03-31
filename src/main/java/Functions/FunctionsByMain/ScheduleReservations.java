package Functions.FunctionsByMain;

import Functions.SelectFunctions;
import People.Cliente;
import People.Funcionario;
import Tour.Passeio;

import java.util.ArrayList;
import java.util.Scanner;

public class ScheduleReservations {
    public void scheduleReservation(ArrayList<Cliente> clienteArrayList , ArrayList<Funcionario> funcionarioArrayList, ArrayList<Passeio> passeioArrayList) {
        Scanner sc = new Scanner(System.in);
        int passeioEscolhido = 0;

        boolean allValidateIsTrue = validateAllInstance(clienteArrayList,funcionarioArrayList,passeioArrayList);
        if(!allValidateIsTrue) {return;}

        System.out.println("--- Passeios disponiveis ---");
        for (int i = 0; i<passeioArrayList.size(); i++) {
            System.out.println((i+1) +" - ");
            passeioArrayList.get(i).printInformationOfTour();
        }
        System.out.println("Escolha um passeio");
        passeioEscolhido = sc.nextInt();

        Passeio passeioSelecionado = passeioArrayList.get(passeioEscolhido-1);

        Cliente cliente = new SelectFunctions().selectCliente(clienteArrayList);

        Funcionario funcionario = new SelectFunctions().selectFuncionario(funcionarioArrayList);

        passeioSelecionado.makeReservation(funcionario,cliente);

    }
    public boolean validateAllInstance(ArrayList<Cliente> clienteArrayList , ArrayList<Funcionario> funcionarioArrayList, ArrayList<Passeio> passeioArrayList){

        if (clienteArrayList == null ||clienteArrayList.isEmpty()){
            System.out.println("Não existe cliente ativo, por favor crie um novo cliente");
            return false;
        }
        if (funcionarioArrayList == null||funcionarioArrayList.isEmpty()){
            System.out.println("Não foi encontrado funcionário ativo, por favor crie um novo funcionário");
            return false;
        }
        if (passeioArrayList == null ||passeioArrayList.isEmpty()){
            System.out.println("Não foi encontrado passeio ativo, por favor crie um novo");
            return false;
        }
        return true;
    }
}
