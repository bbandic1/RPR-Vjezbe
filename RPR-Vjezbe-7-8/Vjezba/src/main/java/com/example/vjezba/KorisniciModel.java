package com.example.vjezba;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValueBase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class KorisniciModel {
    private ObservableList<Korisnik> korisnici= FXCollections.observableArrayList();
    private SimpleObjectProperty<Korisnik> trenutni_Korisnik = new SimpleObjectProperty<>();

    public void napuni()
    {
        korisnici.add(new Korisnik("","","","",""));
        korisnici.add(new Korisnik("Benjamin","Bandić","bbandic1@etf.unsa,ba","bbandic1","topsvemajstorski"));
        trenutni_Korisnik.set(korisnici.get(0));
    }

    public ObservableList<Korisnik> getKorisnici() {
        return korisnici;
    }

    public void setKorisnici(ObservableList<Korisnik> korisnici) {
        this.korisnici = korisnici;
    }

    public Korisnik getTrenutni_Korisnik() {
        return trenutni_Korisnik.get();
    }

    public SimpleObjectProperty<Korisnik> trenutni_KorisnikProperty() {
        return trenutni_Korisnik;
    }

    public void setTrenutni_Korisnik(Korisnik trenutni_Korisnik) {
        this.trenutni_Korisnik.set(trenutni_Korisnik);
    }
}
