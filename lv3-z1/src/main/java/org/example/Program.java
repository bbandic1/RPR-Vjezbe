package org.example;
import java.util.HashMap;
import java.util.Scanner;

public class Program {
    public static void main(String[] arg) throws Pogresno {
        FiksniBroj.Grad grad= FiksniBroj.Grad.valueOf("SARAJEVO");
        String broj= "123-456";
        TelefonskiBroj tel = new FiksniBroj(grad,broj);
        String rjesenje = tel.ispisi();
        System.out.println("\n" + rjesenje);
        int MobilnaMreza=60;
        TelefonskiBroj mob = new MobilniBroj(MobilnaMreza,broj);
        System.out.println("\n" + mob.ispisi());
        String drzava="+387";
        TelefonskiBroj medunarodnibroj=new MedunarodniBroj(drzava,broj);
        System.out.println("\n" + medunarodnibroj.ispisi());
        Imenik imenik = new Imenik();
        String ime="Benjamin";
        tel=new FiksniBroj(grad,"333-333");
        imenik.dodaj(ime,tel);
        ime="Anes";
        tel=new FiksniBroj(grad,"111-111");
        imenik.dodaj(ime,tel);
        ime="Muhamed";
        tel=new FiksniBroj(grad,"222-222");
        imenik.dodaj(ime,tel);
        ime="Abu";
        tel=new FiksniBroj(grad,"555-555");
        imenik.dodaj(ime,tel);
        System.out.println("\n" + imenik.dajIme(tel));
        System.out.println("\n" + imenik.dajBroj("Benjamin"));
        System.out.println("\n" + imenik.naSlovo('B'));
        System.out.println("\n" + imenik.naSlovo('A'));
        System.out.println("\n" + imenik.izGrada(grad));
        System.out.println("\n" + imenik.izGradaBrojevi(grad));
        try{
            ime="Vasilije";
            if(ime.equals("Vasilije"))
                throw new Pogresno("Ne može to ime!");
        }catch(Pogresno poruka)
        {
            System.out.println("Izuzetak: " + poruka);
        }
    }
}
