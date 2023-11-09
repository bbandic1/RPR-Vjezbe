package org.example;
import java.io.File;
import java.util.ArrayList;

public class Program {
    public static void main(String[] args)
    {
        Laptop laptop1=new Laptop("HP","303",2100,16,550,150,"Intel core i7-7700","Nvidia GeForce RTX 3060",70);
        System.out.println(laptop1.getProcesor());
        System.out.println(laptop1.getBrend());
        laptop1.setRam(32);
        System.out.println(laptop1.getRam());

        Laptop laptop2=new Laptop("ASUS","313",5000,32,980,220,"Intel core i7-10700k","Nvidia GeForce RTX 4090",70);

        Laptop laptop3=new Laptop("DELL","354",2800,16,600,145,"Intel core i7-8700k","Nvidia GeForce 1080Ti",70);

        String filePath = "E:/MyDownloads/ETFRI/RPR/Dokument.txt";
        File file=new File(filePath);
        LaptopDaoSerializableFile lap=new LaptopDaoSerializableFile(file);

        lap.dodajLaptopUListu(laptop1);
        lap.dodajLaptopUListu(laptop2);
        lap.dodajLaptopUListu(laptop3);

        System.out.println("Laptopi u file-u:");
        ArrayList<Laptop> laptopi = lap.vratiPodatkeIzDatoteke();
        for (Laptop l : laptopi) {
            System.out.println(l.getProcesor());
        }
    }
}
