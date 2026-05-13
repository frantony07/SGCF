package View;

import View.ShowViews.*;

import javax.swing.*;

import static View.ItensDefault.addWindowButton;

public class FunctionsOfWindowsShow {

    public FunctionsOfWindowsShow() {
    }

    public static ShowTours showTour(ShowTours showTours , JDesktopPane desktop , JPanel taskBar){
        if (showTours == null || showTours.isClosed()){

            showTours = new ShowTours();
            showTours.setVisible(true);

            desktop.add(showTours);

            showTours.toFront();

            addWindowButton(showTours,taskBar);

        }else {

            showTours.toFront();
        }
        return showTours;
    }

    public static ShowEmployee showEmployees(ShowEmployee showEmployee , JDesktopPane desktop , JPanel taskBar){
        if (showEmployee == null || showEmployee.isClosed()){

            showEmployee = new ShowEmployee();
            desktop.add(showEmployee);
            showEmployee.setVisible(true);
            showEmployee.toFront();

            addWindowButton(showEmployee, taskBar);

        } else {
            showEmployee.toFront();
        }
        return showEmployee;
    }

    public static ShowCustomer showCustomers(ShowCustomer showCustomer , JDesktopPane desktop , JPanel taskBar){
        if (showCustomer == null || showCustomer.isClosed()){

            showCustomer = new ShowCustomer();
            desktop.add(showCustomer);
            showCustomer.setVisible(true);
            showCustomer.toFront();

            addWindowButton(showCustomer, taskBar);

        } else {
            showCustomer.toFront();
        }
        return showCustomer;
    }

    public static ShowUser showUsers(ShowUser showUser , JDesktopPane desktop , JPanel taskBar) {
        if (showUser == null || showUser.isClosed()) {

            showUser = new ShowUser();
            showUser.setVisible(true);

            desktop.add(showUser);
            showUser.toFront();

            addWindowButton(showUser, taskBar);

        } else {
            showUser.toFront();
        }
        return  showUser;
    }

    public static ShowReservations showReservations(ShowReservations showReservations,JDesktopPane desktop , JPanel taskBar){
        if(showReservations == null || showReservations.isClosed()) {

            showReservations = new ShowReservations();

            desktop.add(showReservations);

            showReservations.setVisible(true);
            showReservations.toFront();
            addWindowButton(showReservations,taskBar);
        }else {
            showReservations.toFront();
        }
        return showReservations;
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
