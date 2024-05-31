package com.example.vjezba;

import javafx.beans.property.SimpleStringProperty;

public class Korisnik {

    public Korisnik() {
        Ime=new SimpleStringProperty("");
        Prezime=new SimpleStringProperty("");
        E_mail=new SimpleStringProperty("");
        korisnicko_ime=new SimpleStringProperty("");
        lozinka=new SimpleStringProperty("");
    }

    public Korisnik(String ime, String prezime, String e_mail, String korisnicko_ime, String lozinka) {
        Ime = new SimpleStringProperty(ime);
        Prezime = new SimpleStringProperty(prezime);
        E_mail = new SimpleStringProperty(e_mail);
        this.korisnicko_ime = new SimpleStringProperty(korisnicko_ime);
        this.lozinka = new SimpleStringProperty(lozinka);
    }

    public String getIme() {
        return Ime.get();
    }

    public SimpleStringProperty imeProperty() {
        return Ime;
    }

    public void setIme(String ime) {
        this.Ime.set(ime);
    }

    public String getPrezime() {
        return Prezime.get();
    }

    public SimpleStringProperty prezimeProperty() {
        return Prezime;
    }

    public void setPrezime(String prezime) {
        this.Prezime.set(prezime);
    }

    public String getE_mail() {
        return E_mail.get();
    }

    public SimpleStringProperty e_mailProperty() {
        return E_mail;
    }

    public void setE_mail(String e_mail) {
        this.E_mail.set(e_mail);
    }

    public String getKorisnicko_ime() {
        return korisnicko_ime.get();
    }

    public SimpleStringProperty korisnicko_imeProperty() {
        return korisnicko_ime;
    }

    public void setKorisnicko_ime(String korisnicko_ime) {
        this.korisnicko_ime.set(korisnicko_ime);
    }

    public String getLozinka() {
        return lozinka.get();
    }

    public SimpleStringProperty lozinkaProperty() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka.set(lozinka);
    }

    private SimpleStringProperty Ime, Prezime, E_mail, korisnicko_ime, lozinka;

    @Override
    public String toString()
    {
        if(getKorisnicko_ime().isEmpty())
            return "";
        return getKorisnicko_ime();
    }

}
