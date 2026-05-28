package View.ShowViews;

import Controller.Impl.TourControllerImpl;
import Controller.Record.PasseioDTO;
import Controller.TourController;
import org.ONE.model.services.impl.PasseioServiceImpl;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ShowTours extends JInternalFrame {

    private JTable table;
    private DefaultTableModel model;
    private TourController tourController;

    private JTextField txtBusca;

    public ShowTours() {
        super("Lista de Passeios", true, true, true, true);

        tourController = new TourControllerImpl();

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
        model.addColumn("Nome");
        model.addColumn("Preço");
        model.addColumn("Duração");
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

        List<PasseioDTO> lista = tourController.findAll();

        for (PasseioDTO passeio : lista) {
            model.addRow(new Object[]{
                    passeio.nameOfTour(),
                    passeio.price(),
                    passeio.durationOfTourInMinute(),
                    passeio.countryTour()
            });
        }
    }

    private void buscarPorNome() {
        try {

        model.setRowCount(0);

        String nomeBusca = txtBusca.getText().trim();

        if (nomeBusca.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Digite um nome para buscar!",
                    "Aviso",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        List<PasseioDTO> lista = tourController.findByName(nomeBusca);

        if (lista.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Nenhum passeio encontrado!",
                    "Informação",
                    JOptionPane.INFORMATION_MESSAGE
            );
            return;
        }

        for (PasseioDTO passeio : lista) {
            model.addRow(new Object[]{
                    passeio.nameOfTour(),
                    passeio.price(),
                    passeio.durationOfTourInMinute(),
                    passeio.countryTour()
            });
        }

        } catch (Exception e) {
           JOptionPane.showMessageDialog(this,e,"erro",JOptionPane.ERROR_MESSAGE);
        }
    }
}