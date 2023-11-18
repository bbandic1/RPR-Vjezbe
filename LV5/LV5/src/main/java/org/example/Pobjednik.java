package org.example;

import java.util.ArrayList;

public class Pobjednik extends KolekcijaImena implements Informacije{

    private String ime;
    private String prezime;
    int brojZnakova;

    KolekcijaImena ki;

    Pobjednik(KolekcijaImena ki)
    {
        this.ime=ki.getIme();
        this.prezime=ki.gePrezime();
        this.brojZnakova=this.ime.length();
    }
    @Override
    public String predstavi() {
        return null;
    }

}
