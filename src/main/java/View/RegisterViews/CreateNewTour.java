package View.RegisterViews;

import Functions.FunctionsByMain.LoadCountry;
import Functions.SelectFunctions;
import View.ItensDefault;
import org.ONE.models.ENUM.CountryTour;
import org.ONE.models.ENUM.Language;
import org.ONE.models.Passeio;
import org.ONE.services.PasseioServices;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CreateNewTour extends JInternalFrame {

    private JTextField txtNome;
    private JTextField txtPreco;
    private JTextField txtDuracao;
    private JTextField txtLocalizacao;
    private JTextField txtKm;
    private CountryTour countryTour;

    private PasseioServices passeioService;
    private JComboBox<CountryTour> cbCountry;

    public CreateNewTour(){
        super("Criar novo passeio" , true,true,true,true);

        setVisible(true);
        setBackground(new Color(0x7E7D64));
        setOpaque(true);
        setSize(600,400);
        setLocation(550,100);

        var url = getClass().getResource("/icons/icons8-creating-20.png");

        if (url != null) {
            ImageIcon icon = new ImageIcon(url);
            setFrameIcon(icon);
        } else {
            System.out.println("o icone é nulo");
        }

        passeioService = new PasseioServices();

        setTitle("Cadastrar Novo Passeio");
        setSize(500, 400);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(0x7E7D64));
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(ItensDefault.createBoldLabel("Nome do Passeio:",14), gbc);

        gbc.gridx = 1;
        txtNome = new JTextField(15);
        panel.add(txtNome, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(ItensDefault.createBoldLabel("Preço:",14), gbc);

        gbc.gridx = 1;
        txtPreco = new JTextField(15);
        panel.add(txtPreco, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(ItensDefault.createBoldLabel("Duração (minutos):",14), gbc);

        gbc.gridx = 1;
        txtDuracao = new JTextField(15);
        panel.add(txtDuracao, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(ItensDefault.createBoldLabel("Localização:",14), gbc);

        gbc.gridx = 1;
        txtLocalizacao = new JTextField(15);
        panel.add(txtLocalizacao, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(ItensDefault.createBoldLabel("Distância (KM):",14), gbc);

        gbc.gridx = 1;
        txtKm = new JTextField(15);
        panel.add(txtKm, gbc);


        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(ItensDefault.createBoldLabel("País do Passeio:",14), gbc);

        gbc.gridx = 1;
        cbCountry = new JComboBox<>(CountryTour.values());
        panel.add(cbCountry, gbc);


        gbc.gridx = 1;
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.fill = GridBagConstraints.NONE;

        JButton btnSalvar = new JButton("Salvar Passeio");
        btnSalvar.addActionListener(e -> salvarPasseio());

        panel.add(btnSalvar, gbc);


        add(panel);


        setVisible(true);
    }

    private void salvarPasseio() {
        try {

            String nome = txtNome.getText();
            String precoTexto = txtPreco.getText();
            String duracaoTexto = txtDuracao.getText();
            String localizacao = txtLocalizacao.getText();
            String kmTexto = txtKm.getText();


            if (nome.isEmpty() || precoTexto.isEmpty() || duracaoTexto.isEmpty()
                    || localizacao.isEmpty() || kmTexto.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Preencha todos os campos!",
                        "Erro",
                        JOptionPane.ERROR_MESSAGE
                );
                return;
            }

            float preco;
            long duracao;
            long km;
            countryTour = (CountryTour) cbCountry.getSelectedItem();

            try {
                preco = Float.parseFloat(precoTexto);
                duracao = Long.parseLong(duracaoTexto);
                km = Long.parseLong(kmTexto);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(
                        this,
                        "Digite apenas números válidos nos campos numéricos!",
                        "Erro",
                        JOptionPane.ERROR_MESSAGE
                );
                return;
            }



            Passeio passeio = new Passeio(preco, duracao, countryTour, km, nome, localizacao);

            passeioService.createNewRecorde(passeio);

            JOptionPane.showMessageDialog(this,passeio.toString(), "passeio cadastrado com sucesso",JOptionPane.INFORMATION_MESSAGE);

            txtDuracao.setText("");
            txtKm.setText("");
            txtLocalizacao.setText("");
            txtNome.setText("");
            txtPreco.setText("");


        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage());
        }
    }
}
