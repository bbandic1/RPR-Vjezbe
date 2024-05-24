package org.example;
import java.lang.reflect.Array;
import java.util.ArrayList;
public class Predmet implements Informacije {
    private String naziv;
    private String opis;
    private ArrayList<Ocjena> ocjene;

    public Predmet(String naziv, String opis){
        this.opis=opis;
        this.naziv=naziv;
        this.ocjene=new ArrayList<>();
    }

    @Override
    public String predstavi(){
        return getNaziv() + " " + getOpis();
    }
    public String getNaziv(){
        return this.naziv;
    }
    public String getOpis(){
        return this.opis;
    }

    public void setNaziv(String naziv){
        this.naziv=naziv;
    }
    public void setOpis(String opis){
        this.opis=opis;
    }
    public void setOcjene(Ocjena ocjena) { ocjene.add(ocjena);}
}
