package View.LoginScreens;

import Functions.GenerateCode;
import org.ONE.models.PasswordReset;
import org.ONE.models.User;
import org.ONE.services.EmailService;
import org.ONE.services.PasswordResetService;
import org.ONE.services.UserServices;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;

public class PasswordRecovery extends JFrame {

    private JTextField inputEmail;
    private JButton btnEnviar;
    private JButton btnVoltar;
    private UserServices userServices;
    private PasswordResetService passwordResetService;
    private EmailService emailService;

    public PasswordRecovery() {

        userServices = new UserServices();
        passwordResetService = new PasswordResetService();
        emailService = new EmailService();
        initComponents();
    }

    private void initComponents() {

        setTitle("Recuperar Senha");
        setSize(400, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel titulo = new JLabel("Recuperação de Senha");

        titulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        add(titulo, BorderLayout.NORTH);

        JPanel painel = new JPanel();
        painel.setLayout(new GridLayout(4, 1, 10, 10));
        painel.setBorder(
                BorderFactory.createEmptyBorder(20, 30, 20, 30)
        );

        painel.add(new JLabel("Digite seu email"));
        inputEmail = new JTextField();
        painel.add(inputEmail);

        btnEnviar = new JButton("Enviar");
        painel.add(btnEnviar);

        btnVoltar = new JButton("Voltar");
        painel.add(btnVoltar);
        add(painel, BorderLayout.CENTER);

        btnEnviar.addActionListener(
                this::btnEnviarActionPerformed
        );

        btnVoltar.addActionListener(e -> dispose());
    }

    private void btnEnviarActionPerformed(
            java.awt.event.ActionEvent evt
    ) {

        String email = inputEmail.getText();

        if(email.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Digite um email",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }

        try {

            User user = userServices.findByEmail(email);
            if(user == null) {
                JOptionPane.showMessageDialog(
                        this,
                        "Usuário não encontrado",
                        "Erro",
                        JOptionPane.ERROR_MESSAGE
                );

                return;
            }

            String codigo = GenerateCode.generateCode();
            PasswordReset passwordReset = new PasswordReset();
            passwordReset.setToken(codigo);

            passwordReset.setExpiration(
                    LocalDateTime.now().plusMinutes(30)
            );

            passwordReset.setUsed(false);
            passwordReset.setUser(user);
            passwordResetService.create(passwordReset);

            emailService.sendEmail(
                    email,
                    codigo,
                    ""
            );

            JOptionPane.showMessageDialog(
                    this,
                    "Código enviado para o email"
            );

            ResetPassword resetPassword =
                    new ResetPassword(email);
            resetPassword.setVisible(true);
            dispose();

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Erro ao enviar recuperação",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
            );

            e.printStackTrace();
        }
    }
}