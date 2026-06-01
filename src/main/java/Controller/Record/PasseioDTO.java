package Controller.Record;

import org.ONE.model.entity.ENUM.CountryTour;

public record PasseioDTO(
                         double price,
                         long durationOfTourInMinute,
                         CountryTour countryTour,
                         Long kmOftour,
                         String nameOfTour,
                         String locations) {

    }
