package View.ShowViews;



import org.ONE.models.ENUM.Status;
import org.ONE.models.QuotaModel;
import org.ONE.services.FuncionarioServices;
import org.ONE.services.PayServices;
import org.ONE.services.QuotaServices;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

public class ShowQuotasFrame extends javax.swing.JInternalFrame {

    private final QuotaServices quotaServices = new QuotaServices();
    private final FuncionarioServices funcionarioServices = new FuncionarioServices();
    private final PayServices payServices = new PayServices();

    private final JTable contributingPayments;
    private final DefaultTableModel tableModel;
    private final JTextPane showCompanyRemainingQuota;
    private final JTextPane showEmployeeRemainingQuota;
    private final JTextField employeeCpfField;

    private final NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

    public ShowQuotasFrame() {
        super("Metas", true, true, true, true);

        tableModel = new DefaultTableModel(
                new Object[]{"ID", "Tipo", "Funcionário", "Início", "Fim", "Valor da Meta"}, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        contributingPayments = new JTable(tableModel);
        showCompanyRemainingQuota = new JTextPane();
        showEmployeeRemainingQuota = new JTextPane();
        employeeCpfField = new JTextField(16);

        configureComponents();
        buildLayout();
        refreshAllData();

        setSize(700, 500);
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
        topPanel.add(createEmployeePanel());

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

    private JPanel createCompanyPanel() {
        JPanel panel = new JPanel(new BorderLayout(8, 8));
        panel.setBorder(BorderFactory.createTitledBorder("Meta da Empresa"));

        JButton createCompanyQuotaButton = new JButton("Criar Meta da Empresa");
        createCompanyQuotaButton.addActionListener(event -> openCompanyQuotaDialog());

        panel.add(new JScrollPane(showCompanyRemainingQuota), BorderLayout.CENTER);
        panel.add(createCompanyQuotaButton, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createEmployeePanel() {
        JPanel panel = new JPanel(new BorderLayout(8, 8));
        panel.setBorder(BorderFactory.createTitledBorder("Meta Pessoal"));

        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchPanel.add(new JLabel("CPF:"));
        searchPanel.add(employeeCpfField);

        JButton searchButton = new JButton("Consultar");
        searchButton.addActionListener(event -> refreshEmployeeQuota());
        searchPanel.add(searchButton);

        JButton createEmployeeQuotaButton = new JButton("Criar Meta Pessoal");
        createEmployeeQuotaButton.addActionListener(event -> openEmployeeQuotaDialog());

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(createEmployeeQuotaButton);

        panel.add(searchPanel, BorderLayout.NORTH);
        panel.add(new JScrollPane(showEmployeeRemainingQuota), BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }

    private void refreshCompanyQuota() {
        try {
            QuotaModel activeQuota = quotaServices.findActiveQuotaById(null);

            if (activeQuota == null) {
                showCompanyRemainingQuota.setText("A companhia não possui meta ativa");
                return;
            }

            showCompanyRemainingQuota.setText(buildCompanyQuotaText(activeQuota));
        } catch (Exception err) {
            System.out.println("Erro no refreshCompanyQuota");
            showCompanyRemainingQuota.setText("Erro ao carregar a meta da empresa");
        }
    }

    private void refreshEmployeeQuota() {
        try {
            String cpf = employeeCpfField.getText().trim();

            if (cpf.isBlank()) {
                showEmployeeRemainingQuota.setText("Digite um CPF e clique em Consultar");
                return;
            }

            Long employeeId = funcionarioServices.findIdByCPF(cpf);

            if (employeeId == null) {
                showEmployeeRemainingQuota.setText("Não foi encontrado nenhum funcionário com este CPF");
                return;
            }

            QuotaModel activeQuota = quotaServices.findActiveQuotaById(employeeId);

            if (activeQuota == null) {
                showEmployeeRemainingQuota.setText("Este funcionário não possui uma meta ativa");
                return;
            }

            showEmployeeRemainingQuota.setText(buildEmployeeQuotaText(activeQuota, employeeId));
        } catch (Exception err) {
            System.out.println("Erro no refreshEmployeeQuota");
            showEmployeeRemainingQuota.setText("Erro ao carregar a meta do funcionário");
        }
    }

    private void refreshQuotaTable() {
        try {
            tableModel.setRowCount(0);

            List<QuotaModel> quotas = quotaServices.findAllQuotas();

            for (QuotaModel quota : quotas) {
                Long employeeId = quota.getIdFuncionario();
                boolean isCompanyQuota = employeeId == null;

                tableModel.addRow(new Object[]{
                        quota.getId(),
                        isCompanyQuota ? "Empresa" : "Funcionário",
                        isCompanyQuota ? "-" : quota.getIdFuncionario(),
                        quota.getStartDate(),
                        quota.getEndDate(),
                        currencyFormat.format(quota.getTargetValue())
                });
            }
        } catch (Exception err) {
            System.out.println("Erro em refreshQuotaTable");
        }
    }

    private void refreshAllData() {
        try {
            refreshCompanyQuota();
            refreshEmployeeQuota();
            refreshQuotaTable();
        } catch (Exception err) {
            System.out.println("Erro ao atualizar todas as informações");
        }
    }

    private String buildCompanyQuotaText(QuotaModel activeQuota) {
        LocalDate startDate = activeQuota.getStartDate();
        LocalDate endDate = activeQuota.getEndDate();

        double earned = payServices.sumEarningsForCompany(
                Status.CONFIRMADA,
                startDate,
                endDate
        );

        return buildQuotaSummaryText(activeQuota, earned);
    }

    private String buildEmployeeQuotaText(QuotaModel activeQuota, Long employeeId) {
        LocalDate startDate = activeQuota.getStartDate();
        LocalDate endDate = activeQuota.getEndDate();

        double earned = payServices.sumEarningsByEmployee(
                employeeId,
                Status.CONFIRMADA,
                startDate,
                endDate
        );

        return buildQuotaSummaryText(activeQuota, earned);
    }

    private String buildQuotaSummaryText(QuotaModel activeQuota, double earned) {
        double target = activeQuota.getTargetValue();
        double progress = target <= 0 ? 0 : (earned / target) * 100.0;
        double remaining = Math.max(target - earned, 0);

        String completedMessage = earned >= target ? "\n\nMeta completa, parabéns!" : "";

        return "Período: " + activeQuota.getStartDate() + " → " + activeQuota.getEndDate() +
                "\nTotal faturado: " + currencyFormat.format(earned) +
                "\nMeta: " + currencyFormat.format(target) +
                "\nProgresso: " + String.format("%.2f%%", progress) +
                "\nFaltam: " + currencyFormat.format(remaining) +
                completedMessage;
    }

    private void openCompanyQuotaDialog() {
        QuotaModel activeQuota = quotaServices.findActiveQuotaById(null);

        if (activeQuota != null) {
            JOptionPane.showMessageDialog(
                    this,
                    "A companhia já possui uma meta ativa",
                    "Meta ativa",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        String valueText = JOptionPane.showInputDialog(
                this,
                "Digite o valor da meta da empresa:",
                "Criar Meta da Empresa",
                JOptionPane.PLAIN_MESSAGE
        );

        if (valueText == null) {
            return;
        }

        Double targetValue = parseTargetValue(valueText);

        if (targetValue == null) {
            showInvalidValueMessage();
            return;
        }

        LocalDate startDate = LocalDate.now();
        LocalDate endDate = startDate.plusDays(30);

        quotaServices.createQuota(new QuotaModel(startDate, endDate, targetValue));

        JOptionPane.showMessageDialog(
                this,
                "Meta da empresa criada com sucesso\nData limite: " + endDate,
                "Sucesso",
                JOptionPane.INFORMATION_MESSAGE
        );

        refreshAllData();
    }

    private void openEmployeeQuotaDialog() {
        JPanel panel = new JPanel (new GridLayout(2, 2, 8, 8));

        JTextField cpfField = new JTextField(employeeCpfField.getText().trim());
        JTextField targetValueField = new JTextField();

        panel.add(new JLabel("CPF do Funcionário:"));
        panel.add(cpfField);
        panel.add(new JLabel("Valor da Meta:"));
        panel.add(targetValueField);

        int result = JOptionPane.showConfirmDialog(
                this,
                panel,
                "Criar Meta Pessoal",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        if (result != JOptionPane.OK_OPTION) {
            return;
        }

        String cpf = cpfField.getText().trim();

        if (cpf.isBlank()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Digite o CPF do Funcionário",
                    "CPF obrigatório",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        Long employeeId = funcionarioServices.findIdByCPF(cpf);

        if (employeeId == null) {
            JOptionPane.showMessageDialog(
                    this,
                    "Não foi encontrado nenhum funcionário com este CPF",
                    "Funcionário não encontrado",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        QuotaModel activeQuota = quotaServices.findActiveQuotaById(employeeId);

        if (activeQuota != null) {
            JOptionPane.showMessageDialog(
                    this,
                    "Este funcionário já possui uma meta ativa",
                    "Meta Ativa",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        Double targetValue = parseTargetValue(targetValueField.getText());

        if (targetValue == null) {
            showInvalidValueMessage();
            return;
        }

        LocalDate startDate = LocalDate.now();
        LocalDate endDate = startDate.plusDays(30);

        quotaServices.createQuota(new QuotaModel(startDate, endDate, targetValue, employeeId));

        employeeCpfField.setText(cpf);

        JOptionPane.showMessageDialog(
                this,
                "Meta pessoal criada com sucesso",
                "Sucesso",
                JOptionPane.INFORMATION_MESSAGE
        );

        refreshAllData();
    }

    private Double parseTargetValue(String valueText) {
        try {
            double value = Double.parseDouble(valueText.trim().replace(",", "."));

            if (value <= 0) {
                return null;
            }

            return value;
        } catch (NumberFormatException err) {
            return null;
        }
    }

    private void showInvalidValueMessage() {
        JOptionPane.showMessageDialog(
                this,
                "Digite um valor válido maior que zero",
                "Valor Inválido",
                JOptionPane.WARNING_MESSAGE
        );
    }
}
