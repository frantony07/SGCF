package View.RegisterViews;

import javax.swing.*;
import java.awt.*;

public class CreateNewUser extends JInternalFrame {
    public CreateNewUser(){
        super("Criar novo usuario",true,true,true,true);

        setBackground(new Color(0x7E7D64));
        setOpaque(true);
        setVisible(true);
        setSize(800,600);
        setLocation(550,100);

        var url = getClass().getResource("/icons/icons8-creating-20.png");

        if (url != null) {
            ImageIcon icon = new ImageIcon(url);
            setFrameIcon(icon);
        } else {
            System.out.println("o icone é nulo");
        }

    }
}
