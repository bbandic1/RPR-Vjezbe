package org.example;

public class Drzava {
    private int drzavaID;
    private String naziv;

    public int getDrzavaID() {
        return drzavaID;
    }

    public void setDrzavaID(int drzavaID) {
        this.drzavaID = drzavaID;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Grad getGlavniGrad() {
        return glavniGrad;
    }

    public void setGlavniGrad(Grad glavniGrad) {
        this.glavniGrad = glavniGrad;
    }

    public Drzava() {
    }

    public Drzava(int drzavaID, String naziv, Grad glavniGrad) {
        this.drzavaID = drzavaID;
        this.naziv = naziv;
        this.glavniGrad = glavniGrad;
    }

    private Grad glavniGrad;

}
