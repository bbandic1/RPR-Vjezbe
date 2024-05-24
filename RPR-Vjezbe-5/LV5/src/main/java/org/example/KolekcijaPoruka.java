package org.example;

import org.example.Informacije;

import java.util.ArrayList;
import java.util.List;

public class KolekcijaPoruka implements Informacije {

    private ArrayList<Informacije> poruke;

    public KolekcijaPoruka(ArrayList<Informacije> poruke){
        this.poruke = poruke;
    }

    public ArrayList<Informacije> getPoruke() {
        return poruke;
    }

    @Override
    public String predstavi() {
        return null;
    }
}