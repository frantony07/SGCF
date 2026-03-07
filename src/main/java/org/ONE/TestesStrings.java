package org.ONE;

import Tour.CountryTour;
import Tour.Passeio;

import java.sql.Date;

public class TestesStrings {

    public static void main(String[] args) {
        Passeio passeioOk = new Passeio(
                250.0,
                120,
                CountryTour.Brasil,
                "5km",
                Date.valueOf("2026-03-10"),
                "Passeio no Centro Historico",
                "Curitiba - PR"
        );

        Passeio passeioComFalha = new Passeio(
                180.0,
                90,
                CountryTour.Argentina,
                " ",
                Date.valueOf("2026-03-12"),
                null,
                "Buenos Aires"
        );

        validarStrings("passeioOk", passeioOk);
        validarStrings("passeioComFalha", passeioComFalha);
    }

    private static void validarStrings(String nomeTeste, Passeio passeio) {
        System.out.println("\nTeste: " + nomeTeste);
        validarCampo("walk", passeio.getWalk());
        validarCampo("nameOfTour", passeio.getNameOfTour());
        validarCampo("locations", passeio.getLocations());
    }

    private static void validarCampo(String campo, String valor) {
        boolean definido = valor != null && !valor.isBlank();
        System.out.println(campo + " definido? " + definido + " | valor: " + valor);
    }
}
