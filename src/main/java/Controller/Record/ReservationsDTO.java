package Controller.Record;

import org.ONE.model.entity.Cliente;
import org.ONE.model.entity.ENUM.Status;
import org.ONE.model.entity.Funcionario;
import org.ONE.model.entity.Passeio;

import java.time.LocalDate;

public record ReservationsDTO(Cliente cliente,
                              LocalDate date,
                              Funcionario funcionario,
                              Passeio passeio,
                              double value,
                              Status status) {
}
