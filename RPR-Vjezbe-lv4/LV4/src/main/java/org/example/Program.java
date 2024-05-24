package org.example;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Program {
    public static void main(String[] args) throws IOException {

        // Unos podataka i provjera Laptop klase

        Laptop laptop1=new Laptop("HP","303",2100,16,550,150,"Intel core i7-7700","Nvidia GeForce RTX 3060",70);

        System.out.println(laptop1.getProcesor());
        System.out.println(laptop1.getBrend());
        laptop1.setRam(32);
        System.out.println(laptop1.getRam());

        Laptop laptop2=new Laptop("ASUS","313",5000,32,980,220,"Intel core i7-10700k","Nvidia GeForce RTX 4090",70);

        Laptop laptop3=new Laptop("DELL","354",2800,16,600,145,"Intel core i7-8700k","Nvidia GeForce 1080Ti",70);



        //Binarna datoteka PRIMJER

        String filePath = "E:/MyDownloads/ETFRI/RPR/Dokument.txt";
        File file1=new File(filePath);

        try (FileWriter writer = new FileWriter(file1)) {
            writer.write(""); // Brisanje file-a
        } catch (IOException e) {
            e.printStackTrace();
        }

        LaptopDaoSerializableFile lap=new LaptopDaoSerializableFile(file1);

        lap.dodajLaptopUListu(laptop1);
        lap.dodajLaptopUListu(laptop2);
        lap.dodajLaptopUListu(laptop3);

        System.out.println("BINARNA DATOTEKA PRIMJER: \n");

        System.out.println("Laptopi u file-u:");
        ArrayList<Laptop> laptopi1 = lap.vratiPodatkeIzDatoteke();
        for (Laptop l : laptopi1) {
            System.out.println(l.getProcesor());
        }
        System.out.println("\n");

        try {
            if (laptopi1.isEmpty())
                throw  new Pogresno("Lista ne smije biti prazna");
        }
        catch(Pogresno poruka)
        {
            System.out.println("Izuzetak: " + poruka);
        }

        //Primjer oko getLaptop metode

        System.out.println("getLaptop metoda PRIMJER: \n");

        Laptop l4=lap.getLaptop("Intel core i7-10700k");
        System.out.println("Procesor: " + l4.getProcesor() + " Brend: " + l4.getBrend() + " Model: " + l4.getModel());
        System.out.println("\n");

        //XML PRIMJER

        filePath="E:/MyDownloads/ETFRI/RPR/Dokument.XML";
        File file2=new File(filePath);
        LaptopDaoXMLFile xml = new LaptopDaoXMLFile(file2);

        try (FileWriter writer = new FileWriter(file2)) {
            writer.write(""); // Brisanje file-a
        } catch (IOException e) {
            e.printStackTrace();
        }

        xml.dodajLaptopUListu(laptop1);
        xml.dodajLaptopUFile(laptop2);
        xml.dodajLaptopUFile(laptop3);

        System.out.println("XML PRIMJER: \n");

        System.out.println("Laptopi u file-u:");
        ArrayList<Laptop> laptopi2 = lap.vratiPodatkeIzDatoteke();
        for (Laptop l : laptopi2) {
            System.out.println(l.getProcesor());
        }
        System.out.println("\n");

        try {
            if (laptopi2.isEmpty())
                throw  new Pogresno("Lista ne smije biti prazna");
        }
        catch(Pogresno poruka)
        {
            System.out.println("Izuzetak: " + poruka);
        }

        //JSON PRIMJER

        filePath="E:/MyDownloads/ETFRI/RPR/Dokument.JSON";
        File file3=new File(filePath);
        LaptopDaoJSONFile json = new LaptopDaoJSONFile(file3);

        try (FileWriter writer = new FileWriter(file2)) {
            writer.write(""); // Brisanje file-a
        } catch (IOException e) {
            e.printStackTrace();
        }

        json.dodajLaptopUListu(laptop1);
        json.dodajLaptopUFile(laptop2);
        json.dodajLaptopUFile(laptop3);

        System.out.println("JSON PRIMJER: \n");

        System.out.println("Laptopi u file-u:");
        ArrayList<Laptop> laptopi3 = lap.vratiPodatkeIzDatoteke();
        for (Laptop l : laptopi3) {
            System.out.println(l.getProcesor());
        }

        try {
            if (laptopi3.isEmpty())
                throw  new Pogresno("Lista ne smije biti prazna");
        }
        catch(Pogresno poruka)
        {
            System.out.println("Izuzetak: " + poruka);
        }
    }
}
