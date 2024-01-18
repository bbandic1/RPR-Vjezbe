package com.example.vjezba10_11;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.Scanner;

public class GeografijaDAO {
    private static GeografijaDAO instance = null;
    private final Connection conn;
    private PreparedStatement ps;
    private final PreparedStatement vratiGlavniGrad, vratiDrzavu, vratiSveGradove, dodajGradUpit, sljedeciIdGrad, dodajDrzavuUpit, sljedeciIdDrzava;
    private final PreparedStatement izmjeniGradUpit, nadjiDrzavuUpit, vratiSveDrzave;
    private static Grad trenutniGrad;
    public ObservableList<Grad> gradovi;
    private ObservableList<Drzava> drzave;
    public static Grad getTrenutniGrad() {
        return trenutniGrad;
    }
    public static void setTrenutniGrad(Grad trenutniGrad) {
        GeografijaDAO.trenutniGrad = trenutniGrad;
    }

    private GeografijaDAO() throws SQLException {
        String url = "jdbc:sqlite:bazaGradDrzava.db";
        conn = DriverManager.getConnection(url);
        try {
            ps = conn.prepareStatement("SELECT * FROM Grad, Drzava;");
        } catch (SQLException e) {
            kreirajBazu();
        } finally {
            ps = conn.prepareStatement("SELECT * FROM Grad, Drzava;");
            vratiGlavniGrad = conn.prepareStatement("SELECT * FROM Grad g, Drzava d " +
                    "WHERE d.naziv = ? AND d.GlavniGrad = g.id;");
            vratiDrzavu = conn.prepareStatement("SELECT * FROM Drzava WHERE naziv = ?;");
            vratiSveGradove = conn.prepareStatement("SELECT * FROM Grad;");
            dodajGradUpit = conn.prepareStatement("INSERT INTO Grad VALUES (?, ?, ?, ?);");
            sljedeciIdGrad = conn.prepareStatement("SELECT Max(id) + 1 FROM Grad;");
            dodajDrzavuUpit = conn.prepareStatement("INSERT INTO Drzava VALUES (?, ?, ?);");
            sljedeciIdDrzava = conn.prepareStatement("SELECT Max(id) + 1 FROM Drzava;");
            izmjeniGradUpit = conn.prepareStatement("UPDATE Grad SET Naziv=?, BrojStanovnika=?, Drzava=?" +
                    "WHERE id=?;");
            nadjiDrzavuUpit = conn.prepareStatement("SELECT * FROM Drzava WHERE id=?;");
            vratiSveDrzave = conn.prepareStatement("SELECT * FROM Drzava;");
        }
    }
    private void ispisi(ResultSet resultSet) throws SQLException {
        ResultSetMetaData rsmd = resultSet.getMetaData();
        int columnsNumber = rsmd.getColumnCount();
        while (resultSet.next()) {
            for (int i = 1; i <= columnsNumber; i++) {
                if (i > 1) System.out.print(",  ");
                String columnValue = resultSet.getString(i);
                //System.out.print(columnValue + " " + rsmd.getColumnName(i));
                System.out.print(rsmd.getColumnName(i) + ": " + columnValue);
            }
            System.out.println("");
        }
    }
    public void vratiSveUBazi() {
        try {
            Statement stm = conn.createStatement();
            ResultSet resultSet = stm.executeQuery("SELECT * FROM Drzava");
            System.out.println("Ispis svih Drzava:");
            ispisi(resultSet);
            stm.close();
            stm = conn.createStatement();
            resultSet = stm.executeQuery("SELECT * FROM Grad");
            System.out.println("Ispis svih Gradova:");
            ispisi(resultSet);
        } catch (SQLException e) {
            System.out.println("\nNEMA BAZE!");
        }
    }
    public boolean daLiImaRedova() {
        try {
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                return true;
            }
            return false;
        } catch (SQLException e) {
            System.out.println("\nNEMA BAZE!");
            return false;
        }
    }
    public synchronized void obrisiGrad() throws SQLException {
        System.out.println("Starting obrisiGrad...");
        try(Statement stm = conn.createStatement()) {
            conn.setAutoCommit(false);
            stm.execute("DELETE FROM Grad;");
            if (gradovi != null) {
                gradovi.clear();
            }
            conn.commit();
        } catch (SQLException e) {
            conn.rollback();
            throw e;
        } finally {
            conn.setAutoCommit(true);
            System.out.println("Finishing obrisiGrad...");
        }
    }
    public synchronized void obrisiDrzavu() throws SQLException {
        System.out.println("Starting obrisiDrzavu...");
        try(Statement stm = conn.createStatement()) {
            conn.setAutoCommit(false);
            stm.execute("DELETE FROM Drzava;");
            if (drzave != null) {
                drzave.clear();
            }
            conn.commit();
        } catch (SQLException e) {
            conn.rollback();
            throw e;
        } finally {
            conn.setAutoCommit(true);
            System.out.println("Finishing obrisiDrzavu...");
        }
    }
    public synchronized void popuniTabeluGrad() throws SQLException {
        System.out.println("Starting popuniTabeluGrad...");
        try (Statement stm = conn.createStatement()) {
            conn.setAutoCommit(false);
            // Insert into Grad
            stm.execute("INSERT INTO Grad VALUES (1, 'Pariz', 1658745, 1)");
            stm.execute("INSERT INTO Grad VALUES (2, 'London', 7641498, 2)");
            stm.execute("INSERT INTO Grad VALUES (3, 'Beč', 48744848, 3)");
            stm.execute("INSERT INTO Grad VALUES (4, 'Manchester', 4987497, 2)");
            stm.execute("INSERT INTO Grad VALUES (5, 'Graz', 4198414, 3)");
            conn.commit();
        } catch (SQLException e) {
            conn.rollback();
            throw e;
        } finally {
            conn.setAutoCommit(true);
            System.out.println("Finishing popuniTabeluGrad...");
        }
    }
    public synchronized void popuniTabeluDrzava() throws SQLException {
        System.out.println("Starting popuniTabeluDrzava...");
        try (Statement stm = conn.createStatement()) {
            conn.setAutoCommit(false);
            // Insert into Drzava
            stm.execute("INSERT INTO Drzava VALUES (1, 'Francuska', 1)");
            stm.execute("INSERT INTO Drzava VALUES (2, 'V. Britanija', 2)");
            stm.execute("INSERT INTO Drzava VALUES (3, 'Austrija', 3)");
            conn.commit();
        } catch (SQLException e) {
            conn.rollback();
            throw e;
        } finally {
            conn.setAutoCommit(true);
            System.out.println("Fisnishing popuniTabeluDrzava...");
        }
    }
    public void kreirajBazu() {
        Scanner ulaz = null;
        try {
            ulaz = new Scanner(new FileInputStream("bazaGradDrzava.db.sql"));
            String sqlUpit = "";
            if(ulaz.hasNext()) {
                sqlUpit = sqlUpit + ulaz.nextLine();
                if(sqlUpit.length() > 1 && sqlUpit.charAt(sqlUpit.length() -1 ) == ';') {
                    Statement stm = conn.createStatement();
                    stm.execute(sqlUpit);
                    sqlUpit = "";
                }
            }
            ulaz.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static GeografijaDAO getInstance() {
        if(instance == null) {
            try {
                instance = new GeografijaDAO();
            } catch (SQLException e) {
                instance = null;
                System.out.println("Greska pri radu s bazom OVDJE!");
                System.out.println(e.getMessage());
            }
        }
        return instance;
    }

    public static void removeInstance() throws SQLException {
        if(instance != null) {
            instance.conn.close();
            instance = null;
        }
    }

    public ObservableList<Grad> getGradovi() {
        gradovi = FXCollections.observableArrayList();
        ResultSet rs = null;
        ResultSet rsDrzava = null;
        try {
            rs = vratiSveGradove.executeQuery();
            while(rs.next()) {
                nadjiDrzavuUpit.setInt(1, rs.getInt(4));
                rsDrzava = nadjiDrzavuUpit.executeQuery();
                Drzava d = new Drzava();
                d.setId(rsDrzava.getInt(1));
                d.setNaziv(rsDrzava.getString(2));
                PreparedStatement psGrad = conn.prepareStatement("SELECT * FROM Grad WHERE id = ?;");
                psGrad.setInt(1, rsDrzava.getInt(3));
                ResultSet rsGrad = ps.executeQuery();
                d.setGlavniGrad(new Grad(new SimpleIntegerProperty(rs.getInt(1)),
                        new SimpleStringProperty(rs.getString(2)), new SimpleIntegerProperty(rs.getInt(3)), d));
                Grad grad = new Grad(new SimpleIntegerProperty(rs.getInt(1)), new SimpleStringProperty(rs.getString(2)),
                        new SimpleIntegerProperty(rs.getInt(3)), d);
                gradovi.add(grad);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return gradovi;
    }

    public void dodajGrad(Grad grad) {
        try {
            gradovi.add(grad);
            ResultSet rs = sljedeciIdGrad.executeQuery();
            grad.setId(rs.getInt(1));
            dodajGradUpit.setInt(1, grad.getId());
            dodajGradUpit.setString(2, grad.getNaziv());
            dodajGradUpit.setInt(3, grad.getBrojStanovnika());
            dodajGradUpit.setInt(4, grad.getDrzava().getId());
            dodajGradUpit.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Doslo je do greske u dodavanju grada.");
            System.out.println(e.getMessage());
        }
    }

    public void dodajDrzavu(Drzava drzava) {
        try {
            ResultSet rs = sljedeciIdDrzava.executeQuery();
            drzava.setId(rs.getInt(1));
            dodajDrzavuUpit.setInt(1, drzava.getId());
            dodajDrzavuUpit.setString(2, drzava.getNaziv());
            dodajDrzavuUpit.setInt(3, drzava.getGlavniGrad().getId());
            dodajDrzavuUpit.execute();
        } catch (SQLException e) {
            System.out.println("Doslo je do greske u dodavanju drzave.");
            System.out.println(e.getMessage());
        }
    }

    public void izmijeniGrad(Grad grad) {
        try {
            dodajGradUpit.setString(1, grad.getNaziv());
            dodajGradUpit.setInt(2, grad.getBrojStanovnika());
            dodajGradUpit.setInt(3, grad.getDrzava().getId());
            dodajGradUpit.setInt(4, grad.getId());
            dodajGradUpit.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Doslo je do greske u azuriranju grada.");
            System.out.println(e.getMessage());
        }
    }

    public Drzava nadjiDrzavu(String drzava) {
        try {
            vratiDrzavu.setString(1, drzava);
            ResultSet rs = vratiDrzavu.executeQuery();
            // Postavljanje vrijednost poljima koje ima Drzava
            SimpleIntegerProperty idDrzave = new SimpleIntegerProperty(rs.getInt(1));
            SimpleStringProperty nazivDrzave = new SimpleStringProperty(rs.getString(2));
            Grad glavniGrad = null;

            // Trazenje glavnogGrada
            vratiGlavniGrad.setInt(1, rs.getInt(3));
            ResultSet rsGrad = vratiGlavniGrad.executeQuery();

            SimpleIntegerProperty idGrada = new SimpleIntegerProperty(rsGrad.getInt(1));
            SimpleStringProperty nazivGrada = new SimpleStringProperty(rsGrad.getString(2));
            SimpleIntegerProperty brojStanovnikaGrada = new SimpleIntegerProperty(rsGrad.getInt(3));

            // Drzava za dati grad
            Drzava drzavaGrada = new Drzava(idDrzave, nazivDrzave, glavniGrad);
            // Grad
            glavniGrad = new Grad(idGrada, nazivGrada, brojStanovnikaGrada, drzavaGrada);
            // Namjestanje glavnog grada za drzavu
            drzavaGrada.setGlavniGrad(glavniGrad);

            return drzavaGrada;
        } catch (SQLException e) {
            System.out.println("Doslo je do greske u trazenju drzave.");
            System.out.println(e.getMessage());
            return null;
        }
    }

    public Grad glavniGrad(String drzava) {
        Grad g = null;
        try {
            vratiGlavniGrad.setString(1, drzava);
            ResultSet rs = vratiGlavniGrad.executeQuery();
            if(!rs.next()) return null;
            Drzava d = new Drzava();
            d.setId(rs.getInt(5));
            d.setNaziv(rs.getString(6));
            g = new Grad(new SimpleIntegerProperty(rs.getInt(1)), new SimpleStringProperty(rs.getString(2)),
                    new SimpleIntegerProperty(rs.getInt(3)), d);
            d.setGlavniGrad(g);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
        return g;
    }

    public void obrisiDrzavu(String drzava) {
        try {
            try (PreparedStatement obrisi = conn.prepareStatement("DELETE FROM Grad WHERE id = " +
                    "(SELECT d.id FROM Drzava d WHERE naziv = ?);")) {
                obrisi.setString(1, drzava);
                obrisi.execute();
            }
            try (PreparedStatement obrisi = conn.prepareStatement("DELETE FROM Drzava WHERE naziv = ?;")) {
                obrisi.setString(1, drzava);
                obrisi.execute();
            }
        } catch (SQLException e) {
            System.out.println("Doslo je do greske u brisanju.");
            System.out.println(e.getMessage());
        }
    }

    public void obrisiGrad(Grad zaBrisanja) throws SQLException{
        if(zaBrisanja.getDrzava() == null) {
            // System.out.println("Nema sta za brisati");
            return;
        }
        try(PreparedStatement ps = conn.prepareStatement("DELETE FROM Grad WHERE naziv = ?;")) {
            ps.setString(1, zaBrisanja.getNaziv());
            ps.execute();
            gradovi.remove(zaBrisanja);
        } catch(SQLException e) {
            System.out.println("Doslo je do greske pri izvrsavanju metode obrisiGrad.");
            throw e;
        }
    }

    public ObservableList<Drzava> drzava() {
        drzave = FXCollections.observableArrayList();
        try(ResultSet rs = vratiSveDrzave.executeQuery()) {
            while(rs.next()) {
                drzave.add(new Drzava(new SimpleIntegerProperty(rs.getInt(1)),
                        new SimpleStringProperty(rs.getString(2)), glavniGrad(rs.getString(3))));
            }
        } catch(SQLException e) {
            System.out.println("Doslo je do greske pri vracanju liste drzava.");
        }
        return drzave;
    }
}
