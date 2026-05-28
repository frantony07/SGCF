package View.ShowViews;

import Controller.Impl.UserControllerImpl;
import Controller.Record.UserDTO;
import Controller.UserController;
import org.ONE.model.services.impl.UserServiceImpl;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ShowUser extends JInternalFrame {

    private JTable table;
    private DefaultTableModel model;
    private UserController userController;

    private JTextField txtSearch;
    private JButton btnSearch;
    private JButton btnRefresh;

    public ShowUser() {
        super("Lista de Usuários", true, true, true, true);

        userController = new UserControllerImpl();

        setLocation(500, 100);
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        txtSearch = new JTextField(20);
        btnSearch = new JButton("Buscar");
        btnRefresh = new JButton("Atualizar");

        topPanel.add(new JLabel("Nome: "));
        topPanel.add(txtSearch);
        topPanel.add(btnSearch);
        topPanel.add(btnRefresh);

        add(topPanel, BorderLayout.NORTH);

        model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Nome");
        model.addColumn("Email");


        table = new JTable(model);
        JScrollPane scroll = new JScrollPane(table);

        add(scroll, BorderLayout.CENTER);

        btnSearch.addActionListener(e -> searchByName());
        btnRefresh.addActionListener(e -> loadData());

        loadData();
        pack();
    }

    private void loadData() {
        model.setRowCount(0);

        List<UserDTO> list = userController.findAll();

        for (UserDTO user : list) {
            model.addRow(new Object[]{
                    user.userName(),
                    user.permission(),
                    user.email()

            });
        }
    }

    private void searchByName() {
        model.setRowCount(0);

        String name = txtSearch.getText();

        List<UserDTO> list = userController.findByName(name);

        if (list.isEmpty()){
            JOptionPane.showMessageDialog(this,"nenhum usuario encontrado","erro" ,JOptionPane.ERROR_MESSAGE);
        }

        for (UserDTO user : list) {
            model.addRow(new Object[]{
                    user.userName(),
                    user.permission(),
                    user.email()

            });
        }
    }
}