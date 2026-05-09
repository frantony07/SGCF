package View;

import View.RegisterViews.CreateNewCustomer;
import View.RegisterViews.CreateNewEmployee;
import View.RegisterViews.CreateNewTour;
import View.RegisterViews.CreateNewUser;
import View.ShowViews.ShowCustomer;
import View.ShowViews.ShowEmployee;
import View.ShowViews.ShowTours;
import View.ShowViews.ShowUser;

import javax.swing.*;
import java.awt.*;

import static View.ItensDefault.addWindowButton;

public class MainScreens  extends JFrame {

    private JDesktopPane desktop;
    private JMenuBar menuBar = new JMenuBar();
    private CreateNewEmployee createNewEmployee;
    private CreateNewCustomer createNewCustomer;
    private CreateNewUser createNewUser;
    private CreateNewTour createNewTour;
    private ShowTours showTours;
    private JPanel taskBar;
    private ShowEmployee showEmployee;
    private ShowCustomer showCustomer;
    private ShowUser showUser;


    public MainScreens(){
        setTitle("SGCF");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);

        desktop = new JDesktopPane();
        desktop.setVisible(true);
        desktop.setBackground(new Color(0x5E5B41));
        desktop.setOpaque(true);

        var url = getClass().getResource("/icons/Waterfall.png");
        if (url != null) {
            setIconImage(new ImageIcon(url).getImage());
        } else {
            System.out.println("Imagem não encontrada");
        }
        setLayout(new BorderLayout());

        add(desktop, BorderLayout.CENTER);
        add(createTaskBar(), BorderLayout.SOUTH);


        NewRegister();
        ShowRegister();

        setVisible(true);
    }
    private void NewRegister(){

        JMenu menuCadastro = new JMenu("cadastros");
        menuCadastro.setFont(new Font("Arial",Font.BOLD,20));
        menuCadastro.setBackground(new Color(0x79616139, true));
        menuCadastro.setOpaque(true);

        menuCadastro.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        JMenuItem NewEmployee = new JMenuItem("Criar novo funcionario");
        NewEmployee.addActionListener(e -> new FunctionsOfWindowsRegister().CreatedNewEmployee(createNewEmployee,desktop,taskBar));
        menuCadastro.add(NewEmployee);

        JMenuItem newCustomer = new JMenuItem("Criar novo cliente");
        newCustomer.addActionListener(e -> new FunctionsOfWindowsRegister().CreateNewCustomer(createNewCustomer,desktop,taskBar));
        menuCadastro.add(newCustomer);

        JMenuItem newTour = new JMenuItem("Criar novo passeio");
        newTour.addActionListener(e -> new FunctionsOfWindowsRegister().CreateNewTour(createNewTour,desktop,taskBar));
        menuCadastro.add(newTour);

        JMenuItem newUser = new JMenuItem("Criar novo usuario");
        newUser.addActionListener(e -> new FunctionsOfWindowsRegister().CreateNewUser(createNewUser,desktop,taskBar));
        menuCadastro.add(newUser);

        menuBar.add(menuCadastro);
        setJMenuBar(menuBar);
    }
    private void ShowRegister(){
        JMenu menuOfShow = new JMenu("Mostrar registros");
        menuOfShow.setFont(new Font("Arial",Font.BOLD,20));
        menuOfShow.setBackground(new Color(0x79616139, true));
        menuOfShow.setOpaque(true);

        menuOfShow.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        JMenuItem showTours = new JMenuItem("Mostrar passeios");
        showTours.addActionListener(e -> ShowTours());
        menuOfShow.add(showTours);

        JMenuItem showEmployee = new JMenuItem("Mostrar Funcionários");
        showEmployee.addActionListener(e -> ShowEmployee());
        menuOfShow.add(showEmployee);

        JMenuItem showCustomer = new JMenuItem("Mostrar Clientes");
        showCustomer.addActionListener(e -> ShowCustomer());
        menuOfShow.add(showCustomer);

        JMenuItem showUser = new JMenuItem("Mostrar Usuarios");
        showUser.addActionListener(e -> ShowUser());
        menuOfShow.add(showUser);

        menuBar.add(menuOfShow);
        setJMenuBar(menuBar);

    }
    public void ShowTours(){
        if (showTours == null || showTours.isClosed()){

            showTours = new ShowTours();
            showTours.setVisible(true);

            desktop.add(showTours);

            showTours.toFront();

            addWindowButton(showTours,taskBar);

        }else {

            showTours.toFront();
        }
    }

    private void ShowEmployee(){
        if (showEmployee == null || showEmployee.isClosed()){

            showEmployee = new ShowEmployee();
            desktop.add(showEmployee);
            showEmployee.setVisible(true);
            showEmployee.toFront();

           addWindowButton(showEmployee, taskBar);

        } else {
            showEmployee.toFront();
        }
    }

    private void ShowCustomer(){
        if (showCustomer == null || showCustomer.isClosed()){

            showCustomer = new ShowCustomer();
            desktop.add(showCustomer);
            showCustomer.setVisible(true);
            showCustomer.toFront();

            addWindowButton(showCustomer, taskBar);

        } else {
            showCustomer.toFront();
        }
    }

    public void ShowUser() {
        if (showUser == null || showUser.isClosed()) {

            showUser = new ShowUser();
            showUser.setVisible(true);

            desktop.add(showUser);
            showUser.toFront();

            addWindowButton(showUser, taskBar);

        } else {
            showUser.toFront();
        }
    }

    private JPanel createTaskBar() {
        taskBar = new JPanel();
        taskBar.setLayout(new FlowLayout(FlowLayout.LEFT));
        taskBar.setBackground(new Color(40, 40, 40));
        taskBar.setPreferredSize(new Dimension(0, 40));
        return taskBar;
    }

}
