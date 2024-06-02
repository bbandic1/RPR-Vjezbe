package org.example;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {

    public static String ispisiGradove() throws SQLException, FileNotFoundException {
        GeografijaDAO dao = GeografijaDAO.getInstance();
        String result = "";
        for(Grad grad : dao.gradovi())
            result=result + grad.getNaziv() + " (" + grad.getDrzavaID().getNaziv() + ") - "
                    + grad.getBrojStanovnika() + "\n";
        return result;
    }
    public static void glavniGrad() throws SQLException, FileNotFoundException {
        GeografijaDAO dao=GeografijaDAO.getInstance();
        Scanner ulaz = new Scanner(System.in);
        System.out.println("Unesite naziv države: ");
        String naziv = ulaz.nextLine();
        Grad grad=dao.glavniGrad(naziv);
        if(grad==null)
            System.out.println("Nepostojeća država");
        else
            System.out.println("Glavni grad države" + naziv +" je "+ grad.getNaziv());
    }

    public static void main(String[] args) throws SQLException, FileNotFoundException {
        System.out.println("Gradovi su:\n" + ispisiGradove());
        glavniGrad();
    }
}