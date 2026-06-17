package View;

import Controller.Impl.ReservationControllerImpl;
import Controller.ReservationController;
import View.RegisterViews.CreateNewCustomer;
import View.RegisterViews.CreateNewEmployee;
import View.RegisterViews.CreateNewTour;
import View.RegisterViews.CreateNewUser;
import View.RegisterViews.ScheduleReservationWindow;

import javax.swing.*;

import static View.ItensDefault.addWindowButton;


public class FunctionsOfWindowsRegister {
    public FunctionsOfWindowsRegister() {
    }

    public static CreateNewEmployee  CreatedNewEmployee(CreateNewEmployee createNewEmployee, JDesktopPane desktop, JPanel taskBar){
        if (createNewEmployee == null || createNewEmployee.isClosed()){

            createNewEmployee = new CreateNewEmployee();
            createNewEmployee.setVisible(true);

            desktop.add(createNewEmployee);

            createNewEmployee.toFront();

            addWindowButton(createNewEmployee,taskBar);

        }else {

            createNewEmployee.toFront();
        }
        return createNewEmployee;
    }

    public static CreateNewCustomer CreateNewCustomer(CreateNewCustomer createNewCustomer,JDesktopPane desktop,JPanel taskBar){
        if (createNewCustomer == null || createNewCustomer.isClosed()){

            createNewCustomer = new CreateNewCustomer();
            createNewCustomer.setVisible(true);

            desktop.add(createNewCustomer);

            createNewCustomer.toFront();

            addWindowButton(createNewCustomer,taskBar);

        }else {

            createNewCustomer.toFront();
        }
        return createNewCustomer;
    }
    public static CreateNewTour CreateNewTour(CreateNewTour createNewTour,JDesktopPane desktop,JPanel taskBar){
        if (createNewTour == null || createNewTour.isClosed()){

            createNewTour= new CreateNewTour();
            createNewTour.setVisible(true);

            desktop.add(createNewTour);

            createNewTour.toFront();

            addWindowButton(createNewTour,taskBar);

        }else {

            createNewTour.toFront();
        }
        return createNewTour;
    }

    public static CreateNewUser CreateNewUser(CreateNewUser createNewUser,JDesktopPane desktop,JPanel taskBar){
        if (createNewUser == null || createNewUser.isClosed()){

            createNewUser = new CreateNewUser();
            createNewUser.setVisible(true);

            desktop.add(createNewUser);

            createNewUser.toFront();

            addWindowButton(createNewUser,taskBar);

        }else {

            createNewUser.toFront();
        }
        return createNewUser;
    }

    public static ScheduleReservationWindow scheduleReservation(ScheduleReservationWindow win, JDesktopPane desktop, JPanel taskBar) {
        if (win == null || win.isClosed()) {

            ReservationController controller = new ReservationControllerImpl();

            win = new ScheduleReservationWindow(controller);
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
