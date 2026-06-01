package Controller.Record;

import org.ONE.model.entity.ENUM.CountryCostumer;
import org.ONE.model.entity.ENUM.Language;

import java.util.List;

public record ClienteDTO(
                         List<Language> languageSpeak,
                         CountryCostumer countryOfCostumer,
                         String cnpj,
                         String cpf,
                         String name) {

}