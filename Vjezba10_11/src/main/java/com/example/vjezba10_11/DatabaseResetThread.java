package com.example.vjezba10_11;

import java.sql.SQLException;

public class DatabaseResetThread implements Runnable {
    private String akcija;
    public DatabaseResetThread(String akcija) {
        this.akcija = akcija;
    }
    @Override
    public void run() {
        try {
            GeografijaDAO gdao = GeografijaDAO.getInstance();
            synchronized (gdao) {
                if (akcija.equals("Grad")) {
                    gdao.obrisiGrad();
                } else if (akcija.equals("Drzava")) {
                    gdao.obrisiDrzavu();
                } else if (akcija.equals("PopuniGrad")) {
                    gdao.popuniTabeluGrad();
                } else {
                    gdao.popuniTabeluDrzava();
                }
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
}
