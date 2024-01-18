package com.example.vjezba10_11;

import javafx.beans.Observable;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
class GeografijaDAOTest {
    private GeografijaDAO model = GeografijaDAO.getInstance();
    private boolean customEqual(Drzava d1, Drzava d2) {
        return (d1.getId() == d2.getId() && d1.getNaziv().equals(d2.getNaziv()));
    }
    @Test
    void setTrenutniGrad() {
        Drzava d1 = new Drzava(new SimpleIntegerProperty(1), new SimpleStringProperty("BiH"), null);
        Grad g1 = new Grad(new SimpleIntegerProperty(1),
                new SimpleStringProperty("Sarajevo"), new SimpleIntegerProperty(123), d1);

        GeografijaDAO.setTrenutniGrad(g1);

        Grad expected = g1;
        Grad actual = GeografijaDAO.getTrenutniGrad();

        assertEquals(expected, actual);
    }

    @Test
    void obrisiGrad() throws SQLException {
        model.obrisiGrad();
        ObservableList<Grad> gradovi = model.getGradovi();
        assertEquals(0, gradovi.size());
    }
    @Test
    void popuniTabeluGradAndGetGradovi() throws SQLException {
        try {
            model.popuniTabeluGrad();
        } catch(SQLException e) {
            System.out.println("Doslo je do greske prilikom popunjavanja tabele Grad!");
        }
        finally {
            String[] actual = {"Pariz", "London", "Beč", "Manchester", "Graz"};
            ObservableList<Grad> gradovi = model.getGradovi();
            for(int i=0; i<gradovi.size(); i++) {
                assertEquals(actual[i], gradovi.get(i).getNaziv());
            }
        }
    }
    @Test
    void vratiSveUBazi() {
        ByteArrayOutputStream mojOutput = new ByteArrayOutputStream();
        PrintStream mojStream = new PrintStream(mojOutput);

        System.setOut(mojStream);
        model.vratiSveUBazi();
        // reset output stream (izlaznog toka)
        System.setOut(System.out);
        // osigurava da se upise u odrediste sve ono sto je u buffer-u(tj da ne ostane u buffer-u)
        mojStream.flush();

        String expected = "Ispis svih Drzava:\n" +
                "ID: 1,  Naziv: Francuska,  GlavniGrad: 1\n" +
                "ID: 2,  Naziv: V. Britanija,  GlavniGrad: 2\n" +
                "ID: 3,  Naziv: Austrija,  GlavniGrad: 3\n" +
                "Ispis svih Gradova:\n" +
                "ID: 1,  Naziv: Pariz,  BrojStanovnika: 1658745,  Drzava: 1\n" +
                "ID: 2,  Naziv: London,  BrojStanovnika: 7641498,  Drzava: 2\n" +
                "ID: 3,  Naziv: Beč,  BrojStanovnika: 48744848,  Drzava: 3\n" +
                "ID: 4,  Naziv: Manchester,  BrojStanovnika: 4987497,  Drzava: 2\n" +
                "ID: 5,  Naziv: Graz,  BrojStanovnika: 4198414,  Drzava: 3";
        String actual = mojOutput.toString().trim();

        assertEquals(expected.trim(), actual);
    }
    @Test
    void getInstance() {
        assertTrue(model.daLiImaRedova());
    }
    @Test
    void nadjiDrzavu() {
        Drzava expected = new Drzava(new SimpleIntegerProperty(1), new SimpleStringProperty("Francuska"),
                model.glavniGrad("Francuska"));
        Drzava actual = model.nadjiDrzavu("Francuska");
        assertTrue(customEqual(expected, actual));
    }

    @Test
    void testObrisiGrad() {
//        assertThrows(NullPointerException.class, () -> {
//            model.obrisiGrad(new Grad(new SimpleIntegerProperty(1), new SimpleStringProperty("Ne postoji"),
//                    new SimpleIntegerProperty(123), null));
//        });
        assertDoesNotThrow(() -> {
            model.obrisiGrad(new Grad(new SimpleIntegerProperty(1), new SimpleStringProperty("Ne postoji"),
                    new SimpleIntegerProperty(123), null));
        });
    }
}