package org.example;
import java.util.ArrayList;
import java.util.ArrayList;

public class KolekcijaImena implements  Kolekcija{

    private ArrayList<String> ImenaPrezimena;

    public KolekcijaImena(){
        this.ImenaPrezimena=new ArrayList<>();
    }

    public KolekcijaImena(ArrayList<String> a){
        this.ImenaPrezimena=a;
    }

    public void dodajImePrezime(String imePrezime){
        ImenaPrezimena.add(imePrezime);
    }
    @Override
    public String getNajduzeIme(){
        if(ImenaPrezimena.isEmpty()){
            return null;
        }
        String longest = ImenaPrezimena.get(0);
        for(String ime : ImenaPrezimena){
            if(ime.length()>longest.length()){
                longest = ime;
            }
        }
        return longest;
    }
}
