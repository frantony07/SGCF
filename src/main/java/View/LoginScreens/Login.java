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
        setVisible(true);
        userServices = new UserServices();
        setLocationRelativeTo(null);
        setTitle("Sistema de Reservas");
        var url = getClass().getResource("/icons/Waterfall.png");

        if (url != null) {
            ImageIcon icon = new ImageIcon(url);
            setIconImage(icon.getImage());
        } else {
            System.out.println("o icone é nulo");
        }
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

            SwingWorker<User, Void> worker = new SwingWorker<>() {

                @Override
                protected User doInBackground() throws Exception {
                    return userServices.authenticate(usuario, senha);
                }

                @Override
                protected void done() {
                    try {
                        User user = doInBackground();

                        if (user != null){
                            JOptionPane.showMessageDialog(null, "Login realizado!");

                            SwingUtilities.invokeLater(MainScreens::new);
                            dispose();

                        }else {

                            JOptionPane.showMessageDialog(null, "Erro no login");
                        }
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Erro no login");

                    }
                }
            };

            worker.execute();

        } catch (Exception e) {

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



    private javax.swing.JButton btnEntrar;
    private javax.swing.JButton btnPasswordRecovery;
    private javax.swing.JPasswordField inputSenha;
    private javax.swing.JTextField inputUsuario;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
}