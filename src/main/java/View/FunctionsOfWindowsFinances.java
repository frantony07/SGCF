package View;

import View.FinanceView.QuotasPanel;
import View.RegisterViews.ScheduleReservationWindow;
import View.ShowViews.ShowConfirmPaymentFrame;
import View.ShowViews.ShowUser;

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

    public static QuotasPanel addQuotaPanel(QuotasPanel win, JDesktopPane desktop, JPanel taskBar) {
        if (win == null || win.isClosed()) {
            win = new QuotasPanel();
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
