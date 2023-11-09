package org.example;

import junit.framework.TestCase;

import java.util.ArrayList;

public class LaptopDaoSerializableFileTest extends TestCase {

    public void testDodajLaptopUListu() {
    }

    public void testGetLaptop() { //mocking
        Laptop laptop2=new Laptop("ASUS","313",5000,32,980,220,"Intel core i7-10700k","Nvidia GeForce RTX 4090",70);
        String procesor="Intel core i7-10700k";
            assertEquals(procesor, laptop2.getProcesor());
    }

    public void testNapuniListu() {
    }

    public void testVratiPodatkeIzDatoteke() {
    }

    public void testDodajLaptopUFile() {
    }
}