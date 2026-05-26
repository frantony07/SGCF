package View;

import View.RegisterViews.CreateNewCustomer;
import View.RegisterViews.CreateNewEmployee;
import View.RegisterViews.CreateNewTour;
import View.RegisterViews.CreateNewUser;
import View.RegisterViews.ScheduleReservationWindow;
import View.ShowViews.*;
import org.ONE.model.entity.ENUM.Permission;
import org.ONE.model.entity.User;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

import static View.FunctionOfWindowsEdit.*;
import static View.FunctionsOfWindowsFinances.*;
import static View.FunctionsOfWindowsRegister.*;
import static View.FunctionsOfWindowsShow.*;

public class MainScreens extends JFrame {

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
    private ShowQuotasFrame showQuotasFrame;

    public MainScreens(User userName) {
        setTitle("SGCF");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);

        var logoUrl = getClass().getResource("/icons/Waterfall.png");
        Image logo = logoUrl != null ? new ImageIcon(logoUrl).getImage() : null;
        if (logo != null) setIconImage(logo);

        desktop = buildDesktop(logo);
        setLayout(new BorderLayout());
        add(desktop, BorderLayout.CENTER);
        add(createTaskBar(), BorderLayout.SOUTH);

        menuBar.setBackground(ItensDefault.AZUL_MARINHO);
        menuBar.setOpaque(true);
        menuBar.setBorderPainted(false);

        NewRegister(userName);
        ShowRegister();
        EditRegister(userName);
        Finances(userName);

        menuBar.add(Box.createHorizontalGlue());
        JLabel greetingLabel = new JLabel("Olá, " + userName.getUserName());
        greetingLabel.setFont(ItensDefault.FONTE_MENU);
        greetingLabel.setForeground(Color.WHITE);
        greetingLabel.setBorder(BorderFactory.createEmptyBorder(4, 20, 4, 20));
        menuBar.add(greetingLabel);

        setJMenuBar(menuBar);

