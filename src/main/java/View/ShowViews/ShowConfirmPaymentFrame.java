package View.ShowViews;

import Controller.Impl.PaymentControllerImpl;
import Controller.PaymentController;
import org.ONE.model.entity.ENUM.Status;
import org.ONE.model.entity.PayModel;
import org.ONE.model.services.impl.PayServiceImpl;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class ShowConfirmPaymentFrame extends JInternalFrame {

    private PaymentController payController = new PaymentControllerImpl();

    private JTable paymentsTable;
    private DefaultTableModel tableModel;

    private JTextField paymentIDTextField;
    private JButton confirmPaymentButton;

    public ShowConfirmPaymentFrame() {
        super("Confirmar Pagamento", true, true, true, true);
        setLocation(450, 80);

        initComponents();
        loadData();
    }

    private void initComponents() {

        JLabel pendingPaymentsLabel = new JLabel("Pagamentos Pendentes");
        JLabel paymentIDLabel = new JLabel("ID do Pagamento:");

        paymentIDTextField = new JTextField(10);
        confirmPaymentButton = new JButton("Confirmar");

        tableModel = new DefaultTableModel();
        tableModel.addColumn("ID");
        tableModel.addColumn("Cliente");
        tableModel.addColumn("Valor");
        tableModel.addColumn("Status");

        paymentsTable = new JTable(tableModel);
        paymentsTable.getTableHeader().setReorderingAllowed(false);

        JScrollPane scrollPane = new JScrollPane(paymentsTable);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);

        layout.setHorizontalGroup(
                layout.createParallelGroup()
                        .addGroup(layout.createSequentialGroup()
                                .addGap(20)
                                .addGroup(layout.createParallelGroup()
                                        .addComponent(pendingPaymentsLabel)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(paymentIDLabel)
                                                .addGap(10)
                                                .addComponent(paymentIDTextField))
                                        .addComponent(confirmPaymentButton)
                                        .addComponent(scrollPane, 550, 550, 550))
                                .addGap(20))
        );

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGap(20)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(paymentIDLabel)
                                .addComponent(paymentIDTextField))
                        .addGap(10)
                        .addComponent(confirmPaymentButton)
                        .addGap(40)
                        .addComponent(pendingPaymentsLabel)
                        .addGap(10)
                        .addComponent(scrollPane, 300, 300, 300)
                        .addGap(20)
        );

        confirmPaymentButton.addActionListener(e -> confirmPayment());

        pack();
    }

    private void loadData() {

        tableModel.setRowCount(0);

        List<PayModel> list = payController.findAll();

        for (PayModel payModel : list) {
            tableModel.addRow(new Object[]{
                    payModel.getID(),
                    payModel.getReservation().getName(),
                    payModel.getTotal_account(),
                    payModel.getStatus()
            });
        }
    }

    private void confirmPayment() {

        try {
            String text = paymentIDTextField.getText().trim();

            if (text.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Digite o ID do pagamento.",
                        "Erro",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            int idPayment = Integer.parseInt(text);

            PayModel payment = payController.findById(idPayment);

            if (payment == null) {
                JOptionPane.showMessageDialog(this,
                        "Pagamento não encontrado.",
                        "Erro",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            payment.setStatus(Status.CONFIRMADA);

            payController.updateRecords(payment);

            JOptionPane.showMessageDialog(this,
                    "Pagamento confirmado com sucesso!");

            loadData();

            paymentIDTextField.setText("");

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(this,
                    "ID inválido. Digite apenas números.",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Erro ao confirmar pagamento.",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}