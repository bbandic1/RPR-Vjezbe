package org.example;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.*;
import java.util.ArrayList;

public class LaptopDaoJSONFile implements LaptopDao{
    private File file;
    private ArrayList<Laptop> laptopi;

    public LaptopDaoJSONFile (File file){
        this.file = file;
        this.laptopi = new ArrayList<>();
    }
    @Override
    public void dodajLaptopUListu(Laptop laptop) throws IOException {
        this.laptopi = vratiPodatkeIzDatoteke(); // uzmi podatke iz datoteke
        if (!laptopi.contains(laptop)) {
            laptopi.add(laptop); // dodaj laptop ako ne postoji već u listi
            dodajLaptopUFile(laptop); // dodaj laptop u file
        }
    }

    @Override
    public Laptop getLaptop(String procesor) throws IOException {
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
    public ArrayList<Laptop> vratiPodatkeIzDatoteke() throws IOException {
        ArrayList<Laptop> nlaptopi = new ArrayList<>();

        ObjectMapper mapper = new ObjectMapper();
        if (file.exists() && file.length() > 0) {
            nlaptopi = mapper.readValue(file, new TypeReference<ArrayList<Laptop>>() {});
        }
        return nlaptopi;
    }

    @Override
    public void dodajLaptopUFile(Laptop laptop) {
        try {
            ArrayList<Laptop> laptopi = vratiPodatkeIzDatoteke();
            laptopi.add(laptop);
            zapisiPodatkeUFile(laptopi);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void zapisiPodatkeUFile(ArrayList<Laptop> laptopi) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(file, laptopi);
    }
}
