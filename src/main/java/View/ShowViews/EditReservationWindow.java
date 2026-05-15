package View.ShowViews;

import org.ONE.models.Cliente;
import org.ONE.models.Funcionario;
import org.ONE.models.Passeio;
import org.ONE.models.Reservations;
import org.ONE.services.ClienteServices;
import org.ONE.services.FuncionarioServices;
import org.ONE.services.PasseioServices;
import org.ONE.services.ReservationsServices;
import View.ItensDefault;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

public class EditReservationWindow extends JInternalFrame {

    private JTable table;
    private DefaultTableModel model;
    private ReservationsServices reservationsServices = new ReservationsServices();
    private ClienteServices clienteServices = new ClienteServices();
    private FuncionarioServices funcionarioServices = new FuncionarioServices();
    private PasseioServices passeioServices = new PasseioServices();

    private JComboBox<Cliente> cbCliente;
    private JComboBox<Funcionario> cbFuncionario;
    private JComboBox<Passeio> cbPasseio;
    private JTextField tfData;
    private JTextField tfValor;

    public EditReservationWindow() {
        super("Alterar Registros de Reservas", true, true, true, true);

        setLocation(300, 80);
        setLayout(new BorderLayout());

        add(buildTopPanel(), BorderLayout.NORTH);
        add(buildTablePanel(), BorderLayout.CENTER);
        add(buildFormPanel(), BorderLayout.SOUTH);

        pack();
        loadData();
    }

    private JPanel buildTopPanel() {
        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JTextField tfBusca = new JTextField(10);
        JButton btnBuscar = new JButton("Buscar por ID");
        JButton btnAtualizar = new JButton("Atualizar");

        top.add(new JLabel("ID:"));
        top.add(tfBusca);
        top.add(btnBuscar);
        top.add(btnAtualizar);

        btnAtualizar.addActionListener(e -> loadData());
        btnBuscar.addActionListener(e -> {
            String txt = tfBusca.getText().trim();
            if (txt.isEmpty()) { loadData(); return; }
            try {
                long id = Long.parseLong(txt);
                Reservations r = reservationsServices.findById(id);
                model.setRowCount(0);
                if (r != null) {
                    model.addRow(toRow(r));
                } else {
                    JOptionPane.showMessageDialog(this, "Reserva não encontrada.", "Aviso", JOptionPane.WARNING_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "ID inválido.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });


        return top;
    }

    private JScrollPane buildTablePanel() {
        model = new DefaultTableModel(new String[]{"ID", "Data", "Cliente", "Funcionário", "Passeio", "Valor", "Status"}, 0) {
            @Override public boolean isCellEditable(int row, int col) { return false; }
        };
        table = new JTable(model);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) populateForm();
        });

        return new JScrollPane(table);
    }

    private JPanel buildFormPanel() {
        List<Cliente> clientes = clienteServices.findAll();
        List<Funcionario> funcionarios = funcionarioServices.findAll();
        List<Passeio> passeios = passeioServices.findAll();

        cbCliente = new JComboBox<>(clientes.toArray(new Cliente[0]));
        cbCliente.setRenderer((list, value, index, isSelected, cellHasFocus) -> {
            JLabel lbl = new JLabel(value != null ? value.getName() : "");
            lbl.setOpaque(true);
            if (isSelected) lbl.setBackground(list.getSelectionBackground());
            return lbl;
        });

        cbFuncionario = new JComboBox<>(funcionarios.toArray(new Funcionario[0]));
        cbFuncionario.setRenderer((list, value, index, isSelected, cellHasFocus) -> {
            JLabel lbl = new JLabel(value != null ? value.getName() : "");
            lbl.setOpaque(true);
            if (isSelected) lbl.setBackground(list.getSelectionBackground());
            return lbl;
        });

        cbPasseio = new JComboBox<>(passeios.toArray(new Passeio[0]));
        cbPasseio.setRenderer((list, value, index, isSelected, cellHasFocus) -> {
            JLabel lbl = new JLabel(value != null ? value.getNameOfTour() : "");
            lbl.setOpaque(true);
            if (isSelected) lbl.setBackground(list.getSelectionBackground());
            return lbl;
        });

        tfData = new JTextField(12);
        tfValor = new JTextField(8);

        JButton btnSalvar = new JButton("Salvar Alterações");
        btnSalvar.addActionListener(e -> salvarAlteracoes());

        JPanel form = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 8));
        form.setBorder(BorderFactory.createTitledBorder("Editar reserva selecionada"));

        form.add(ItensDefault.createBoldLabel("Cliente:", 13)); form.add(cbCliente);
        form.add(ItensDefault.createBoldLabel("Funcionário:", 13)); form.add(cbFuncionario);
        form.add(ItensDefault.createBoldLabel("Passeio:", 13)); form.add(cbPasseio);
        form.add(ItensDefault.createBoldLabel("Data (AAAA-MM-DD):", 13)); form.add(tfData);
        form.add(ItensDefault.createBoldLabel("Valor:", 13)); form.add(tfValor);
        form.add(btnSalvar);

        return form;
    }

    private void populateForm() {
        int row = table.getSelectedRow();
        if (row < 0) return;

        Long id = (Long) model.getValueAt(row, 0);
        Reservations r = reservationsServices.findById(id);
        if (r == null) return;

        selectComboItem(cbCliente, r.getCliente().getId());
        selectComboItem(cbFuncionario, r.getFuncionario().getId());
        selectComboItem(cbPasseio, r.getTour().getId());
        tfData.setText(r.getDate().toString());
        tfValor.setText(String.format("%.2f", r.getValue()));
    }

    private <T> void selectComboItem(JComboBox<T> combo, long id) {
        for (int i = 0; i < combo.getItemCount(); i++) {
            Object item = combo.getItemAt(i);
            long itemId = -1;
            if (item instanceof Cliente c) itemId = c.getId();
            else if (item instanceof Funcionario f) itemId = f.getId();
            else if (item instanceof Passeio p) itemId = p.getId();
            if (itemId == id) { combo.setSelectedIndex(i); return; }
        }
    }

    private void salvarAlteracoes() {
        int row = table.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Selecione uma reserva na tabela.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Long id = (Long) model.getValueAt(row, 0);
        Reservations r = reservationsServices.findById(id);
        if (r == null) {
            JOptionPane.showMessageDialog(this, "Reserva não encontrada.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            LocalDate novaData = LocalDate.parse(tfData.getText().trim());
            double novoValor = Double.parseDouble(tfValor.getText().trim().replace(",", "."));

            r.setCliente((Cliente) cbCliente.getSelectedItem());
            r.setFuncionario((Funcionario) cbFuncionario.getSelectedItem());
            r.setTour((Passeio) cbPasseio.getSelectedItem());
            r.setDate(novaData);
            r.setValue(novoValor);

            reservationsServices.updateRecorde(r);
            JOptionPane.showMessageDialog(this, "Reserva atualizada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            loadData();
        } catch (DateTimeParseException ex) {
            JOptionPane.showMessageDialog(this, "Data inválida. Use o formato AAAA-MM-DD.", "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Valor inválido.", "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadData() {
        model.setRowCount(0);
        for (Reservations r : reservationsServices.findAll()) {
            model.addRow(toRow(r));
        }
    }

    private Object[] toRow(Reservations r) {
        return new Object[]{
                r.getId(),
                r.getDate(),
                r.getCliente().getName(),
                r.getFuncionario().getName(),
                r.getTour().getNameOfTour(),
                String.format("%.2f", r.getValue()),
                r.getStatus()
        };
    }
}
