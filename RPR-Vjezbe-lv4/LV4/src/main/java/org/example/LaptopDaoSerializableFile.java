package org.example;
import java.io.*;
import java.io.File;
import java.util.ArrayList;

public class LaptopDaoSerializableFile implements LaptopDao {
    private File file;
    private ArrayList<Laptop> laptopi;

    public LaptopDaoSerializableFile(File file){
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
        ArrayList<Laptop> nlaptopi = new ArrayList<>(); // lista za spasavanje procitanih elemenata

        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file))) {
            while (true) {
                try {
                    Object obj = inputStream.readObject(); // obj može biti tipa Laptop ili ArrayList<Laptop>
                    if (obj instanceof Laptop) { // ako je objekat tipa Laptop
                        Laptop l = (Laptop) obj;
                        nlaptopi.add(l); // ubaci ga u listu
                    }
                    else { // ako nije
                        for(Laptop lap: (ArrayList<Laptop>) obj) //ispisi sve elemente iz liste
                        nlaptopi.add(lap); // te dodaj u jedan po jedan laptop u listu
                    }
                } catch (EOFException e) {
                    break;
                }
            }
        } catch (IOException | ClassNotFoundException except) {
            except.printStackTrace();
        }
        return nlaptopi; // vraća se u orginalni laptopi niz
    }

    @Override
    public void dodajLaptopUFile(Laptop laptop){
        laptopi = vratiPodatkeIzDatoteke(); // uzmi podatke iz datoteke
        laptopi.add(laptop); // dodaj novi laptop na vec procitanu listu

        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file));
            outputStream.writeObject(laptopi); // zapiši cijelu listu
            outputStream.close();
        } catch (IOException except) {
            except.printStackTrace();
        }
    }
}