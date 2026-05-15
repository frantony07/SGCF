package View;

import View.ShowViews.ShowConfirmPaymentFrame;
import View.ShowViews.ShowQuotasFrame;

import javax.swing.*;

import static View.ItensDefault.addWindowButton;

public class FunctionsOfWindowsFinances {
    public FunctionsOfWindowsFinances() {
    }

    public static ShowConfirmPaymentFrame confirmPaymentPanel(ShowConfirmPaymentFrame win, JDesktopPane desktop, JPanel taskBar) {
        if (win == null || win.isClosed()) {
            win = new ShowConfirmPaymentFrame();
            win.setVisible(true);
            desktop.add(win);
            win.toFront();
            addWindowButton(win, taskBar);
        } else {
            win.toFront();
        }
        return win;
    }

    public static ShowQuotasFrame addQuotaPanel(ShowQuotasFrame win, JDesktopPane desktop, JPanel taskBar) {
        if (win == null || win.isClosed()) {
            win = new ShowQuotasFrame();
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
