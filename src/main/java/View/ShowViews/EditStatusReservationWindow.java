package View.ShowViews;

import org.ONE.model.entity.Reservations;
import org.ONE.model.entity.ENUM.Status;
import org.ONE.model.services.impl.ReservationsServiceImpl;
import View.ItensDefault;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class EditStatusReservationWindow extends JInternalFrame {

    private JTable table;
    private DefaultTableModel model;
    private ReservationsServiceImpl reservationsServices = new ReservationsServiceImpl();

    private JComboBox<Status> cbFiltro;
    private JComboBox<Status> cbNovoStatus;

    public EditStatusReservationWindow() {
        super("Alterar Estado de Reservas", true, true, true, true);

        setSize(900, 500);
        setLocation(300, 80);
        setLayout(new BorderLayout());

        add(buildTopPanel(), BorderLayout.NORTH);
        add(buildTablePanel(), BorderLayout.CENTER);
        add(buildBottomPanel(), BorderLayout.SOUTH);

        loadData(Status.pendente);
    }

    private JPanel buildTopPanel() {
        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT));

        cbFiltro = new JComboBox<>(Status.values());
        cbFiltro.setSelectedItem(Status.pendente);

        JButton btnFiltrar = new JButton("Filtrar");
        JButton btnMostrarTodas = new JButton("Mostrar Todas");

        top.add(ItensDefault.createBoldLabel("Filtrar por status:", 13));
        top.add(cbFiltro);
        top.add(btnFiltrar);
        top.add(btnMostrarTodas);

        btnFiltrar.addActionListener(e -> loadData((Status) cbFiltro.getSelectedItem()));
        btnMostrarTodas.addActionListener(e -> loadAll());

        return top;
    }

    private JScrollPane buildTablePanel() {
        model = new DefaultTableModel(new String[]{"ID", "Data", "Cliente", "Passeio", "Funcionário", "Valor", "Status"}, 0) {
            @Override public boolean isCellEditable(int row, int col) { return false; }
        };
        table = new JTable(model);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        return new JScrollPane(table);
    }

    private JPanel buildBottomPanel() {
        cbNovoStatus = new JComboBox<>(Status.values());

        JButton btnAlterar = new JButton("Alterar Status");
        btnAlterar.addActionListener(e -> alterarStatus());

        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 8));
        bottom.setBorder(BorderFactory.createTitledBorder("Alterar status da reserva selecionada"));
        bottom.add(ItensDefault.createBoldLabel("Novo status:", 13));
        bottom.add(cbNovoStatus);
        bottom.add(btnAlterar);

        return bottom;
    }

    private void alterarStatus() {
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

        Status novoStatus = (Status) cbNovoStatus.getSelectedItem();
        r.setStatus(novoStatus);
        reservationsServices.updateRecorde(r);

        JOptionPane.showMessageDialog(this,
                "Status da reserva " + id + " alterado para " + novoStatus + ".",
                "Sucesso", JOptionPane.INFORMATION_MESSAGE);

        loadData((Status) cbFiltro.getSelectedItem());
    }

    private void loadData(Status filtro) {
        model.setRowCount(0);
        List<Reservations> lista = reservationsServices.findAll();
        for (Reservations r : lista) {
            if (r.getStatus() == filtro) model.addRow(toRow(r));
        }
    }

    private void loadAll() {
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
                r.getTour().getNameOfTour(),
                r.getFuncionario().getName(),
                String.format("%.2f", r.getValue()),
                r.getStatus()
        };
    }
}
