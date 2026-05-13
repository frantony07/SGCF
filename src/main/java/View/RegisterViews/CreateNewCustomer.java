package View.RegisterViews;

import Functions.CNPJ;
import Functions.CPF;
import Functions.loadLanguage;
import View.ItensDefault;
import org.ONE.models.Cliente;
import org.ONE.models.ENUM.CountryCostumer;
import org.ONE.models.ENUM.Language;
import org.ONE.services.ClienteServices;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class CreateNewCustomer extends JInternalFrame {

    CountryCostumer CountryCostumer;
    ArrayList<Language> languageArrayList = loadLanguage.getLanguage();
    ClienteServices clienteServices = new ClienteServices();

    public CreateNewCustomer(){
        super("Criar novo cliente" , true,true,true,true);

        setOpaque(true);
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
        panel.setBackground(ItensDefault.FUNDO_CLARO);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; gbc.gridy = 0;


        panel.add(ItensDefault.createBoldLabel("Nome do Cliente:",14), gbc);

        gbc.gridx = 1;
        JTextField txtName = new JTextField(20);
        panel.add(txtName, gbc);

        gbc.gridx = 0; gbc.gridy = 1;

        panel.add(ItensDefault.createBoldLabel("Tipo de Documento",14),gbc);

        JPanel pnlRadio = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JRadioButton rbCpf = new JRadioButton("CPF", true);
        JRadioButton rbCnpj = new JRadioButton("CNPJ");
        ButtonGroup group = new ButtonGroup();
        group.add(rbCpf);
        group.add(rbCnpj);
        pnlRadio.add(rbCpf);
        pnlRadio.add(rbCnpj);
        gbc.gridx = 1;
        panel.add(pnlRadio, gbc);

        gbc.gridx = 0; gbc.gridy = 2;

        JLabel lblDoc = new JLabel("Número (CPF):");
        lblDoc.setFont(new Font("SansSerif",Font.BOLD,14));
        panel.add(lblDoc, gbc);

        gbc.gridx = 1;
        JTextField txtDoc = new JTextField(15);
        panel.add(txtDoc, gbc);

        rbCpf.addActionListener(e -> lblDoc.setText("Número (CPF):"));
        rbCnpj.addActionListener(e -> lblDoc.setText("Número (CNPJ):"));

        gbc.gridx = 0; gbc.gridy = 3;


        panel.add(ItensDefault.createBoldLabel("País de Origem:",14),gbc);


        gbc.gridx = 1;
        JComboBox<CountryCostumer> cbCountry = new JComboBox<>(CountryCostumer.values());
        panel.add(cbCountry, gbc);

        gbc.gridx = 0; gbc.gridy = 4;

        panel.add(ItensDefault.createBoldLabel("Idiomas:",14),gbc);

        gbc.gridx = 1;

        JList<Language> listLanguages = new JList<>(languageArrayList.toArray(new Language[0]));
        listLanguages.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        JScrollPane scroll = new JScrollPane(listLanguages);
        scroll.setPreferredSize(new Dimension(150, 80));
        panel.add(scroll, gbc);

        gbc.gridx = 1; gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.fill = GridBagConstraints.NONE;
        JButton btnSalvar = getBtnSalvar(txtName, txtDoc, rbCpf, cbCountry, listLanguages);
        panel.add(btnSalvar, gbc);

        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.getViewport().setBackground(ItensDefault.FUNDO_CLARO);

        add(scrollPane);

        pack();
        setVisible(true);

    }

    private @NotNull JButton getBtnSalvar(JTextField txtName, JTextField txtDoc, JRadioButton rbCpf,
                                          JComboBox<CountryCostumer> cbCountry, JList<Language> listLang) {
        JButton btn = new JButton("Salvar Cliente");
        btn.addActionListener(e -> {
            String name = txtName.getText();
            String doc = txtDoc.getText();
            CountryCostumer country = (CountryCostumer) cbCountry.getSelectedItem();
            java.util.List<Language> selectedLangs = listLang.getSelectedValuesList();

            if (name.isEmpty() || doc.isEmpty() || selectedLangs.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Preencha todos os campos!", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String cpf = rbCpf.isSelected() ? doc : null;
            String cnpj = !rbCpf.isSelected() ? doc : null;

            if (cpf != null && !CPF.isValidCPF(cpf)) {
                JOptionPane.showMessageDialog(this, "CPF Inválido!"); return;
            }
            if (cnpj != null && !CNPJ.isValidCNPJ(cnpj)) {
                JOptionPane.showMessageDialog(this, "CNPJ Inválido!"); return;
            }

            try {

                Cliente newCustomer = new Cliente((ArrayList<Language>) selectedLangs,country,cnpj,cpf,name);
                clienteServices.createNewRecord(newCustomer);

                JOptionPane.showMessageDialog(this, "Cliente " + name + " salvo com sucesso!");

                txtName.setText("");
                txtDoc.setText("");
                listLang.clearSelection();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage(), "Erro no Banco", JOptionPane.ERROR_MESSAGE);
            }
        });
        return btn;
    }


}
