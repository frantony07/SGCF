package View.RegisterViews;

import Controller.EmployeeController;
import Controller.Impl.EmployeeControllerImpl;
import Controller.Record.FuncionarioDTO;
import Functions.CPF;
import Functions.loadLanguage;
import View.ItensDefault;
import org.ONE.model.entity.ENUM.Language;
import org.ONE.model.entity.Funcionario;
import org.ONE.model.services.FuncionarioService;
import org.ONE.model.services.impl.FuncionarioServiceImpl;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class CreateNewEmployee extends JInternalFrame {
    ArrayList<Language> languageArrayList = new ArrayList<>();
    EmployeeController funcionarioController = new EmployeeControllerImpl();

    public CreateNewEmployee() {
        super("Criar novo funcionário", true, true, true, true);

        setSize(600, 400);
        setLocation(550, 100);

        var url = getClass().getResource("/icons/icons8-creating-20.png");
        if (url != null) {
            setFrameIcon(new ImageIcon(url));
        }

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(ItensDefault.FUNDO_CLARO);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(ItensDefault.createBoldLabel("Nome do funcionário:",14), gbc);

        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        JTextField getName = new JTextField(20);
        panel.add(getName, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.NONE;
        panel.add(ItensDefault.createBoldLabel("CPF do funcionário:",14), gbc);

        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        JTextField getCPF = new JTextField(15);
        panel.add(getCPF, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.NONE;
        panel.add(ItensDefault.createBoldLabel("Idiomas:",14), gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        languageArrayList = loadLanguage.getLanguage();
        JList<Language> listLanguage = new JList<>(languageArrayList.toArray(new Language[0]));
        listLanguage.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        JScrollPane scrollPane = new JScrollPane(listLanguage);
        scrollPane.setPreferredSize(new Dimension(150, 80));
        panel.add(scrollPane, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.fill = GridBagConstraints.NONE;
        JButton btnSalvar = getJButton(getName, getCPF, listLanguage);
        panel.add(btnSalvar, gbc);

        add(panel);
    }

    private @NotNull JButton getJButton(JTextField getName, JTextField getCPF, JList<Language> listLanguage) {
        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(e -> {
            String nome = getName.getText().trim();
            String cpf = getCPF.getText().trim();
            java.util.List<Language> languages = listLanguage.getSelectedValuesList();

            if (nome.isEmpty() || cpf.isEmpty() || languages.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Por favor, preencha todos os campos e selecione ao menos um idioma.",
                        "Aviso",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (!CPF.isValidCPF(cpf)) {
                JOptionPane.showMessageDialog(this, "Por favor, insira um CPF válido", "CPF Inválido", JOptionPane.WARNING_MESSAGE);
                return;
            }

            try {
                FuncionarioDTO employee = new FuncionarioDTO(cpf, nome, languages);
                funcionarioController.createNewRecorde(employee);

                String mensagem = "Funcionário: " + nome + "\nCPF: " + cpf + "\nIdiomas: " + languages;
                JOptionPane.showMessageDialog(this, mensagem, "Sucesso", JOptionPane.INFORMATION_MESSAGE);

                getCPF.setText("");
                getName.setText("");
                listLanguage.clearSelection();

            } catch (Exception ex) {
                if (ex.getMessage().contains("Duplicate") || ex.getMessage().contains("unique")) {
                    JOptionPane.showMessageDialog(this,
                            "Erro: Já existe um funcionário cadastrado com este CPF.",
                            "Conflito de Dados",
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this,
                            "Erro ao salvar no banco de dados: " + ex.getMessage(),
                            "Erro Interno",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        return btnSalvar;
    }
}