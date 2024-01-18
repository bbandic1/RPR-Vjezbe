package com.example.vjezba10_11;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException, InterruptedException {
        // pri pocetku programa brisanje i popunjavanje baze
        DatabaseResetThread dbReset1 = new DatabaseResetThread("Grad");
        DatabaseResetThread dbReset2 = new DatabaseResetThread("Drzava");
        Thread t1 = new Thread(dbReset1);
        Thread t2 = new Thread(dbReset2);

        t1.setName("Prvi thread");
        t2.setName("Drugi thread");
        t1.start();
        t2.start();
        t1.join();
        t2.join();

        // popunjavanje tabela
        DatabaseResetThread dbPopuniGrad = new DatabaseResetThread("PopuniGrad");
        DatabaseResetThread dbPopuniDrzavu = new DatabaseResetThread("PopuniDrzavu");

        Thread popuniGrad = new Thread(dbPopuniGrad);
        Thread popuniDrzavu = new Thread(dbPopuniDrzavu);

        popuniGrad.setName("PopuniGradTH");
        popuniDrzavu.setName("PopuniDrzavuTH");
        popuniGrad.start();
        popuniDrzavu.start();
        // main ceka dok se oba threada ne zavrse
        popuniDrzavu.join();
        popuniGrad.join();
        //------------------
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("fxml/glavna.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Gradovi svijeta");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}