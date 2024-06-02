package org.example;

public class Grad {
    private int gradID;
    private String naziv;
    private int brojStanovnika;

    public int getGradID() {
        return gradID;
    }

    public void setGradID(int gradID) {
        this.gradID = gradID;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public int getBrojStanovnika() {
        return brojStanovnika;
    }

    public void setBrojStanovnika(int brojStanovnika) {
        this.brojStanovnika = brojStanovnika;
    }

    public Drzava getDrzavaID() {
        return drzavaID;
    }

    public void setDrzavaID(Drzava drzavaID) {
        this.drzavaID = drzavaID;
    }

    public Grad() {
    }

    public Grad(int gradID, String naziv, int brojStanovnika, Drzava drzavaID) {
        this.gradID = gradID;
        this.naziv = naziv;
        this.brojStanovnika = brojStanovnika;
        this.drzavaID = drzavaID;
    }

    private Drzava drzavaID;
}
