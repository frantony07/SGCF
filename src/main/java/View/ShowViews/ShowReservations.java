package View.ShowViews;

import org.ONE.model.entity.Reservations;
import org.ONE.model.services.ReservationsService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ShowReservations extends JInternalFrame {

    private JTable table;
    private DefaultTableModel model;
    private ReservationsService reservationsServices;
    private JTextField txtBusca;

    public ShowReservations() {

        super(
                "Reservas Ativas",
                true,
                true,
                true,
                true
        );

        reservationsServices =
                new ReservationsService();

        setSize(1000, 500);
        setLocation(550, 100);
        setLayout(new BorderLayout());

        JPanel topPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.LEFT
                        )
                );

        topPanel.add(
                new JLabel("Buscar por cliente:")
        );

        txtBusca = new JTextField(20);
        topPanel.add(txtBusca);

        JButton btnBuscar =
                new JButton("Buscar");

        JButton btnAtualizar =
                new JButton("Atualizar");

        JButton btnCancelar =
                new JButton("Cancelar Reserva");

        topPanel.add(btnBuscar);
        topPanel.add(btnAtualizar);
        topPanel.add(btnCancelar);
        add(topPanel, BorderLayout.NORTH);

        model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Cliente");
        model.addColumn("Passeio");
        model.addColumn("Data");
        model.addColumn("Status");

        table = new JTable(model);
        JScrollPane scroll =
                new JScrollPane(table);
        add(scroll, BorderLayout.CENTER);

        btnBuscar.addActionListener(
                this::btnBuscarActionPerformed
        );

        btnAtualizar.addActionListener(
                e -> loadData()
        );

        btnCancelar.addActionListener(
                this::btnCancelarActionPerformed
        );
        loadData();
    }

    private void loadData() {

        model.setRowCount(0);
        List<Reservations> lista =
                reservationsServices.findAll();

        for (Reservations r : lista) {
            model.addRow(new Object[]{

                    r.getId(),
                    r.getCliente().getName(),
                    r.getTour().getNameOfTour(),
                    r.getDate(),
                    r.getStatus()
            });
        }
    }

    private void btnBuscarActionPerformed(
            java.awt.event.ActionEvent evt
    ) {

        String texto = txtBusca.getText();

        if(texto.isEmpty()) {

            loadData();
            return;
        }

        model.setRowCount(0);

        List<Reservations> lista =
                reservationsServices
                        .getConfirmedReservations();

        for (Reservations r : lista) {
            if(r.getCliente().getName()
                    .toLowerCase()
                    .contains(
                            texto.toLowerCase()
                    )) {
                model.addRow(new Object[]{

                        r.getId(),
                        r.getCliente().getName(),
                        r.getTour().getNameOfTour(),
                        r.getDate(),
                        r.getStatus()
                });
            }
        }
    }

    private void btnCancelarActionPerformed(
            java.awt.event.ActionEvent evt
    ) {

        int linha = table.getSelectedRow();
        if(linha == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Selecione uma reserva"
            );
            return;
        }

        Long id = Long.valueOf(
                model.getValueAt(
                        linha,
                        0
                ).toString()
        );

        Reservations reservation =
                reservationsServices.findById(id);
        if(reservation == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Reserva não encontrada"
            );
            return;
        }

        int confirmacao =
                JOptionPane.showConfirmDialog(
                        this,
                        "Deseja cancelar esta reserva?",
                        "Confirmar",
                        JOptionPane.YES_NO_OPTION
                );

        if(confirmacao ==
                JOptionPane.YES_OPTION) {
            reservationsServices.delete(
                    reservation
            );

            JOptionPane.showMessageDialog(
                    this,
                    "Reserva CANCELADA"
            );
            loadData();
        }
    }
}