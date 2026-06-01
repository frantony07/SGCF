package View.ShowViews;

import Controller.Record.ClienteDTO;
import org.ONE.model.entity.Cliente;
import org.ONE.model.services.impl.ClienteServicesImpl;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ShowCustomer extends JInternalFrame {

    private JTable table;
    private DefaultTableModel model;
    private ClienteServicesImpl clienteService;
    private JTextField txtBusca;

    public ShowCustomer() {
        super("Lista de Clientes", true, true, true, true);

        clienteService = new ClienteServicesImpl();

        setLocation(550, 100);
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        topPanel.add(new JLabel("Buscar por nome:"));
        txtBusca = new JTextField(20);
        topPanel.add(txtBusca);

        JButton btnBuscar = new JButton("Buscar");
        JButton btnAtualizar = new JButton("Atualizar");

        topPanel.add(btnBuscar);
        topPanel.add(btnAtualizar);

        add(topPanel, BorderLayout.NORTH);


        model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Nome");
        model.addColumn("CPF");
        model.addColumn("CNPJ");
        model.addColumn("País");

        table = new JTable(model);
        JScrollPane scroll = new JScrollPane(table);

        add(scroll, BorderLayout.CENTER);


        btnBuscar.addActionListener(e -> buscarPorNome());
        btnAtualizar.addActionListener(e -> loadData());

        loadData();
        pack();
    }

    private void loadData() {
        model.setRowCount(0);

        List<ClienteDTO> lista = clienteService.findAll();

        for (ClienteDTO c : lista) {
            model.addRow(new Object[]{
                    c.name(),
                    c.cpf(),
                    c.cnpj(),
                    c.countryOfCostumer()
            });
        }
    }

    private void buscarPorNome() {

        String nome = txtBusca.getText();

        if (nome.isEmpty()) {
            loadData();
            return;
        }

        model.setRowCount(0);

        List<ClienteDTO> lista = clienteService.findByName(nome);

        if (lista.isEmpty()){
             JOptionPane.showMessageDialog(this,"nenhum cliente encontrado","erro",JOptionPane.ERROR_MESSAGE);
        }

        for (ClienteDTO c : lista) {
            model.addRow(new Object[]{
                    c.name(),
                    c.cpf(),
                    c.cnpj(),
                    c.countryOfCostumer()
            });
        }
    }
}