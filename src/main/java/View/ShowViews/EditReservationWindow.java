package View.ShowViews;

import Controller.Impl.ReservationControllerImpl;
import Controller.Record.ClienteDTO;
import Controller.Record.FuncionarioDTO;
import Controller.Record.PasseioDTO;
import Controller.ReservationController;
import org.ONE.model.entity.Cliente;
import org.ONE.model.entity.Funcionario;
import org.ONE.model.entity.Passeio;
import org.ONE.model.entity.Reservations;
import org.ONE.model.repositories.ClienteRepository;
import org.ONE.model.repositories.CustomizerFactory;
import org.ONE.model.repositories.FuncionarioRepository;
import org.ONE.model.services.impl.ClienteServicesImpl;
import org.ONE.model.services.FuncionarioService;
import org.ONE.model.services.PasseioService;
import org.ONE.model.services.impl.FuncionarioServiceImpl;
import org.ONE.model.services.impl.PasseioServiceImpl;
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
    private ReservationController reservationController = new ReservationControllerImpl();
    private ClienteServicesImpl clienteServices = new ClienteServicesImpl();
    private FuncionarioService funcionarioServices = new FuncionarioServiceImpl();
    private PasseioService passeioServices = new PasseioServiceImpl();

    private JComboBox<ClienteDTO> cbCliente;
    private JComboBox<FuncionarioDTO> cbFuncionario;
    private ClienteRepository clienteRepository = new ClienteRepository(CustomizerFactory.getEntityManager());
    private FuncionarioRepository funcionarioRepository = new FuncionarioRepository(CustomizerFactory.getEntityManager());
    private JComboBox<PasseioDTO> cbPasseio;
    private List<Passeio> allPasseios;
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
                Reservations r = reservationController.findById(id);
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
        List<ClienteDTO> clientes = clienteServices.findAll();

        List<FuncionarioDTO> funcionarios = funcionarioServices.findAll();
        allPasseios = passeioServices.findAllEntities();
        List<PasseioDTO> passeios = allPasseios.stream()
                .map(p -> new PasseioDTO(p.getPrice(), p.getDurationOfTourInMinute(), p.getCountryTour(), p.getKmOftour(), p.getNameOfTour(), p.getLocations()))
                .collect(java.util.stream.Collectors.toList());

        cbCliente = new JComboBox<>(clientes.toArray(new ClienteDTO[0]));
        cbCliente.setRenderer((list, value, index, isSelected, cellHasFocus) -> {
            JLabel lbl = new JLabel(value != null ? value.name() : "");
            lbl.setOpaque(true);
            if (isSelected) lbl.setBackground(list.getSelectionBackground());
            return lbl;
        });

        cbFuncionario = new JComboBox<>(funcionarios.toArray(new FuncionarioDTO[0]));
        cbFuncionario.setRenderer((list, value, index, isSelected, cellHasFocus) -> {
            JLabel lbl = new JLabel(value != null ? value.name() : "");
            lbl.setOpaque(true);
            if (isSelected) lbl.setBackground(list.getSelectionBackground());
            return lbl;
        });

        cbPasseio = new JComboBox<>(passeios.toArray(new PasseioDTO[0]));
        cbPasseio.setRenderer((list, value, index, isSelected, cellHasFocus) -> {
            JLabel lbl = new JLabel(value != null ? value.nameOfTour() : "");
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
        Reservations r = reservationController.findById(id);
        if (r == null) return;

        selectComboItemByCpf(cbCliente, r.getCliente().getCpf());
        selectComboItemByCpf(cbFuncionario, r.getFuncionario().getCpf());
        long tourId = r.getTour().getId();
        for (int i = 0; i < allPasseios.size(); i++) {
            if (allPasseios.get(i).getId() == tourId) { cbPasseio.setSelectedIndex(i); break; }
        }
        tfData.setText(r.getDate().toString());
        tfValor.setText(String.format("%.2f", r.getValue()));
    }

    private <T> void selectComboItemByCpf(JComboBox<T> combo, String cpf) {
        for (int i = 0; i < combo.getItemCount(); i++) {
            Object item = combo.getItemAt(i);
            String itemCpf = null;
            if (item instanceof ClienteDTO c) itemCpf = c.cpf();
            else if (item instanceof FuncionarioDTO f) itemCpf = f.cpf();
            if (cpf != null && cpf.equals(itemCpf)) { combo.setSelectedIndex(i); return; }
        }
    }

    private void salvarAlteracoes() {
        int row = table.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Selecione uma reserva na tabela.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Long id = (Long) model.getValueAt(row, 0);
        Reservations r = reservationController.findById(id);
        if (r == null) {
            JOptionPane.showMessageDialog(this, "Reserva não encontrada.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            LocalDate novaData = LocalDate.parse(tfData.getText().trim());
            double novoValor = Double.parseDouble(tfValor.getText().trim().replace(",", "."));

            ClienteDTO clienteDTO = (ClienteDTO) cbCliente.getSelectedItem();
            FuncionarioDTO funcionarioDTO = (FuncionarioDTO) cbFuncionario.getSelectedItem();

            Cliente clienteSelecionado = clienteRepository.findByCpf(clienteDTO.cpf());
            List<Funcionario> funcionarios = funcionarioRepository.findByCPF(funcionarioDTO.cpf());
            if (clienteSelecionado == null || funcionarios.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Cliente ou funcionário não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            r.setCliente(clienteSelecionado);
            r.setFuncionario(funcionarios.get(0));
            int passeioIdx = cbPasseio.getSelectedIndex();
            r.setTour(passeioIdx >= 0 ? allPasseios.get(passeioIdx) : null);
            r.setDate(novaData);
            r.setValue(novoValor);

            reservationController.updateRecorde(r);

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
        for (Reservations r : reservationController.findAll()) {
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
