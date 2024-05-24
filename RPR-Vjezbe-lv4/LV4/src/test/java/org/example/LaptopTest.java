package org.example;

import junit.framework.TestCase;

public class LaptopTest extends TestCase {

    public void testGetBrend() {
        Laptop laptop1=new Laptop("HP","303",2100,16,550,150,"Intel core i7-7700","Nvidia GeForce RTX 3060",70);
        assertEquals("HP",laptop1.getBrend());
    }

    public void testGetModel() {
        Laptop laptop1=new Laptop("HP","303",2100,16,550,150,"Intel core i7-7700","Nvidia GeForce RTX 3060",70);
        assertEquals("303",laptop1.getModel());
    }

    public void testGetCijena() {
    }

    public void testGetRam() {

    }

    public void testGetHdd() {
    }

    public void testGetSsd() {
    }

    public void testGetProcesor() {
    }

    public void testGetGrafickaKartica() {
    }

    public void testGetVelicinaEkrana() {
    }

    public void testSetBrend() {
        String brend;
        brend="HP";
        assertEquals("HP",brend);
    }

    public void testSetModel() {
    }

    public void testSetCijena() {
    }

    public void testSetRam() {

    }

    public void testSetHdd() {
    }

    public void testSetSsd() {
    }

    public void testSetProcesor() {
    }

    public void testSetGrafickaKartica() {
    }

    public void testSetVelicinaEkrana() {
    }

}