package View;

import View.FinanceView.QuotasPanel;
import View.RegisterViews.CreateNewCustomer;
import View.RegisterViews.CreateNewEmployee;
import View.RegisterViews.CreateNewTour;
import View.RegisterViews.CreateNewUser;
import View.RegisterViews.ScheduleReservationWindow;
import View.ShowViews.*;

import javax.swing.*;
import java.awt.*;

import static View.FunctionOfWindowsEdit.*;

import static View.FunctionsOfWindowsFinances.*;
import static View.FunctionsOfWindowsRegister.*;
import static View.FunctionsOfWindowsShow.*;

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
    private ShowReservations showReservations;
    private ScheduleReservationWindow scheduleReservationWindow;
    private EditReservationWindow editReservationWindow;
    private EditStatusReservationWindow editStatusReservationWindow;
    private ShowConfirmPaymentFrame confirmPaymentPanel;
    private QuotasPanel quotasPanel;


    public MainScreens(){
        setTitle("SGCF");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);

        desktop = new JDesktopPane();
        desktop.setVisible(true);
        desktop.setBackground(new Color(0x9E9D8D));
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
        EditRegister();
        Finances();

        setVisible(true);
    }
    private void NewRegister(){

        JMenu menuCadastro = new JMenu("Cadastros");
        menuCadastro.setFont(new Font("Arial",Font.BOLD,20));
        menuCadastro.setBackground(new Color(0x79616139, true));
        menuCadastro.setOpaque(true);

        menuCadastro.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        JMenuItem NewEmployee = new JMenuItem("Criar novo funcionario");
        NewEmployee.addActionListener(e -> createNewEmployee = CreatedNewEmployee(createNewEmployee,desktop,taskBar));
        menuCadastro.add(NewEmployee);

        JMenuItem newCustomer = new JMenuItem("Criar novo cliente");
        newCustomer.addActionListener(e -> createNewCustomer = CreateNewCustomer(createNewCustomer,desktop,taskBar));
        menuCadastro.add(newCustomer);

        JMenuItem newTour = new JMenuItem("Criar novo passeio");
        newTour.addActionListener(e -> createNewTour = CreateNewTour(createNewTour,desktop,taskBar));
        menuCadastro.add(newTour);

        JMenuItem newUser = new JMenuItem("Criar novo usuario");
        newUser.addActionListener(e -> createNewUser = CreateNewUser(createNewUser,desktop,taskBar));
        menuCadastro.add(newUser);

        JMenuItem agendarReserva = new JMenuItem("Agendar reserva");
        agendarReserva.addActionListener(e -> scheduleReservationWindow = scheduleReservation(scheduleReservationWindow,desktop,taskBar));
        menuCadastro.add(agendarReserva);

        menuBar.add(menuCadastro);
        setJMenuBar(menuBar);
    }
    private void ShowRegister(){
        JMenu menuOfShow = new JMenu("Mostrar registros");
        menuOfShow.setFont(new Font("Arial",Font.BOLD,20));
        menuOfShow.setBackground(new Color(0x79616139, true));
        menuOfShow.setOpaque(true);

        menuOfShow.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        JMenuItem showToursItens = new JMenuItem("Mostrar passeios");
        showToursItens.addActionListener(e -> showTours = showTour(showTours,desktop,taskBar));
        menuOfShow.add(showToursItens);

        JMenuItem showEmployeeItens = new JMenuItem("Mostrar Funcionários");
        showEmployeeItens.addActionListener(e -> showEmployee = showEmployees(showEmployee,desktop,taskBar));
        menuOfShow.add(showEmployeeItens);

        JMenuItem showCustomerItens = new JMenuItem("Mostrar Clientes");
        showCustomerItens.addActionListener(e -> showCustomer = showCustomers(showCustomer,desktop,taskBar));
        menuOfShow.add(showCustomerItens);

        JMenuItem showUserItens = new JMenuItem("Mostrar Usuarios");
        showUserItens.addActionListener(e -> showUser = showUsers(showUser,desktop,taskBar));
        menuOfShow.add(showUserItens);

        JMenuItem showReservationsItens = new JMenuItem("Mostrar Reservas");
        showReservationsItens.addActionListener(e -> showReservations = showReservations(showReservations,desktop,taskBar));

        menuOfShow.add(showReservationsItens);


        menuBar.add(menuOfShow);
        setJMenuBar(menuBar);

    }

    private void EditRegister(){
        JMenu menuOfShow = new JMenu("Editar registros");
        menuOfShow.setFont(new Font("Arial",Font.BOLD,20));
        menuOfShow.setBackground(new Color(0x79616139, true));
        menuOfShow.setOpaque(true);

        menuOfShow.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        JMenuItem alterarRegistros = new JMenuItem("Alterar registros de reservas");
        alterarRegistros.addActionListener(e -> editReservationWindow = editReservations(editReservationWindow,desktop,taskBar));
        menuOfShow.add(alterarRegistros);

        JMenuItem alterarEstado = new JMenuItem("Alterar estado de reserva");
        alterarEstado.addActionListener(e -> editStatusReservationWindow = editStatusReservations(editStatusReservationWindow,desktop,taskBar));
        menuOfShow.add(alterarEstado);


        menuBar.add(menuOfShow);
        setJMenuBar(menuBar);

    }

    private void Finances(){

        JMenu menuOfShow = new JMenu("Finanças ");
        menuOfShow.setFont(new Font("Arial",Font.BOLD,20));
        menuOfShow.setBackground(new Color(0x79616139, true));
        menuOfShow.setOpaque(true);

        menuOfShow.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        JMenuItem panelPayment = new JMenuItem("Realizar Pagamento");
        panelPayment.addActionListener(e ->confirmPaymentPanel= confirmPaymentPanel(confirmPaymentPanel,desktop,taskBar));
        menuOfShow.add(panelPayment);

        JMenuItem metas = new JMenuItem("Metas");
        metas.addActionListener(e ->quotasPanel = addQuotaPanel(quotasPanel,desktop,taskBar));
        menuOfShow.add(metas);


        menuBar.add(menuOfShow);
        setJMenuBar(menuBar);

    }


    private JPanel createTaskBar() {
        taskBar = new JPanel();
        taskBar.setLayout(new FlowLayout(FlowLayout.LEFT));
        taskBar.setBackground(new Color(40, 40, 40));
        taskBar.setPreferredSize(new Dimension(0, 40));
        return taskBar;
    }

}