        setVisible(true);
    }

    private JDesktopPane buildDesktop(Image logo) {
        final BufferedImage cachedLogo;
        if (logo != null) {
            int size = 140;
            BufferedImage img = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
            Graphics2D wg = img.createGraphics();
            wg.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            wg.drawImage(logo, 0, 0, size, size, null);
            wg.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_IN));
            wg.setColor(new Color(255, 255, 255, 220));
            wg.fillRect(0, 0, size, size);
            wg.dispose();
            cachedLogo = img;
        } else {
            cachedLogo = null;
        }

        return new JDesktopPane() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,  RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setRenderingHint(RenderingHints.KEY_RENDERING,     RenderingHints.VALUE_RENDER_QUALITY);
                g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

                g2.setPaint(new GradientPaint(0, 0, ItensDefault.TEAL_ESCURO, getWidth(), getHeight(), ItensDefault.TEAL_MEDIO));
                g2.fillRect(0, 0, getWidth(), getHeight());

                int cx = getWidth() / 2;
                int cy = getHeight() / 2;

                if (cachedLogo != null) {
                    g2.drawImage(cachedLogo, cx - 70, cy - 120, this);
                }

                g2.setColor(new Color(255, 255, 255, 230));
                g2.setFont(ItensDefault.FONTE_TITULO);
                FontMetrics fm = g2.getFontMetrics();
                String title = "SGCF";
                g2.drawString(title, cx - fm.stringWidth(title) / 2, cy + 120);

                g2.setColor(new Color(255, 255, 255, 140));
                g2.setFont(ItensDefault.FONTE_SUBTITULO);
                fm = g2.getFontMetrics();
                String sub = "Sistema de Gerenciamento Comercial e Financeiro";
                g2.drawString(sub, cx - fm.stringWidth(sub) / 2, cy + 150);

                g2.dispose();
            }
        };
    }

    private JMenu createStyledMenu(String title) {
        JMenu menu = new JMenu(title);
        menu.setFont(ItensDefault.FONTE_MENU);
        menu.setForeground(Color.WHITE);
        menu.setBorder(BorderFactory.createEmptyBorder(4, 10, 4, 10));
        return menu;
    }

    private void NewRegister(User user) {
        JMenu menuCadastro = createStyledMenu("Cadastros");
        if (user.getPermission() == Permission.FUNCIONARIO) {
            return;
        }
        JMenuItem newEmployee = new JMenuItem("Criar novo funcionário");
        newEmployee.addActionListener(e -> createNewEmployee = CreatedNewEmployee(createNewEmployee, desktop, taskBar));
        menuCadastro.add(newEmployee);

        JMenuItem newCustomer = new JMenuItem("Criar novo cliente");
        newCustomer.addActionListener(e -> createNewCustomer = CreateNewCustomer(createNewCustomer, desktop, taskBar));
        menuCadastro.add(newCustomer);

        JMenuItem newTour = new JMenuItem("Criar novo passeio");
        newTour.addActionListener(e -> createNewTour = CreateNewTour(createNewTour, desktop, taskBar));
        menuCadastro.add(newTour);

        JMenuItem newUser = new JMenuItem("Criar novo usuário");
        newUser.addActionListener(e -> createNewUser = CreateNewUser(createNewUser, desktop, taskBar));
        menuCadastro.add(newUser);

        JMenuItem agendarReserva = new JMenuItem("Agendar reserva");
        agendarReserva.addActionListener(e -> scheduleReservationWindow = scheduleReservation(scheduleReservationWindow, desktop, taskBar));
        menuCadastro.add(agendarReserva);

        menuBar.add(menuCadastro);
    }

    private void ShowRegister() {
        JMenu menuOfShow = createStyledMenu("Mostrar registros");

        JMenuItem showToursItens = new JMenuItem("Mostrar passeios");
        showToursItens.addActionListener(e -> showTours = showTour(showTours, desktop, taskBar));
        menuOfShow.add(showToursItens);

        JMenuItem showEmployeeItens = new JMenuItem("Mostrar Funcionários");
        showEmployeeItens.addActionListener(e -> showEmployee = showEmployees(showEmployee, desktop, taskBar));
        menuOfShow.add(showEmployeeItens);

        JMenuItem showCustomerItens = new JMenuItem("Mostrar Clientes");
        showCustomerItens.addActionListener(e -> showCustomer = showCustomers(showCustomer, desktop, taskBar));
        menuOfShow.add(showCustomerItens);

        JMenuItem showUserItens = new JMenuItem("Mostrar Usuários");
        showUserItens.addActionListener(e -> showUser = showUsers(showUser, desktop, taskBar));
        menuOfShow.add(showUserItens);

        JMenuItem showReservationsItens = new JMenuItem("Mostrar Reservas");
        showReservationsItens.addActionListener(e -> showReservations = showReservations(showReservations, desktop, taskBar));
        menuOfShow.add(showReservationsItens);

        menuBar.add(menuOfShow);
    }

    private void EditRegister(User user) {
        JMenu menuOfShow = createStyledMenu("Editar registros");
        if (user.getPermission() == Permission.FUNCIONARIO) {
            return;
        }
        JMenuItem alterarRegistros = new JMenuItem("Alterar registros de reservas");
        alterarRegistros.addActionListener(e -> editReservationWindow = editReservations(editReservationWindow, desktop, taskBar));
        menuOfShow.add(alterarRegistros);

        JMenuItem alterarEstado = new JMenuItem("Alterar estado de reserva");
        alterarEstado.addActionListener(e -> editStatusReservationWindow = editStatusReservations(editStatusReservationWindow, desktop, taskBar));
        menuOfShow.add(alterarEstado);

        menuBar.add(menuOfShow);
    }

    private void Finances(User user) {
        JMenu menuOfShow = createStyledMenu("Finanças");

        if (user.getPermission() == Permission.FUNCIONARIO) {
            return;
        }
            JMenuItem panelPayment = new JMenuItem("Realizar Pagamento");
            panelPayment.addActionListener(e -> confirmPaymentPanel = confirmPaymentPanel(confirmPaymentPanel, desktop, taskBar));
            menuOfShow.add(panelPayment);

        JMenuItem metas = new JMenuItem("Metas");
        metas.addActionListener(e -> showQuotasFrame = addQuotaPanel(showQuotasFrame, desktop, taskBar));
        menuOfShow.add(metas);

            menuBar.add(menuOfShow);
    }

    private JPanel createTaskBar() {
        taskBar = new JPanel();
        taskBar.setLayout(new FlowLayout(FlowLayout.LEFT));
        taskBar.setBackground(ItensDefault.AZUL_MARINHO);
        taskBar.setPreferredSize(new Dimension(0, 40));
        return taskBar;
    }
}
