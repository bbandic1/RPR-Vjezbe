package org.example;
import java.util.*;

public interface LaptopDao {
    public void dodajLaptopUListu(Laptop laptop);
    public void dodajLaptopUFile(Laptop laptop);
    Laptop getLaptop(String procesor);
    void napuniListu(ArrayList<Laptop> laptopi);
    Laptop vratiPodatkeIzDatoteke();
}
