package Functions;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.Scanner;

public class CreateReservationDate {
    Scanner sc = new Scanner(System.in);

    public void mainCreateResDate() {
        LocalDate minimumResDate = LocalDate.now();
        LocalDate userResDate = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu").withResolverStyle(ResolverStyle.STRICT);
        System.out.println("Insira uma data após: " + minimumResDate.format(formatter));
        System.out.println("Com formato dd/MM/aaaa");

        try {
            String input = sc.next();
            while (userResDate.isBefore(minimumResDate)) {
                System.out.println("Data inválida! Insira uma data após ou igual à: "
                        + minimumResDate.format(formatter));
                sc.next();
            }


        } catch (DateTimeException e) {
            return;
        };
    }
}
