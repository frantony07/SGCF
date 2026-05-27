package Controller.Record;

import org.ONE.model.entity.ENUM.Language;

import java.util.List;

public record FuncionarioDTO(String cpf, String name, List<Language> languagesSpoken) {

}
