package com.example.vjezba10_11;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class GlavnaController {
    @FXML
    private TableColumn idCol;
    @FXML
    private TableColumn nazivCol;
    @FXML
    private TableColumn stanovnikaCol;
    @FXML
    private TableColumn drzavaCol;
    GeografijaDAO model = GeografijaDAO.getInstance();
    @FXML
    private TableView gradoviTableView = new TableView<>(model.getGradovi());
    @FXML
    public void initialize() {
        idCol.setCellValueFactory(new PropertyValueFactory<Grad, Integer>("id"));
        nazivCol.setCellValueFactory(new PropertyValueFactory<Grad, String>("naziv"));
        stanovnikaCol.setCellValueFactory(new PropertyValueFactory<Grad, Integer>("brojStanovnika"));
        drzavaCol.setCellValueFactory(new PropertyValueFactory<Grad, Drzava>("drzava"));
        gradoviTableView.setItems(model.getGradovi());
        gradoviTableView.getSelectionModel().selectNext();
        model.setTrenutniGrad((Grad) gradoviTableView.getSelectionModel().getSelectedItem());
        gradoviTableView.getSelectionModel().selectedItemProperty().addListener((obs, Ov, novoIzabraniGrad) -> {
            GeografijaDAO.setTrenutniGrad((Grad) novoIzabraniGrad);
        });
    }

    public void dodajGradOnClick(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = fxmlLoader.load(getClass().getResource("fxml/grad.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Dodaj grad");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        stage.setOnHidden(event -> {
            gradoviTableView.setItems(model.gradovi);
            gradoviTableView.getSelectionModel().selectNext();
            model.setTrenutniGrad((Grad) gradoviTableView.getSelectionModel().getSelectedItem());
        });
    }

    public void dodajDrzavuOnClick(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = fxmlLoader.load(getClass().getResource("fxml/drzava.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Dodaj drzavu");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public void izmijeniGradOnClick(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("fxml/grad.fxml"));
        stage.setTitle("Izmjena grada");
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();

        stage.setOnHidden(event -> gradoviTableView.refresh());
    }

    public void obrisiGradOnClick(ActionEvent actionEvent) {
        try {
            model.obrisiGrad(GeografijaDAO.getTrenutniGrad());
        } catch (SQLException e) {}
        gradoviTableView.getSelectionModel().selectNext();
    }
}