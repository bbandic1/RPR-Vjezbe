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
        this.laptopi = vratiPodatkeIzDatoteke();
        for(Laptop laptop: laptopi){
            if(laptop.getProcesor().equals(procesor)){
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

        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file))) {
            while (true) {
                try {
                    Object obj = inputStream.readObject();
                    if (obj instanceof Laptop) {
                        // Cast the object to Laptop and then add it to the list
                        Laptop l = (Laptop) obj;
                        nlaptopi.add(l);
                    }
                } catch (EOFException e) {
                    break;
                }
            }
        } catch (IOException | ClassNotFoundException except) {
            except.printStackTrace();
        }
        return nlaptopi;
    }

    @Override
    public void dodajLaptopUFile(Laptop laptop){
        laptopi = vratiPodatkeIzDatoteke();
        laptopi.add(laptop); // Add the new laptop to the existing list

        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file));
            outputStream.writeObject(laptopi); // zapiši cijelu listu
            outputStream.close();
        } catch (IOException except) {
            except.printStackTrace();
        }
    }
}