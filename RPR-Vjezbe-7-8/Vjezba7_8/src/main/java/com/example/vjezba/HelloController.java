package com.example.vjezba;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

public class HelloController {
    public ListView listaKorisnika;
    public TextField fldIme;
    public TextField fldPrezime;
    public TextField fldE_mail;
    public TextField fldKorisnickoIme;
    public PasswordField fldLozinka;
    public Button btnDodaj;
    private KorisniciModel model=new KorisniciModel();

    public HelloController() {
        model.napuni();
    }
    @FXML
    public void initialize(){
        listaKorisnika.setItems(model.getKorisnici());
        model.trenutni_KorisnikProperty().addListener((obs,oldKorisnik, newKorisnik)->{
            if(oldKorisnik!=null) {
                fldIme.textProperty().unbindBidirectional(oldKorisnik.imeProperty());
                fldPrezime.textProperty().unbindBidirectional(oldKorisnik.prezimeProperty());
                fldE_mail.textProperty().unbindBidirectional(oldKorisnik.e_mailProperty());
                fldKorisnickoIme.textProperty().unbindBidirectional(oldKorisnik.korisnicko_imeProperty());
                fldLozinka.textProperty().unbindBidirectional(oldKorisnik.lozinkaProperty());
            }
            if(newKorisnik==null) {
                fldIme.setText("");
                fldPrezime.setText("");
                fldE_mail.setText("");
                fldKorisnickoIme.setText("");
                fldLozinka.setText("");
            }
            else {
                fldIme.textProperty().bindBidirectional(newKorisnik.imeProperty());
                fldPrezime.textProperty().bindBidirectional(newKorisnik.prezimeProperty());
                fldE_mail.textProperty().bindBidirectional(newKorisnik.e_mailProperty());
                fldKorisnickoIme.textProperty().bindBidirectional(newKorisnik.korisnicko_imeProperty());
                fldLozinka.textProperty().bindBidirectional(newKorisnik.lozinkaProperty());
            }
        });
    }
    @FXML
    public void dodajKorisnika() {
        Korisnik noviKorisnik = new Korisnik();
                noviKorisnik.setIme(fldIme.getText());
                noviKorisnik.setPrezime(fldPrezime.getText());
                noviKorisnik.setE_mail(fldE_mail.getText());
                noviKorisnik.setKorisnicko_ime(fldKorisnickoIme.getText());
                noviKorisnik.setLozinka(fldLozinka.getText());
        model.getKorisnici().add(noviKorisnik);
        model.setTrenutni_Korisnik(model.getKorisnici().get(0));
        model.getKorisnici().get(0).setIme("");
        model.getKorisnici().get(0).setPrezime("");
        model.getKorisnici().get(0).setE_mail("");
        model.getKorisnici().get(0).setKorisnicko_ime("");
        model.getKorisnici().get(0).setLozinka("");
        listaKorisnika.refresh();
    }


    public void klikMiša() {
        Korisnik IzabraniKorisnik = (Korisnik) listaKorisnika.getSelectionModel().getSelectedItem();
        if (IzabraniKorisnik != null) {
            model.trenutni_KorisnikProperty().set(IzabraniKorisnik);
            listaKorisnika.refresh();
        }
    }

    public void Izadji() {
        Platform.exit();
    }
}