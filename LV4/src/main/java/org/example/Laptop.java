package org.example;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public abstract class Laptop implements LaptopDao{

    private String brend;
    private String model;
    private double cijena;
    private int ram;
    private int hdd;
    private int ssd;
    private String procesor;
    private String grafickaKartica;
    private double velicinaEkrana;

    public Laptop(){
        this.brend="HP";
        this.model="303";
        this.cijena=2100;
        this.ram=16;
        this.hdd=550;
        this.ssd=150;
        this.procesor="Intel core i7-7700";
        this.grafickaKartica="Nvidia GeForce 1080Ti";
        this.velicinaEkrana=70;
    }

    public Laptop(String brend, String model, double cijena, int ram, int hdd, int ssd, String procesor, String grafickaKartica, double velicinaEkrana)
    {
        this.brend=brend;
        this.model=model;
        this.cijena=cijena;
        this.ram=ram;
        this.hdd=hdd;
        this.ssd=ssd;
        this.procesor=procesor;
        this.grafickaKartica=grafickaKartica;
        this.velicinaEkrana=velicinaEkrana;
    }
    

}