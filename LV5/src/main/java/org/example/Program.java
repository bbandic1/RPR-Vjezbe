package org.example;
import java.util.ArrayList;
import java.util.List;

public class Program {
    public static void main(String[] args){

        LicneInformacije licneInformacije = new LicneInformacije("Benjamin", "Bandić");
        InformacijeOStudentu informacijeOStudentu = new InformacijeOStudentu("2", "13333", "Benjamin", "Bandić");
        InformacijeONastavniku informacijeONastavniku = new InformacijeONastavniku("Kenan", "Osmanović", "Ph.D.");
        Predmet predmet = new Predmet("Kvantni Kompjuting", "Upoznavanje sa Kvantnom mehanikom i njenom vezom u projektovanju računara, Qbits. Quantum Gates etc");

        ArrayList<Informacije> listaInformacija = new ArrayList<>();
        listaInformacija.add(licneInformacije);
        listaInformacija.add(informacijeOStudentu);
        listaInformacija.add(informacijeONastavniku);
        listaInformacija.add(predmet);

        Ocjena ocjena1 = new Ocjena(informacijeOStudentu,10);
        Ocjena ocjena2 = new Ocjena(informacijeONastavniku,9);
        Ocjena ocjena3 = new Ocjena(informacijeOStudentu,8);

        ArrayList<Ocjena> ocjene = new ArrayList<>();
        ocjene.add(ocjena1);
        ocjene.add(ocjena2);
        ocjene.add(ocjena3);

        for(Ocjena a : ocjene){
            if(a instanceof MozeOcijeniti){
                informacijeONastavniku.setOcjene(a);
                predmet.setOcjene(a);
            }
            else{
                predmet.setOcjene(a);
            }
        }
        KolekcijaPoruka kolekcijaPoruka = new KolekcijaPoruka(listaInformacija);
        List<Informacije> porukeIzKolekcije = kolekcijaPoruka.getPoruke();

        for (Informacije poruka : porukeIzKolekcije) {
            System.out.println(poruka.predstavi());
        }
            ArrayList<String> listaImena = new ArrayList<>();
            listaImena.add("Muhammad Ali");
            listaImena.add("Cristiano Ronaldo");
            listaImena.add("Michael Jordan");

            KolekcijaImena staraKolekcija = new KolekcijaImena(listaImena);

            Pobjednik pobjednikStara = new Pobjednik(staraKolekcija);
            System.out.println("Staro: " + pobjednikStara.getIme() + " " + pobjednikStara.getPrezime());

            // Primjer s novom KolekcijaImenaIPrezimena
            ArrayList<String> listaImenaNovo = new ArrayList<>();
            ArrayList<String> listaPrezimenaNovo = new ArrayList<>();
            listaImenaNovo.add("Benjamin");
            listaPrezimenaNovo.add("Bandić");
            listaImenaNovo.add("Son");
            listaPrezimenaNovo.add("Goku");

            KolekcijaImenaIPrezimena novaKolekcija = new KolekcijaImenaIPrezimena(listaImenaNovo, listaPrezimenaNovo);


            Pobjednik pobjednikNova = new Pobjednik(novaKolekcija);
            System.out.println("Novo: " + pobjednikNova.getIme() + " " + pobjednikNova.getPrezime());


    }

}
