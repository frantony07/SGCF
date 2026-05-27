package Controller.Record;

import org.ONE.model.entity.Cliente;
import org.ONE.model.entity.ENUM.Status;

public record PayModelDTO(Status status, Cliente cliente) {
}
