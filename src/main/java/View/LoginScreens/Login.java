package View.LoginScreens;

import View.MainScreens;
import org.ONE.models.User;
import org.ONE.services.UserServices;

import javax.naming.AuthenticationException;
import javax.swing.*;

/**
 *
 * @author Dell
 */
public class Login extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger =
            java.util.logging.Logger.getLogger(Login.class.getName());

    private UserServices userServices;

    public Login() {

        initComponents();
        userServices = new UserServices();
        setLocationRelativeTo(null);
        setTitle("Sistema de Reservas");
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        inputSenha = new javax.swing.JPasswordField();
        inputUsuario = new javax.swing.JTextField();
        btnEntrar = new javax.swing.JButton();
        btnPasswordRecovery = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 36));
        jLabel1.setText("Login");

        jLabel2.setText("Usuário");
        jLabel3.setText("Senha");

        inputSenha.addActionListener(this::inputSenhaActionPerformed);

        btnEntrar.setBackground(new java.awt.Color(0, 153, 255));
        btnEntrar.setText("Entrar");
        btnEntrar.addActionListener(this::btnEntrarActionPerformed);

        btnPasswordRecovery.setBackground(new java.awt.Color(0, 153, 255));
        btnPasswordRecovery.setText("Esqueci minha senha");
        btnPasswordRecovery.addActionListener(this::btnPasswordRecoveryActionPerformed);

        javax.swing.GroupLayout layout =
                new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);

        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(74, 74, 74)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel1)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(jLabel2)
                                                .addComponent(jLabel3)
                                                .addComponent(inputUsuario)
                                                .addComponent(inputSenha)
                                                .addComponent(
                                                        btnEntrar,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        248,
                                                        Short.MAX_VALUE
                                                )
                                                .addComponent(
                                                        btnPasswordRecovery,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        248,
                                                        Short.MAX_VALUE
                                                )))
                                .addContainerGap(78, Short.MAX_VALUE))
        );

        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(jLabel1)
                                .addGap(20, 20, 20)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(
                                        inputUsuario,
                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                        javax.swing.GroupLayout.PREFERRED_SIZE
                                )
                                .addGap(15, 15, 15)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(
                                        inputSenha,
                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                        javax.swing.GroupLayout.PREFERRED_SIZE
                                )
                                .addGap(25, 25, 25)
                                .addComponent(btnEntrar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnPasswordRecovery)
                                .addContainerGap(20, Short.MAX_VALUE))
        );
        pack();
    }

    private void btnEntrarActionPerformed(java.awt.event.ActionEvent evt) {

        String usuario = inputUsuario.getText();
        String senha = new String(inputSenha.getPassword());
        if(usuario.isEmpty() || senha.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Preencha todos os campos",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        try {

            User user = userServices.authenticate(usuario, senha);
            if(user != null) {
                JOptionPane.showMessageDialog(
                        this,
                        "Login realizado com sucesso"
                );

                MainScreens mainScreens = new MainScreens();
                mainScreens.setVisible(true);
                dispose();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Usuário ou senha inválidos",
                        "Erro",
                        JOptionPane.ERROR_MESSAGE
                );
            }

        } catch (AuthenticationException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Erro de autenticação",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
            );
            e.printStackTrace();
        }
    }

    private void btnPasswordRecoveryActionPerformed(
            java.awt.event.ActionEvent evt
    ) {

        PasswordRecovery recovery = new PasswordRecovery();
        recovery.setVisible(true);
    }

    private void inputSenhaActionPerformed(
            java.awt.event.ActionEvent evt
    ) {
        btnEntrarActionPerformed(evt);
    }

    private void inputUsuarioActionPerformed(
            java.awt.event.ActionEvent evt
    ) {
    }

    public static void main(String args[]) {

        try {

            for (javax.swing.UIManager.LookAndFeelInfo info :
                    javax.swing.UIManager.getInstalledLookAndFeels()) {

                if ("Nimbus".equals(info.getName())) {

                    javax.swing.UIManager.setLookAndFeel(
                            info.getClassName()
                    );
                    break;
                }
            }

        } catch (Exception ex) {

            logger.log(
                    java.util.logging.Level.SEVERE,
                    null,
                    ex
            );
        }

        java.awt.EventQueue.invokeLater(() ->
                new Login().setVisible(true)
        );
    }

    private javax.swing.JButton btnEntrar;
    private javax.swing.JButton btnPasswordRecovery;
    private javax.swing.JPasswordField inputSenha;
    private javax.swing.JTextField inputUsuario;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
}