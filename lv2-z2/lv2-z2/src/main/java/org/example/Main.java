package org.example;
import java.util.List;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public class Osoba
    {
        private String ime;
        private String prezime;
        Osoba(String s1,String s2)
        {

        }
        @Override
        public String toString()
        {
            return this.ime;
        }

    }
    public class Uposlenik extends Osoba
    {
        Uposlenik(String s1,String s2)
        {
            super(s1,s2);
        }
    }
    public class Korisnik extends Osoba
    {
        Korisnik(String s1,String s2)
        {
            super(s1,s2);
        }
        void dodajRacun(Racun r)
        {

        }
    }

    public class Racun
    {
        Racun(Long l,Osoba o)
        {

        }
        boolean provjeriOdobrenjePrekoracenja(double d)
        {
            return false;
        }

        boolean IzvrsiUplatu(double d)
        {
            return false;
        }
        boolean IzvrsiIsplatu(double d)
        {
            return false;
        }
        void odobriPrekoracenje(double d)
        {

        }

    }
    public class Banka
    {
        private long brojRacuna;
        Banka()
        {

        }
        Korisnik kreirajNovogKorisnika(String s1,String s2)
        {
            Korisnik k = new Korisnik(s1, s2);
            return k;
        }
        Uposlenik kreirajNovogUposlenika(String s1,String s2)
        {
            Uposlenik u = new Uposlenik(s1,s2);
            return u;
        }
        Racun kreirajRacunZaKorisnika(Korisnik k)
        {
            Racun r=new Racun(1000L,k);
            return r;
        }
    }
    public static void main(String[] args) {
        // Press Alt+Enter with your caret at the highlighted text to see how
        // IntelliJ IDEA suggests fixing it.
        long brojRacuna;
        Osoba korisnikRacuna;
        boolean odobrenjePreokoracenja;
        double stanjeRacuna;
        Racun racun;
        List<Korisnik> korisnici;
        List<Uposlenik> uposlenici;

    }
}