package View.LoginScreens;

import org.ONE.model.entity.PasswordReset;
import org.ONE.model.entity.User;
import org.ONE.model.services.PasswordResetService;
import org.ONE.model.services.UserService;
import org.ONE.model.services.impl.PasswordResetServiceimpl;
import org.ONE.model.services.impl.UserServiceImpl;
import org.mindrot.jbcrypt.BCrypt;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;

public class ResetPassword extends JFrame {

    private JTextField inputCodigo;
    private JPasswordField inputNovaSenha;
    private JButton btnConfirmar;
    private String email;
    private PasswordResetService passwordResetService;
    private UserService userServices;

    public ResetPassword(String email) {

        this.email = email;
        passwordResetService = new PasswordResetServiceimpl();
        userServices = new UserServiceImpl();
        initComponents();
    }

    private void initComponents() {

        setTitle("Redefinir Senha");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        setLayout(new BorderLayout());

        JLabel titulo =
                new JLabel("Redefinir Senha");

        titulo.setHorizontalAlignment(
                SwingConstants.CENTER
        );

        add(titulo, BorderLayout.NORTH);

        JPanel painel = new JPanel();
        painel.setLayout(
                new GridLayout(6,1,10,10)
        );

        painel.setBorder(
                BorderFactory.createEmptyBorder(
                        20,
                        30,
                        20,
                        30
                )
        );

        painel.add(
                new JLabel("Código enviado")
        );

        inputCodigo = new JTextField();
        painel.add(inputCodigo);

        painel.add(
                new JLabel("Nova senha")
        );

        inputNovaSenha =
                new JPasswordField();
        painel.add(inputNovaSenha);

        btnConfirmar =
                new JButton("Confirmar");
        painel.add(btnConfirmar);
        add(painel, BorderLayout.CENTER);

        btnConfirmar.addActionListener(
                this::btnConfirmarActionPerformed
        );
    }

    private void btnConfirmarActionPerformed(
            java.awt.event.ActionEvent evt
    ) {
        String codigo =
                inputCodigo.getText();

        String novaSenha =
                new String(
                        inputNovaSenha.getPassword()
                );

        if(codigo.isEmpty()
                || novaSenha.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Preencha todos os campos"
            );

            return;
        }

        try {
            PasswordReset token =
                    passwordResetService
                            .findValidToken(codigo);
            if(token == null) {

                JOptionPane.showMessageDialog(
                        this,
                        "Código inválido"
                );

                return;
            }

            if(token.isUsed()) {
                JOptionPane.showMessageDialog(
                        this,
                        "Código já utilizado"
                );

                return;
            }

            if(token.getExpiration().isBefore(LocalDateTime.now())) {
                JOptionPane.showMessageDialog(
                        this,
                        "Código expirado"
                );

                return;
            }

            User user = token.getUser();
            user.setUserPassword(
                    BCrypt.hashpw(
                            novaSenha,
                            BCrypt.gensalt()
                    )
            );

            userServices.updateRecorde(user);
            token.setUsed(true);
            passwordResetService.create(token);

            JOptionPane.showMessageDialog(
                    this,
                    "Senha alterada com sucesso"
            );

            dispose();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                    this,
                    "Erro ao redefinir senha"
            );
            e.printStackTrace();
        }
    }
}