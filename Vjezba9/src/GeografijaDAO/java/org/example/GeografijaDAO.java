package org.example;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class GeografijaDAO {
    private static GeografijaDAO instance;
    private Connection conn;
    private PreparedStatement glavniGradUpit, dajDrzavuUpit, obrisiDrzavuUpit, obrisiGradoveZaDrzavu,nadjiDrzavuUpit,
                            dajGradoveUpit, dodajGradUpit, odrediIDGradaUpit, dodajDrzaveUpit, odrediIDDrzaveUpit,
                            promijeniGradUpit, dajGradUpit;
    public static GeografijaDAO getInstance() throws SQLException, FileNotFoundException {
        if (instance == null)
            instance = new GeografijaDAO();
        return instance;
    }
    private GeografijaDAO() throws FileNotFoundException, SQLException {
        try {
            conn= DriverManager.getConnection("jdbc:sqlite:BazaPodataka.db");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            glavniGradUpit=conn.prepareStatement("SELECT Grad.Grad_ID, Grad.Naziv, Grad.Broj_Stanovnika, Grad.Drzava_ID FROM Grad, Drzava WHERE grad.Drzava_ID=Drzava.Drzava_ID AND Drzava.Naziv=?");
        } catch (SQLException e) {
            regenerisiBazu();
            try {
                glavniGradUpit=conn.prepareStatement("SELECT Grad.Grad_ID, Grad.Naziv, Grad.Broj_Stanovnika, Grad.Drzava_ID FROM Grad, Drzava WHERE grad.Drzava_ID=Drzava.Drzava_ID AND Drzava.Naziv=?");
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
        try {
            dajDrzavuUpit= conn.prepareStatement("SELECT * FROM Drzava WHERE Drzava_ID=?");
            dajGradUpit= conn.prepareStatement("SELECT * FROM Grad WHERE Grad_ID=?");
            obrisiGradoveZaDrzavu = conn.prepareStatement("DELETE FROM Grad WHERE Drzava_ID=?");
            obrisiDrzavuUpit=conn.prepareStatement("DELETE FROM Drzava WHERE Naziv=?");
            nadjiDrzavuUpit=conn.prepareStatement("SELECT * FROM Drzava WHERE Naziv=?");
            dajGradoveUpit=conn.prepareStatement("SELECT * FROM Grad ORDER BY Broj_Stanovnika DESC");
            dodajGradUpit=conn.prepareStatement("INSERT INTO Grad VALUES(?,?,?,?)");
            odrediIDGradaUpit=conn.prepareStatement("SELECT MAX(Grad_ID)+1 FROM Grad");
            dodajDrzaveUpit=conn.prepareStatement("INSERT INTO Drzava VALUES(?,?,?)");
            odrediIDDrzaveUpit=conn.prepareStatement("SELECT MAX(Drzava_ID)+1 FROM Drzava");
            promijeniGradUpit=conn.prepareStatement("UPDATE Grad SET Naziv=?, Broj_Stanovnika=?, Drzava_ID=? WHERE Grad_ID=?");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void regenerisiBazu() throws SQLException, FileNotFoundException {
        Scanner ulaz=new Scanner(new FileInputStream("BazaPodataka.db.sql"));
        String sqlUpit = "";
        while(ulaz.hasNext())
        {
            sqlUpit=ulaz.nextLine();
            if(sqlUpit.charAt(sqlUpit.length()-1)==';'){
                Statement stat=conn.createStatement();
                stat.execute(sqlUpit);
                sqlUpit="";
            }
        }
        ulaz.close();
    }
    public Grad glavniGrad(String drzava){
        try {
            glavniGradUpit.setString(1,drzava);
            ResultSet result = glavniGradUpit.executeQuery();
            if(!result.next())
                return null;
            return dajGradIzResultSeta(result);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Grad dajGradIzResultSeta(ResultSet result) throws SQLException {
        Grad grad= new Grad(result.getInt(1),result.getString(2),result.getInt(3),null);
        grad.setDrzavaID(dajDrzavu(result.getInt(4), grad));
        return grad;
    }

    private Drzava dajDrzavu(int drzavaID, Grad grad) {
        try {
            dajDrzavuUpit.setInt(1,drzavaID);
            ResultSet result = dajDrzavuUpit.executeQuery();
            if(!result.next())
                return null;
            return dajDrzavuIzResultSeta(result, grad);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private Grad dajGrad(int drzavaID) {
        try {
            dajGradUpit.setInt(1,drzavaID);
            ResultSet result = dajGradUpit.executeQuery();
            if(!result.next())
                return null;
            return dajGradIzResultSeta(result);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Drzava dajDrzavuIzResultSeta(ResultSet result, Grad grad) throws SQLException {
        return new Drzava(result.getInt(1),result.getString(2),grad);


    }
    public void obrisiDrzavu(String nazivdrzava){
        try {
            nadjiDrzavuUpit.setString(1,nazivdrzava);
            ResultSet result = nadjiDrzavuUpit.executeQuery();
            if(!result.next())
                return;
            Drzava drzava = dajDrzavuIzResultSeta(result, null);

            obrisiGradoveZaDrzavu.setInt(1,drzava.getDrzavaID());
            obrisiGradoveZaDrzavu.executeUpdate();

            obrisiDrzavuUpit.setInt(1,drzava.getDrzavaID());
            obrisiDrzavuUpit.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public ArrayList<Grad> gradovi(){
        ArrayList<Grad> rez = new ArrayList<>();
        try {
            ResultSet result = dajGradoveUpit.executeQuery();
            while(result.next()){
                Grad grad=dajGradIzResultSeta(result);
                rez.add(grad);
        }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rez;
    }

    public void dodajGrad(Grad grad){
        try {
            ResultSet result = odrediIDGradaUpit.executeQuery();
            int id=1;
            if(result.next()){
                id=result.getInt(1);
            }
            dodajGradUpit.setInt(1,grad.getGradID());
            dodajGradUpit.setString(2,grad.getNaziv());
            dodajGradUpit.setInt(3,grad.getBrojStanovnika());
            dodajGradUpit.setInt(4,grad.getDrzavaID().getDrzavaID());
            dodajGradUpit.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void dodajDrzavu(Drzava drzava){
        try {
            ResultSet result = odrediIDDrzaveUpit.executeQuery();
            int id=1;
            if(result.next()){
                id=result.getInt(1);
            }
            dodajDrzaveUpit.setInt(1,drzava.getDrzavaID());
            dodajDrzaveUpit.setString(2,drzava.getNaziv());
            dodajDrzaveUpit.setInt(3,drzava.getGlavniGrad().getGradID());
            dodajDrzaveUpit.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void izmijeniGrad(Grad grad){
        try {
            promijeniGradUpit.setString(1,grad.getNaziv());
            promijeniGradUpit.setInt(2,grad.getBrojStanovnika());
            promijeniGradUpit.setInt(3,grad.getDrzavaID().getDrzavaID());
            promijeniGradUpit.setInt(4,grad.getGradID());
            promijeniGradUpit.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    Drzava nadjiDrzavu(String nazivdrzava){
        try {
            nadjiDrzavuUpit.setString(1,nazivdrzava);
            ResultSet result = nadjiDrzavuUpit.executeQuery();
            if(!result.next())
                return null;
            return dajDrzavuIzResultSeta(result, dajGrad(result.getInt(3)));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void removeInstance(){
        if(instance==null)
            return;
        instance.close();
        instance = null;
    }
    public void close(){
        try {
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}