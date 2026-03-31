package org.ONE;

import Functions.CreateDate;

import java.time.LocalDate;

public class TesteString {

    public static void main(String[] args) {

        CreateDate dateCreator = new CreateDate();
        LocalDate date = dateCreator.mainCreateResDate();

    }
}

