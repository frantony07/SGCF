package org.ONE;

import Functions.Authenticate;
import Functions.functionOfMain;
import jakarta.persistence.EntityManager;
import org.ONE.models.*;
import org.ONE.models.ENUM.CountryCostumer;
import org.ONE.models.ENUM.CountryTour;
import org.ONE.models.ENUM.Language;
import org.ONE.repositories.*;

import java.lang.reflect.Array;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        new Authenticate().authenticateUser();
//        new functionOfMain().menu();
        EntityManager em = CustomizerFactory.getEntityManager();
        ClienteRepository clienteRepository = new ClienteRepository(em);
        Cliente cliente1 = new Cliente(Language.SPANISH , CountryCostumer.PARAGUAI,"80208997903" , "jose");
        clienteRepository.create(cliente1);

        Funcionario funcionario1 = new Funcionario("80108997904" ,"luis" ,  List.of(Language.SPANISH));
        FuncionarioRepository funcionarioRepository = new FuncionarioRepository(em);
        funcionarioRepository.create(funcionario1);

        Passeio passeio1 = new Passeio(75.5 ,50 , CountryTour.Argentina,"50","passeio das cataratas " , "parque nacional");
        PasseioRepository passeioRepository = new PasseioRepository(em);
        passeioRepository.create(passeio1);

        Reservations reservations1 = new Reservations(1L,"13/03/2026",1L,1L ,350);
        ReservationsRepositore rp = new ReservationsRepositore(em);
        rp.create(reservations1);

        PersonalAccount personalAccount1 = new PersonalAccount(1,1,"14/03/2026",250);
        PersonalAccountRepository par = new PersonalAccountRepository(em);
        par.create(personalAccount1);

        Pay pay1 = new Pay(List.of(1L) , 1 , 2000);
        PayRepository payRepository = new PayRepository(em);
        payRepository.create(pay1);
    }
}