package Functions;

import java.time.LocalDate;
import java.util.Scanner;

public class functionOfData {
    Scanner sc = new Scanner(System.in);
    public LocalDate writeData() {
        LocalDate data;
        try {
            System.out.println("diguite a data da reservação com o seguinte formato (2023-12-25)");
            String dataInString = sc.toString();
            data = LocalDate.parse(dataInString);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return data;
    }

}
