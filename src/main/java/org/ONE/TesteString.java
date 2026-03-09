package org.ONE;

import Tour.Passeio;
import Tour.CountryTour;
import java.sql.Date;
public class TesteString {

    public static void main(String[] args) {

        Passeio p = new Passeio(
                150,
                120,
                CountryTour.Brasil,
                "5",
                Date.valueOf("2026-03-07"),
                "Cataratas Tour",
                "Foz do Iguacu",
                1
        );


        System.out.println("Nome existe? " + (p.getNameOfTour() != null));
        System.out.println("Local exite? " + (p.getLocations() != null));
        System.out.println("Walk existe? " + (p.getWalk() != null));
        System.out.println("Country existe? " + (p.getCountryTour()!= null));




    }
}

