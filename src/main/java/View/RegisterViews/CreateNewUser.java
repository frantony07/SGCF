package View.RegisterViews;

import View.ItensDefault;
import org.ONE.models.ENUM.Permission;
import org.ONE.models.User;
import org.ONE.services.UserServices;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class CreateNewUser extends JInternalFrame {
    private JTextField txtUserName;
    private JPasswordField txtSenha1;
    private JPasswordField txtSenha2;
    private JTextField txtEmail;
    private JComboBox<Permission> cbPermission;

    private UserServices userServices = new UserServices();
    public CreateNewUser(){
        super("Criar novo usuario",true,true,true,true);

        setBackground(new Color(0x7E7D64));
        setOpaque(true);
        setVisible(true);
        setLocation(550,100);

        var url = getClass().getResource("/icons/icons8-creating-20.png");

        if (url != null) {
            ImageIcon icon = new ImageIcon(url);
            setFrameIcon(icon);
        } else {
            System.out.println("o icone é nulo");
        }

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setBackground(new Color(0x7E7D64));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(ItensDefault.createBoldLabel("Nome do Usuário:",14), gbc);

        gbc.gridx = 1;
        txtUserName = new JTextField(20);
        panel.add(txtUserName, gbc);

        gbc.gridx = 0; gbc.gridy++;
        panel.add(ItensDefault.createBoldLabel("Senha:",14), gbc);

        gbc.gridx = 1;
        txtSenha1 = new JPasswordField(20);
        panel.add(txtSenha1, gbc);

        gbc.gridx = 0; gbc.gridy++;
        panel.add(ItensDefault.createBoldLabel("Confirmar Senha:",14), gbc);

        gbc.gridx = 1;
        txtSenha2 = new JPasswordField(20);
        panel.add(txtSenha2, gbc);

        gbc.gridx = 0; gbc.gridy++;
        panel.add(ItensDefault.createBoldLabel("E-mail:",14), gbc);

        gbc.gridx = 1;
        txtEmail = new JTextField(20);
        panel.add(txtEmail, gbc);

        gbc.gridx = 0; gbc.gridy++;
        panel.add(ItensDefault.createBoldLabel("Permissão:",14), gbc);

        gbc.gridx = 1;
        cbPermission = new JComboBox<>(Permission.values());
        panel.add(cbPermission, gbc);

        gbc.gridx = 1; gbc.gridy++;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.fill = GridBagConstraints.NONE;

        JButton btnSalvar = new JButton("Salvar Usuário");
        btnSalvar.addActionListener(e -> salveUser());
        panel.add(btnSalvar, gbc);

        add(panel);
        pack();
        setVisible(true);
    }
    private void salveUser() {

        String userName = txtUserName.getText().trim();
        String senha1 = new String(txtSenha1.getPassword()).trim();
        String senha2 = new String(txtSenha2.getPassword()).trim();
        String email = txtEmail.getText().trim();
        Permission permission = (Permission) cbPermission.getSelectedItem();

        if (userName.isEmpty() || senha1.isEmpty() || senha2.isEmpty() || email.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Preencha todos os campos!",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!Objects.equals(senha1, senha2)) {
            JOptionPane.showMessageDialog(this,
                    "As senhas não coincidem!",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!email.contains("@") || email.length() > 100) {
            JOptionPane.showMessageDialog(this,
                    "E-mail inválido!",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            User newUser = new User();
            newUser.setUserName(userName);
            newUser.setUserPassword(senha1);
            newUser.setPermission(permission);
            newUser.setEmail(email);

            userServices.createNewRecorde(newUser);

            JOptionPane.showMessageDialog(this,
                    "Usuário criado com sucesso!");

            limparCampos();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    "Erro: " + ex.getMessage(),
                    "Erro no Banco",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limparCampos() {
        txtUserName.setText("");
        txtSenha1.setText("");
        txtSenha2.setText("");
        txtEmail.setText("");
    }
}
