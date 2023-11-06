package org.example;
import java.util.*;

public interface LaptopDao {
    public void dodajLaptopUListu(Laptop laptop);
    public void dodajLaptopUFile(Laptop laptop);
    public Laptop getLaptop(String procesor);
    public void napuniListu(ArrayList<Laptop> laptopi);
    public Laptop vratiPodatkeIzDatoteke();
}
