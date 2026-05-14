/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package View.ShowViews;



import org.ONE.services.FuncionarioServices;
import org.ONE.services.PayServices;
import org.ONE.services.QuotaServices;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.text.NumberFormat;

public class ShowQuotasFrame extends javax.swing.JInternalFrame {

    private final QuotaServices quotaServices = new QuotaServices();
    private final FuncionarioServices funcionarioServices = new FuncionarioServices();
    private final PayServices payServices = new PayServices();

    private final JTable contributingPayments;
    private final DefaultTableModel tableModel;
    private final JTextPane showCompanyRemainingQuota;
    private final JTextPane showEmployeeRemainingQuota;
    private final JTextField employeeCpfField;

    private final NumberFormat currencyFormat;

    public ShowQuotasFrame() {
        super("Metas", true, true, true, true);

        tableModel = new DefaultTableModel(
                new Object[]{"ID", "Tipo", "Funcionário", "Início", "Fim", "Valor da Meta"}, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false
            }
        };

        contributingPayments = new JTable(tableModel);
        showCompanyRemainingQuota = new JTextPane();
        showEmployeeRemainingQuota = new JTextPane();
        employeeCpfField = new JTextField(16);

        configureComponents();
        buildLayout();
        refreshAllData();

        setSize(600, 500);
    }

    private void configureComponents() {
        showCompanyRemainingQuota.setEditable(false);
        showEmployeeRemainingQuota.setEditable(false);

        contributingPayments.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        contributingPayments.getTableHeader().setReorderingAllowed(false);
    }

    private void buildLayout() {
        JPanel rootPanel = new JPanel(new BorderLayout(12, 12));
        rootPanel.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));

        JPanel topPanel = new JPanel(new GridLayout(1, 2, 12, 12));
        topPanel.add(createCompanyPanel());
        //topPanel.add(createEmployeePanel());

        JPanel tablePanel = new JPanel(new BorderLayout(8, 8));
        tablePanel.setBorder(BorderFactory.createTitledBorder("Metas cadastradas"));
        tablePanel.add(new JScrollPane(contributingPayments), BorderLayout.CENTER);

        JButton refreshButton = new JButton("Atualizar");
        refreshButton.addActionListener(event -> refreshAllData());

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.add(refreshButton);

        rootPanel.add(topPanel, BorderLayout.NORTH);
        rootPanel.add(tablePanel, BorderLayout.CENTER);
        rootPanel.add(bottomPanel, BorderLayout.SOUTH);

        setContentPane(rootPanel);
    }

}
