package org.example;
import java.util.ArrayList;
public class KolekcijaImena implements Informacije{

    private String ime;
    private String prezime;

    KolekcijaImena(String ime, String prezime)
    {
        this.ime=ime;
        this.prezime=prezime;
    }

    public KolekcijaImena() {
    }

    public String getIme()
    {
        return this.ime;
    }
    public String gePrezime()
    {
        return this.prezime;
    }

    public ArrayList<KolekcijaImena> kolekcijaImenas;
    public String getNajduzeIme()
    {
        String PIme= kolekcijaImenas.get(0).getIme();
        for(int i=0;i<kolekcijaImenas.size();i++)
        {
            if(PIme.length()<kolekcijaImenas.get(i).getIme().length())
                PIme=kolekcijaImenas.get(i).getIme();
        }
        return PIme;
    }
    @Override
    public String predstavi() {
        return this.ime+" "+this.prezime;
    }
}
