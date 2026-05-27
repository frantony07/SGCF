package Controller.Record;

import org.ONE.model.entity.ENUM.CountryCostumer;
import org.ONE.model.entity.ENUM.Language;

import java.util.ArrayList;

public record ClienteDTO(ArrayList<Language> languageSpeak,
                         CountryCostumer countryOfCostumer,
                         String cnpj,
                         String cpf,
                         String name) {
}