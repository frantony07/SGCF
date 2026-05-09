package View.ShowViews;

import org.ONE.models.User;
import org.ONE.services.UserServices;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ShowUser extends JInternalFrame {

    private JTable table;
    private DefaultTableModel model;
    private UserServices userService;

    private JTextField txtSearch;
    private JButton btnSearch;
    private JButton btnRefresh;

    public ShowUser() {
        super("Lista de Usuários", true, true, true, true);

        userService = new UserServices();

        setSize(800, 500);
        setLocation(550, 100);
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
    }

    private void loadData() {
        model.setRowCount(0);

        List<User> list = userService.findAll();

        for (User user : list) {
            model.addRow(new Object[]{
                    user.getId(),
                    user.getUserName(),
                    user.getEmail(),

            });
        }
    }

    private void searchByName() {
        model.setRowCount(0);

        String name = txtSearch.getText();

        List<User> list = userService.findByName(name);

        if (list.isEmpty()){
            JOptionPane.showMessageDialog(this,"nenhum usuario encontrado","erro" ,JOptionPane.ERROR_MESSAGE);
        }

        for (User user : list) {
            model.addRow(new Object[]{
                    user.getId(),
                    user.getUserName(),
                    user.getEmail(),

            });
        }
    }
}