package org.example;
import java.io.IOException;
import java.util.*;

public interface LaptopDao {
    public void dodajLaptopUListu(Laptop laptop) throws IOException;
    public void dodajLaptopUFile(Laptop laptop);
    public Laptop getLaptop(String procesor);
    public void napuniListu(ArrayList<Laptop> laptopi);
    public ArrayList<Laptop> vratiPodatkeIzDatoteke();
}
