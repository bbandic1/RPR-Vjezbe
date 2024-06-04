package com.example.vjezba10_11;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.sql.*;

// Function to get unique primary key
public class IDGetter {
    // so that I do not need to cast later on I will convert to Properties
    private static SimpleIntegerProperty gradID;
    private static SimpleIntegerProperty drzavaID;
    private static Connection priv;

    static {
        try {
            priv = DriverManager.getConnection("jdbc:sqlite:bazaGradDrzava.db");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static SimpleIntegerProperty gradIDProperty() {
        try(PreparedStatement upit = priv.prepareStatement("SELECT MAX(ID) + 1 FROM Grad;")) {
            ResultSet rs = upit.executeQuery();
            gradID = new SimpleIntegerProperty(rs.getInt(1));
            if(gradID.get() == 0) {
                gradID = new SimpleIntegerProperty(1);
            }
            return gradID;
        } catch(SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    public static SimpleIntegerProperty drzavaIDProperty() {
        try(PreparedStatement upit = priv.prepareStatement("SELECT MAX(ID) + 1 FROM Drzava;")) {
            ResultSet rs = upit.executeQuery();
            drzavaID = new SimpleIntegerProperty(rs.getInt(1));
            return drzavaID;
        } catch(SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
