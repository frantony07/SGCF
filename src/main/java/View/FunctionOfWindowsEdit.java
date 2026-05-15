package View;

import View.ShowViews.EditReservationWindow;
import View.ShowViews.EditStatusReservationWindow;

import javax.swing.*;

import static View.ItensDefault.addWindowButton;

public class FunctionOfWindowsEdit {
    public FunctionOfWindowsEdit() {
    }

    public static EditReservationWindow editReservations(EditReservationWindow win, JDesktopPane desktop, JPanel taskBar) {
        if (win == null || win.isClosed()) {
            win = new EditReservationWindow();
            win.setVisible(true);
            desktop.add(win);
            win.toFront();
            addWindowButton(win, taskBar);
        } else {
            win.toFront();
        }
        return win;
    }

    public static EditStatusReservationWindow editStatusReservations(EditStatusReservationWindow win, JDesktopPane desktop, JPanel taskBar) {
        if (win == null || win.isClosed()) {
            win = new EditStatusReservationWindow();
            win.setVisible(true);
            desktop.add(win);
            win.toFront();
            addWindowButton(win, taskBar);
        } else {
            win.toFront();
        }
        return win;
    }
}
