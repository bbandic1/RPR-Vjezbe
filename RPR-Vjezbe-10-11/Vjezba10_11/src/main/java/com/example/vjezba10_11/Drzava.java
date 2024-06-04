package com.example.vjezba10_11;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Drzava {
    private IntegerProperty id;
    private SimpleStringProperty naziv;
    private Grad glavniGrad;
    public Drzava(IntegerProperty id, SimpleStringProperty naziv, Grad glavniGrad) {
        this.id = id;
        this.naziv = naziv;
        this.glavniGrad = glavniGrad;
    }

    public Drzava() {
        id = new SimpleIntegerProperty();
        naziv = new SimpleStringProperty();
        glavniGrad = null;
    }
    @Override
    public String toString() {
        return this.getNaziv();
    }
    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getNaziv() {
        return naziv.get();
    }

    public SimpleStringProperty nazivProperty() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv.set(naziv);
    }

    public Grad getGlavniGrad() {
        return glavniGrad;
    }

    public void setGlavniGrad(Grad glavniGrad) {
        this.glavniGrad = glavniGrad;
    }
}
