package View;

import javax.swing.*;
import java.awt.*;

public class ItensDefault {
    public static JLabel createBoldLabel(String text, int size) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("SansSerif",Font.BOLD, size));
        return label;
    }
    public static void addWindowButton(JInternalFrame frame, JPanel taskBar) {

        JButton btn = new JButton(frame.getTitle());
        btn.setFocusPainted(false);

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
