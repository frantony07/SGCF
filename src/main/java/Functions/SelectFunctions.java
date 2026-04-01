package Functions;

import People.Cliente;
import People.CountryCostumer;
import People.Funcionario;
import People.Language;
import Tour.CountryTour;


import java.util.ArrayList;
import java.util.Scanner;

public class SelectFunctions {
    public void selectLanguageMain(ArrayList<Language> languages, String classification) {

        Scanner sc = new Scanner(System.in);
        int optionMain = 0;

        while (true) {
            try {
                System.out.println("O" + classification  + " fala alguma lingua estrangeira?");
                System.out.println("1. Sim");
                System.out.println("2. Não");
                optionMain = sc.nextInt();
                if (optionMain == 1) {
                    selectLanguageNotUsing(languages);
                    break;
                    }
                if (optionMain == 2) {
                    languages.add(Language.PORTUGUESE);
                    break;
                    }
                System.out.println("Opção inválida.");
                } catch (Exception e) {
                    PrintError.printErro(e);
                    sc.nextLine();
                }
        }
    }
     private void selectLanguageNotUsing(ArrayList<Language> languages){
        try {
            System.out.println("Escolha a língua que o funcionário fala");
            System.out.println("1. " + Language.ENGLISH);
            System.out.println("2. " + Language.SPANISH);
            System.out.println("3. O funcionario fala as duas lánguas");
            int optionMain = new ValidateNumber().validateNumber(3);
            switch (optionMain){
                case 1:
                    languages.add(Language.ENGLISH);
                    break;
                case 2:
                    languages.add(Language.SPANISH);
                    break;
                case 3:
                    languages.add(Language.SPANISH);
                    languages.add(Language.ENGLISH);
                    break;
                default:
                    System.out.println("Opção inválida");
                    }
                } catch (Exception e) {
                 PrintError.printErro(e);
                    }
    }

    public CountryCostumer SelectCountryOfCostumer(){
        try {
        System.out.println("Selecione o país do cliente");
        System.out.println("1." + CountryCostumer.BRAZIL);
        System.out.println("2." + CountryCostumer.UNITED_STATES);
        System.out.println("3." + CountryCostumer.INDIAN);
        System.out.println("4." + CountryCostumer.EUROPE);
        int optionMain = new ValidateNumber().validateNumber(5);
        switch (optionMain){
            case 1 :
                return CountryCostumer.BRAZIL;
            case 2 :
                return CountryCostumer.UNITED_STATES;
            case 3 :
                return CountryCostumer.INDIAN;
            case 4 :
                return CountryCostumer.EUROPE;
            default:
            System.out.println("opção invalida");
                }

            } catch (Exception e) {
            PrintError.printErro(e);
                }
        return CountryCostumer.UNITED_STATES;
    }

    public CountryTour selecteCountryTour(){
        try {
            System.out.println("Selecione o país do passeio");
            System.out.println("1." + CountryTour.Argentina);
            System.out.println("2." + CountryTour.Brasil);
            System.out.println("3." + CountryTour.Paraguay);

            int optionMenu = new ValidateNumber().validateNumber(3);

            switch (optionMenu){
                case 1:
                    return CountryTour.Argentina;
                case 2:
                    return CountryTour.Brasil;
                case 3 :
                    return CountryTour.Paraguay;
                default:
                    System.out.println("Opção inválida");
                }

            } catch (Exception e) {
            PrintError.printErro(e);

            }

        return CountryTour.Brasil;
    }

    public Funcionario selectFuncionario(ArrayList<Funcionario> funcionarios){
        if(funcionarios == null || funcionarios.isEmpty()){
            System.out.println("Não há funcionários cadastrados.");
            return null;
        }
        try{
            System.out.println("Seleciona o funcionário escolhido");
            for(int i = 0; i < funcionarios.size(); i++){
                System.out.println((i+1) + ". " + funcionarios.get(i).getName());
            }
            int funcionarioSelected = new ValidateNumber().validateNumber(funcionarios.size());
            return funcionarios.get(funcionarioSelected - 1);
        }catch(Exception e){
            PrintError.printErro(e);
        }

        return null;
    }

    public Cliente selectCliente(ArrayList<Cliente> clientes) {
        if(clientes == null || clientes.isEmpty()){
            System.out.println("Não há clientes cadastrados.");
            return null;
        }
        try {
            System.out.println("Selecione o cliente:");
            for (int i = 0; i < clientes.size(); i++) {
                System.out.println((i + 1) + ". " + clientes.get(i).getName());
            }

            int clienteSelecionado = new ValidateNumber().validateNumber(clientes.size());

            return clientes.get(clienteSelecionado - 1);
        } catch (Exception e) {
            PrintError.printErro(e);
        }

        return  null;
    }
}