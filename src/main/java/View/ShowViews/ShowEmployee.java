package View.ShowViews;

import org.ONE.model.entity.Funcionario;
import org.ONE.model.services.FuncionarioService;
import org.ONE.model.services.impl.FuncionarioServiceImpl;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ShowEmployee extends JInternalFrame {

    private JTable table;
    private DefaultTableModel model;
    private FuncionarioService employeeService;

    private JTextField txtBusca;

    public ShowEmployee() {
        super("Lista de Funcionários", true, true, true, true);

        employeeService = new FuncionarioServiceImpl();

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

        List<Funcionario> lista = employeeService.findAll();

        for (Funcionario emp : lista) {
            model.addRow(new Object[]{
                    emp.getId(),
                    emp.getName(),
                    emp.getCpf(),

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

        List<Funcionario> lista = employeeService.findByName(nome);

        if (lista.isEmpty()){
            JOptionPane.showMessageDialog(this,"Nenhum funcionario encontrado", "erro",JOptionPane.ERROR_MESSAGE);
        }

        for (Funcionario emp : lista) {
            model.addRow(new Object[]{
                    emp.getId(),
                    emp.getName(),
                    emp.getCpf(),

            });
        }
    }
}