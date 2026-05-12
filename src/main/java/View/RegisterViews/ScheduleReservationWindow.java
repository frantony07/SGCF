package View.RegisterViews;

import View.ItensDefault;
import org.ONE.models.Cliente;
import org.ONE.models.Funcionario;
import org.ONE.models.Passeio;
import org.ONE.models.Reservations;
import org.ONE.models.ENUM.Status;
import org.ONE.services.ClienteServices;
import org.ONE.services.FuncionarioServices;
import org.ONE.services.PasseioServices;
import org.ONE.services.ReservationsServices;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

public class ScheduleReservationWindow extends JInternalFrame {

    private ReservationsServices reservationsServices = new ReservationsServices();
    private ClienteServices clienteServices = new ClienteServices();
    private FuncionarioServices funcionarioServices = new FuncionarioServices();
    private PasseioServices passeioServices = new PasseioServices();

    public ScheduleReservationWindow() {
        super("Agendar Reserva", true, true, true, true);

        setSize(650, 450);
        setLocation(550, 100);

        var url = getClass().getResource("/icons/icons8-creating-20.png");
        if (url != null) setFrameIcon(new ImageIcon(url));

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(0x7E7D64));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        // Passeio
        gbc.gridx = 0; gbc.gridy = 0; gbc.fill = GridBagConstraints.NONE;
        panel.add(ItensDefault.createBoldLabel("Passeio:", 14), gbc);

        List<Passeio> passeios = passeioServices.findAll();
        JComboBox<Passeio> cbPasseio = new JComboBox<>(passeios.toArray(new Passeio[0]));
        cbPasseio.setRenderer((list, value, index, isSelected, cellHasFocus) -> {
            JLabel lbl = new JLabel(value != null ? value.getNameOfTour() + " - R$ " + String.format("%.2f", value.getPrice()) : "");
            lbl.setOpaque(true);
            if (isSelected) lbl.setBackground(list.getSelectionBackground());
            return lbl;
        });
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(cbPasseio, gbc);

        // Valor (auto-preenchido)
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

        // Cliente
        gbc.gridx = 0; gbc.gridy = 2; gbc.fill = GridBagConstraints.NONE;
        panel.add(ItensDefault.createBoldLabel("Cliente:", 14), gbc);

        List<Cliente> clientes = clienteServices.findAll();
        JComboBox<Cliente> cbCliente = new JComboBox<>(clientes.toArray(new Cliente[0]));
        cbCliente.setRenderer((list, value, index, isSelected, cellHasFocus) -> {
            JLabel lbl = new JLabel(value != null ? value.getName() : "");
            lbl.setOpaque(true);
            if (isSelected) lbl.setBackground(list.getSelectionBackground());
            return lbl;
        });
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(cbCliente, gbc);

        // Funcionário
        gbc.gridx = 0; gbc.gridy = 3; gbc.fill = GridBagConstraints.NONE;
        panel.add(ItensDefault.createBoldLabel("Funcionário:", 14), gbc);

        List<Funcionario> funcionarios = funcionarioServices.findAll();
        JComboBox<Funcionario> cbFuncionario = new JComboBox<>(funcionarios.toArray(new Funcionario[0]));
        cbFuncionario.setRenderer((list, value, index, isSelected, cellHasFocus) -> {
            JLabel lbl = new JLabel(value != null ? value.getName() : "");
            lbl.setOpaque(true);
            if (isSelected) lbl.setBackground(list.getSelectionBackground());
            return lbl;
        });
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(cbFuncionario, gbc);

        // Data
        gbc.gridx = 0; gbc.gridy = 4; gbc.fill = GridBagConstraints.NONE;
        panel.add(ItensDefault.createBoldLabel("Data (AAAA-MM-DD):", 14), gbc);

        JTextField tfData = new JTextField(LocalDate.now().toString(), 15);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(tfData, gbc);

        // Botão Agendar
        gbc.gridx = 1; gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.fill = GridBagConstraints.NONE;
        JButton btnAgendar = new JButton("Agendar");
        btnAgendar.addActionListener(e -> {
            Passeio passeio = (Passeio) cbPasseio.getSelectedItem();
            Cliente cliente = (Cliente) cbCliente.getSelectedItem();
            Funcionario funcionario = (Funcionario) cbFuncionario.getSelectedItem();
            String dataStr = tfData.getText().trim();

            if (passeio == null || cliente == null || funcionario == null || dataStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Preencha todos os campos.", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }

            try {
                LocalDate data = LocalDate.parse(dataStr);
                Reservations reservation = new Reservations(cliente, data, funcionario, passeio, passeio.getPrice(), Status.pendente);
                reservationsServices.createNewRecorde(reservation);
                JOptionPane.showMessageDialog(this,
                        "Reserva agendada com sucesso!\nCliente: " + cliente.getName() +
                        "\nPasseio: " + passeio.getNameOfTour() +
                        "\nData: " + data,
                        "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                tfData.setText(LocalDate.now().toString());
            } catch (DateTimeParseException ex) {
                JOptionPane.showMessageDialog(this, "Data inválida. Use o formato AAAA-MM-DD.", "Erro", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao agendar: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });
        panel.add(btnAgendar, gbc);

        add(panel);
    }
}
