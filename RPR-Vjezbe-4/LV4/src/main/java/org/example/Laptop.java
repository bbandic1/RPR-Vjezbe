package org.example;
import java.io.Serializable;
// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Laptop implements Serializable{

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

    public String getBrend(){
        return this.brend;
    }
    public String getModel(){
        return this.model;
    }
    public double getCijena(){
        return this.cijena;
    }
    public int getRam(){
        return this.ram;
    }
    public int getHdd(){
        return this.hdd;
    }
    public int getSsd(){
        return this.ssd;
    }
    public String getProcesor(){
        return this.procesor;
    }
    public String getGrafickaKartica(){
        return this.grafickaKartica;
    }
    public double getVelicinaEkrana(){
        return this.velicinaEkrana;
    }


    public void setBrend(String brend){
        this.brend=brend;
    }
    public void setModel(String model){
        this.model=model;
    }
    public void setCijena(double cijena){
        this.cijena=cijena;
    }
    public void setRam(int ram){
        this.ram=ram;
    }
    public void setHdd(int hdd){
        this.hdd=hdd;
    }
    public void setSsd(int ssd){
        this.ssd=ssd;
    }
    public void setProcesor(String procesor){
        this.procesor=procesor;
    }
    public void setGrafickaKartica(String grafickaKartica){
        this.grafickaKartica=grafickaKartica;
    }
    public void setVelicinaEkrana(double velicinaEkrana){
        this.velicinaEkrana=velicinaEkrana;
    }
}