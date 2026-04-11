package Functions;

import org.ONE.models.Cliente;
import org.ONE.models.ENUM.CountryCostumer;
import org.ONE.models.ENUM.Language;
import org.ONE.models.ENUM.CountryTour;
import org.ONE.models.Funcionario;
import org.ONE.repositories.ClienteRepository;
import org.ONE.repositories.FuncionarioRepository;
import org.ONE.services.ClienteServices;
import org.ONE.services.FuncionarioServices;
import org.ONE.services.PasseioServices;


import java.util.ArrayList;
import java.util.Scanner;

public class SelectFunctions {
    FuncionarioServices funcionarios = new FuncionarioServices();
    ClienteServices clientes = new ClienteServices();
    PasseioServices passeios = new PasseioServices();

    public void selectLanguageMain(ArrayList<Language> languages, String classification) {

        Scanner sc = new Scanner(System.in);
        int optionMain = 0;

        while (true) {
            try {
                System.out.println("O " + classification  + " fala alguma língua estrangeira?");
                System.out.println("1. Sim");
                System.out.println("2. Não");
                optionMain = sc.nextInt();
                if (optionMain == 1) {
                    selectLanguageNotUsing(languages, classification);
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
     private void selectLanguageNotUsing(ArrayList<Language> languages, String classification){
        try {
            System.out.println("Escolha a língua que o " + classification + " fala:");
            System.out.println("1. " + Language.ENGLISH);
            System.out.println("2. " + Language.SPANISH);
            System.out.println("3. O " + classification +" fala as duas línguas");
            int optionMain =  ValidateNumber.validateINT(3);
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
        int optionMain = ValidateNumber.validateINT(5);
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
            System.out.println("Opção inválida");
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

            int optionMenu = ValidateNumber.validateINT(3);

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

    public long selectFuncionario(){
        try{
            System.out.println("Selecione o ID do funcionário escolhido");
            funcionarios.findAll().forEach(System.out::println);
            return   new ValidateNumber().validateLong(funcionarios.getSize());

        }catch(Exception e){
            PrintError.printErro(e);
        }

        return 0;
    }

    public long selectCliente() {
        try {
            System.out.println("Selecione o cliente:");
            clientes.findAll().forEach(System.out::println);
            return  new ValidateNumber().validateLong(clientes.getSize());

        } catch (Exception e) {
            PrintError.printErro(e);
        }

        return  0;
    }

    public Long selectPasseio(){
        try {
            System.out.println("Selecione o passeio.");
            passeios.findAll().forEach(System.out::println);
            return new ValidateNumber().validateLong(passeios.getSize());
        } catch (Exception e) {
            PrintError.printErro(e);
        }
        return 0L;
    }
}