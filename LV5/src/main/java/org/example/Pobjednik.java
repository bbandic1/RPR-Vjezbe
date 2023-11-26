package org.example;

import java.util.ArrayList;

public class Pobjednik {

    private String ime;
    private String prezime;
    private int brojZnakova;
    private Kolekcija kolekcijaImena;




    public Pobjednik(Kolekcija kolekcijaImena) {
        this.kolekcijaImena = kolekcijaImena;
        String[] razdvojeno = kolekcijaImena.getNajduzeIme().split(" ");
        this.ime = razdvojeno[0];
        this.prezime = razdvojeno[1];
        this.brojZnakova=ime.length();
    }


    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public int getBrojZnakova() {
        return brojZnakova;
    }

    public Kolekcija getKolekcijaImena() {
        return kolekcijaImena;
    }

    public void setKolekcijaImena(KolekcijaImena kolekcijaImena) {
        this.kolekcijaImena = kolekcijaImena;
    }
}
