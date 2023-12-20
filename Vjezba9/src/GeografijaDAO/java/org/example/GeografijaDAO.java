package org.example;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.Scanner;

public class GeografijaDAO {
    private static GeografijaDAO instance;
    private Connection conn;
    private PreparedStatement glavniGradUpit, dajDrzavuUpit, obrisiDrzavuUpit;
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
            glavniGradUpit=conn.prepareStatement("SELECT Grad.Grad_ID, Grad.Naziv, Grad.Broj_Stanovnika, Grad.Drzava_ID FROM Grad, Drzava WHERE grad.Drzava_ID=Drzava.Drzava_ID AND Drzava.Glavni_Grad=?");
        } catch (SQLException e) {
            regenerisiBazu();
            try {
                glavniGradUpit=conn.prepareStatement("SELECT Grad.Grad_ID, Grad.Naziv, Grad.Broj_Stanovnika, Grad.Drzava_ID FROM Grad, Drzava WHERE grad.Drzava_ID=Drzava.Drzava_ID AND Drzava.Glavni_Grad=?");
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
        try {
            dajDrzavuUpit= conn.prepareStatement("SELECT * FROM Drzava WHERE Drzava_id=?");
            obrisiDrzavuUpit=conn.prepareStatement("DELETE FROM Drzava WHERE Naziv=?");
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

    private Drzava dajDrzavuIzResultSeta(ResultSet result, Grad grad) throws SQLException {
        return new Drzava(result.getInt(1),result.getString(2),grad);


    }
    public void obrisiDrzavu(String drzava){


    }
}