package org.example;

import java.util.ArrayList;
import java.util.List;

public class Poruka {

    private String tekst;

    public Poruka(String tekst){
        this.tekst=tekst;
    }

    public String predstavi(){
        return tekst;
    }
}