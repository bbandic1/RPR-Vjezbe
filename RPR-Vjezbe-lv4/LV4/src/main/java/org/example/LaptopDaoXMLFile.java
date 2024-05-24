package org.example;
import java.io.*;
import java.util.ArrayList;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class LaptopDaoXMLFile implements LaptopDao{
    private File file;
    private ArrayList<Laptop> laptopi;

    public LaptopDaoXMLFile (File file){
        this.file = file;
        this.laptopi = new ArrayList<>();
    }
    @Override
    public void dodajLaptopUListu(Laptop laptop){
        this.laptopi = vratiPodatkeIzDatoteke(); // uzmi podatke iz datoteke
        if (!laptopi.contains(laptop)) {
            laptopi.add(laptop); // dodaj laptop ako ne postoji već u listi
            dodajLaptopUFile(laptop); // dodaj laptop u file
        }
    }

    @Override
    public Laptop getLaptop(String procesor){
        this.laptopi = vratiPodatkeIzDatoteke();// uzmi podatke iz datoteke
        for(Laptop laptop: laptopi){
            if(laptop.getProcesor().equals(procesor)){ // ako se procesori poklapaju, nasli smo trazeni laptop
                return laptop;
            }
        }
        return null;
    }

    @Override
    public void napuniListu(ArrayList<Laptop> laptopi) {
        this.laptopi = laptopi;
    }

    @Override
    public ArrayList<Laptop> vratiPodatkeIzDatoteke() {
        ArrayList<Laptop> nlaptopi = new ArrayList<>();

        try {
            XmlMapper xmlMapper = new XmlMapper();
            if (file.exists() && file.length() > 0) {
                nlaptopi = xmlMapper.readValue(file, ArrayList.class);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return nlaptopi;
    }

    @Override
    public void dodajLaptopUFile(Laptop laptop) {
        try {
            ArrayList<Laptop> laptopi = vratiPodatkeIzDatoteke();
            laptopi.add(laptop);
            XmlMapper xmlMapper = new XmlMapper();
            xmlMapper.writeValue(file, laptopi);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
