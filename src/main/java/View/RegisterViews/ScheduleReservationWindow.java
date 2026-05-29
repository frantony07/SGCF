package View.RegisterViews;

import Controller.Record.ClienteDTO;
import Controller.Record.FuncionarioDTO;
import Controller.Record.PasseioDTO;
import Controller.ReservationController;
import View.ItensDefault;
import org.ONE.model.entity.Cliente;
import org.ONE.model.entity.Funcionario;
import org.ONE.model.entity.Passeio;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.List;

public class ScheduleReservationWindow extends JInternalFrame {

    private final ReservationController reservationController;

    public ScheduleReservationWindow(ReservationController reservationController) {
        super("Agendar Reserva", true, true, true, true);
        this.reservationController = reservationController;

        setSize(650, 450);
        setLocation(550, 100);

        var url = getClass().getResource("/icons/icons8-creating-20.png");
        if (url != null) setFrameIcon(new ImageIcon(url));

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(ItensDefault.FUNDO_CLARO);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0; gbc.gridy = 0; gbc.fill = GridBagConstraints.NONE;
        panel.add(ItensDefault.createBoldLabel("Passeio:", 14), gbc);

        List<Passeio> passeios = reservationController.getAllPasseios();
        JComboBox<Passeio> cbPasseio = new JComboBox<>(passeios.toArray(new Passeio[0]));
        cbPasseio.setRenderer((list, value, index, isSelected, cellHasFocus) -> {
            JLabel lbl = new JLabel(value != null ? value.getNameOfTour() + " - R$ " + String.format("%.2f", value.getPrice()) : "");
            lbl.setOpaque(true);
            if (isSelected) lbl.setBackground(list.getSelectionBackground());
            return lbl;
        });
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(cbPasseio, gbc);

        gbc.gridx = 0; gbc.gridy = 1; gbc.fill = GridBagConstraints.NONE;
        panel.add(ItensDefault.createBoldLabel("Valor (R$):", 14), gbc);

        JTextField tfValor = new JTextField(15);
        tfValor.setEditable(false);
        if (!passeios.isEmpty()) {
            tfValor.setText(String.format("%.2f", passeios.get(0).getPrice()));
        }
        cbPasseio.addActionListener(e -> {
            Passeio sel = (Passeio) cbPasseio.getSelectedItem();
            if (sel != null) tfValor.setText(String.format("%.2f", sel.getPrice()));
        });
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(tfValor, gbc);

        gbc.gridx = 0; gbc.gridy = 2; gbc.fill = GridBagConstraints.NONE;
        panel.add(ItensDefault.createBoldLabel("Cliente:", 14), gbc);

        List<ClienteDTO> clientes = reservationController.getAllClientes();
        JComboBox<Cliente> cbCliente = new JComboBox<>(clientes.toArray(new Cliente[0]));
        cbCliente.setRenderer((list, value, index, isSelected, cellHasFocus) -> {
            JLabel lbl = new JLabel(value != null ? value.getName() : "");
            lbl.setOpaque(true);
            if (isSelected) lbl.setBackground(list.getSelectionBackground());
            return lbl;
        });
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(cbCliente, gbc);

        gbc.gridx = 0; gbc.gridy = 3; gbc.fill = GridBagConstraints.NONE;
        panel.add(ItensDefault.createBoldLabel("Funcionário:", 14), gbc);

        List<FuncionarioDTO> funcionarios = reservationController.getAllFuncionarios();
        JComboBox<Funcionario> cbFuncionario = new JComboBox<>(funcionarios.toArray(new Funcionario[0]));
        cbFuncionario.setRenderer((list, value, index, isSelected, cellHasFocus) -> {
            JLabel lbl = new JLabel(value != null ? value.getName() : "");
            lbl.setOpaque(true);
            if (isSelected) lbl.setBackground(list.getSelectionBackground());
            return lbl;
        });
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(cbFuncionario, gbc);

        gbc.gridx = 0; gbc.gridy = 4; gbc.fill = GridBagConstraints.NONE;
        panel.add(ItensDefault.createBoldLabel("Data (AAAA-MM-DD):", 14), gbc);

        JTextField tfData = new JTextField(LocalDate.now().toString(), 15);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(tfData, gbc);

        gbc.gridx = 1; gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.fill = GridBagConstraints.NONE;
        JButton btnAgendar = new JButton("Agendar");
        btnAgendar.addActionListener(e -> {
            Passeio passeio       = (Passeio)     cbPasseio.getSelectedItem();
            Cliente cliente       = (Cliente)     cbCliente.getSelectedItem();
            Funcionario funcionario = (Funcionario) cbFuncionario.getSelectedItem();
            String dataStr        = tfData.getText().trim();

            try {
                reservationController.createReservation(passeio, cliente, funcionario, dataStr);

                JOptionPane.showMessageDialog(this,
                        "Reserva agendada com sucesso!\nCliente: " + (cliente != null ? cliente.getName() : "") +
                        "\nPasseio: " + (passeio != null ? passeio.getNameOfTour() : "") +
                        "\nData: " + dataStr,
                        "Sucesso", JOptionPane.INFORMATION_MESSAGE);

                tfData.setText(LocalDate.now().toString());

            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Aviso", JOptionPane.WARNING_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao agendar: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });
        panel.add(btnAgendar, gbc);

        add(panel);
    }
}
