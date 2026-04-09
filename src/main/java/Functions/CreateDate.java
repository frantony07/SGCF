package Functions;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.Scanner;

public class CreateDate {
    Scanner sc = new Scanner(System.in);

    public LocalDate mainCreateResDate() {
        LocalDate minimumResDate = LocalDate.now();
        LocalDate userResDate = null;
        DateTimeFormatter formatter = DateTimeFormatter
                .ofPattern("dd/MM/uuuu")
                .withResolverStyle(ResolverStyle.STRICT);
        System.out.println("Insira uma data após: "
                + minimumResDate.format(formatter));
        System.out.println("Com formato dd/MM/aaaa");

        while (userResDate == null) {
            try {
                String input = sc.next();
                userResDate = LocalDate.parse(input, formatter);

                if (!userResDate.isAfter(minimumResDate)) {
                    System.out.println("Data inválida, insira uma data após a data de hoje: "
                            + minimumResDate.format(formatter));
                    userResDate = null;
                }
            } catch (DateTimeException e) {
                System.out.println(
                        "Formato de data inválido! Por favor, insira uma data no formato dd/MM/aaaa"
                );
            }
        }
        return userResDate;
    }
    public LocalDate createNewData(){
        try {
            Scanner sc = new Scanner(System.in);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu");
            while (true){
                System.out.println("Digite uma data no formato (dd/MM/aaa)");
                String dataString = sc.nextLine();
                LocalDate date = LocalDate.parse(dataString , formatter);
                if (date.isAfter(LocalDate.now())){
                    return date;
                }
                System.out.println("A data deve ser uma data válida");
            }
        } catch (Exception e) {
            PrintError.printErro(e);
        }
        return LocalDate.now();
    }
}
