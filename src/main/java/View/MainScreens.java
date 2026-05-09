package View;

import View.RegisterViews.CreateNewCustomer;
import View.RegisterViews.CreateNewEmployee;
import View.RegisterViews.CreateNewTour;
import View.RegisterViews.CreateNewUser;

import javax.swing.*;
import java.awt.*;

public class MainScreens  extends JFrame {

    private JDesktopPane desktop;
    private JMenuBar menuBar = new JMenuBar();
    private CreateNewEmployee createNewEmployee;
    private CreateNewCustomer createNewCustomer;
    private CreateNewUser createNewUser;
    private CreateNewTour createNewTour;

    public MainScreens(){
        setTitle("SGCF");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        desktop = new JDesktopPane();
        desktop.setVisible(true);
        desktop.setBackground(new Color(0x272618));
        desktop.setOpaque(true);
        add(desktop);

        NewRegister();
    }
    private void NewRegister(){

        JMenu menuCadastro = new JMenu("cadastros");

        JMenuItem NewEmployee = new JMenuItem("Criar novo funcionario");
        NewEmployee.addActionListener(e -> CreatedNewEmployee());
        menuCadastro.add(NewEmployee);

        JMenuItem newCustomer = new JMenuItem("Criar novo cliente");
        newCustomer.addActionListener(e -> CreateNewCustomer());
        menuCadastro.add(newCustomer);

        JMenuItem newTour = new JMenuItem("Criar novo passeio");
        newTour.addActionListener(e -> CreateNewTour());
        menuCadastro.add(newTour);

        JMenuItem newUser = new JMenuItem("Criar novo usuario");
        newUser.addActionListener(e -> CreateNewUser());
        menuCadastro.add(newUser);

        menuBar.add(menuCadastro);
        setJMenuBar(menuBar);
    }
    private void CreatedNewEmployee(){
        if (createNewEmployee == null || createNewEmployee.isClosed()){

            createNewEmployee = new CreateNewEmployee();
            createNewEmployee.setVisible(true);
            createNewEmployee.toFront();

            desktop.add(createNewEmployee);

        }else {

            createNewEmployee.toFront();
        }
    }

    private void CreateNewCustomer(){
        if (createNewCustomer == null || createNewCustomer.isClosed()){

            createNewCustomer = new CreateNewCustomer();
            createNewCustomer.setVisible(true);
            createNewCustomer.toFront();

            desktop.add(createNewCustomer);

        }else {

            createNewCustomer.toFront();
        }
    }
    private void CreateNewTour(){
        if (createNewTour == null || createNewTour.isClosed()){

            createNewTour= new CreateNewTour();
            createNewTour.setVisible(true);
            createNewTour.toFront();

            desktop.add(createNewTour);

        }else {

            createNewTour.toFront();
        }
    }
    private void CreateNewUser(){
        if (createNewUser == null || createNewUser.isClosed()){

            createNewUser = new CreateNewUser();
            createNewUser.setVisible(true);
            createNewUser.toFront();

            desktop.add(createNewUser);

        }else {

            createNewUser.toFront();
        }
    }
}
