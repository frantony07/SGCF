package View.RegisterViews;

import javax.swing.*;
import java.awt.*;

public class CreateNewTour extends JInternalFrame {

    public CreateNewTour(){
        super("Criar novo passeio" , true,true,true,true);

        setVisible(true);
        setBackground(new Color(0x7E7D64));
        setOpaque(true);
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
