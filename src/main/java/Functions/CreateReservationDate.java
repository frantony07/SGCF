package Functions;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.Scanner;

public class CreateReservationDate {
    Scanner sc = new Scanner(System.in);

    public LocalDate mainCreateResDate() {
        LocalDate minimumResDate = LocalDate.now();
        LocalDate userResDate = null;
        DateTimeFormatter formatter = DateTimeFormatter
                .ofPattern("dd/MM/uuuu")
                .withResolverStyle(ResolverStyle.STRICT);
        System.out.println("Insira uma data após: " + minimumResDate.format(formatter));
        System.out.println("Com formato dd/MM/aaaa");

        while (userResDate == null) {
            try {
                String input = sc.next();
                userResDate = LocalDate.parse(input, formatter);

                if (!userResDate.isAfter(minimumResDate)) {
                    System.out.println("Data inválida, insira uma data após a data de hoje: "
                            + minimumResDate.format(formatter));
                }
            } catch (DateTimeException e) {
                System.out.println(
                        "Formato de data inválido! Por favor, insira uma data no formato dd/MM/aaaa"
                );
            }
        }
        return userResDate;
    }
}
