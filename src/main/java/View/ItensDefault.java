package View;

import javax.swing.*;
import java.awt.*;

public class ItensDefault {

    public static final Color AZUL_MARINHO      = new Color(0x0F2233);
    public static final Color TEAL_ESCURO = new Color(0x1B3A52);
    public static final Color TEAL_MEDIO  = new Color(0x0E6E87);
    public static final Color TEAL_BOTAO  = new Color(0x1A5070);
    public static final Color FUNDO_CLARO  = new Color(0xEFF5FA);

    public static final Font FONTE_ROTULO    = new Font("SansSerif", Font.BOLD,  14);
    public static final Font FONTE_MENU     = new Font("SansSerif", Font.BOLD,  14);
    public static final Font FONTE_BARRA  = new Font("SansSerif", Font.PLAIN, 12);
    public static final Font FONTE_TITULO    = new Font("SansSerif", Font.BOLD,  34);
    public static final Font FONTE_SUBTITULO = new Font("SansSerif", Font.PLAIN, 14);

    public static JLabel createBoldLabel(String text, int size) {
        JLabel label = new JLabel(text);
        label.setFont(FONTE_ROTULO.deriveFont(Font.BOLD, (float) size));
        return label;
    }

    public static JLabel createLightLabel(String text, int size) {
        JLabel label = createBoldLabel(text, size);
        label.setForeground(Color.WHITE);
        return label;
    }

    public static void addWindowButton(JInternalFrame frame, JPanel taskBar) {
        JButton btn = new JButton(frame.getTitle());
        btn.setFocusPainted(false);
        btn.setForeground(Color.WHITE);
        btn.setBackground(TEAL_BOTAO);
        btn.setFont(FONTE_BARRA);
        btn.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(TEAL_MEDIO, 1),
            BorderFactory.createEmptyBorder(4, 10, 4, 10)
        ));

        btn.addActionListener(e -> {
            try {
                frame.setIcon(false);
                frame.setSelected(true);
                frame.toFront();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        taskBar.add(btn);
        taskBar.revalidate();
        taskBar.repaint();

        frame.addInternalFrameListener(new javax.swing.event.InternalFrameAdapter() {
            @Override
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent e) {
                taskBar.remove(btn);
                taskBar.revalidate();
                taskBar.repaint();
            }
        });
    }
}
