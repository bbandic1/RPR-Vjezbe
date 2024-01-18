package com.example.vjezba10_11;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.PreparedStatement;

public class GradController {
    @FXML
    private TextField nazivTextField;
    @FXML
    private TextField brStanovnikaTextField;
    @FXML
    private ChoiceBox drzavaChoiceBox;
    GeografijaDAO model = GeografijaDAO.getInstance();
    boolean ispravnoPopunjenNaziv, ispravnoPopunjeniStan;

    @FXML
    public void initialize() {
        nazivTextField.getStyleClass().add("poljeNijeIspravno");
        brStanovnikaTextField.getStyleClass().add("poljeNijeIspravno");
        ispravnoPopunjenNaziv = false;
        ispravnoPopunjeniStan = false;

        nazivTextField.textProperty().addListener((obs, oV, nV) -> {
            if(nazivTextField.getText().trim().isEmpty()) {
                nazivTextField.getStyleClass().removeAll("poljeJeIspravno");
                nazivTextField.getStyleClass().add("poljeNijeIspravno");
                ispravnoPopunjenNaziv = false;
            } else {
                nazivTextField.getStyleClass().removeAll("poljeNijeIspravno");
                nazivTextField.getStyleClass().add("poljeJeIspravno");
                ispravnoPopunjenNaziv = true;
            }
        });
        brStanovnikaTextField.textProperty().addListener((obs, oV, nV) -> {
            if(brStanovnikaTextField.getText().trim().isEmpty()) {
                brStanovnikaTextField.getStyleClass().removeAll("poljeJeIspravno");
                brStanovnikaTextField.getStyleClass().add("poljeNijeIspravno");
                ispravnoPopunjeniStan = false;
            } else {
                brStanovnikaTextField.getStyleClass().removeAll("poljeNijeIspravno");
                brStanovnikaTextField.getStyleClass().add("poljeJeIspravno");
                ispravnoPopunjeniStan = true;
            }
        });
        // user inputs new city and specifies its country
        drzavaChoiceBox.setItems(model.drzava());
        drzavaChoiceBox.getSelectionModel().select(1);
    }
    public void dodajGradOnClick(ActionEvent actionEvent) {
        if(ispravnoPopunjeniStan && ispravnoPopunjenNaziv) {
            Node node = (Node) actionEvent.getSource();
            Stage s = (Stage) node.getScene().getWindow();
            if(s.getTitle().equals("Dodaj grad")) {
                boolean listContainsGrad = false;
                String naziv = nazivTextField.getText();
                ObservableList<Grad> lista_gradova = model.getGradovi();
                for (int i=0; i<lista_gradova.size(); i++) {
                    if (lista_gradova.get(i).getNaziv().equals(naziv)) {
                        listContainsGrad = true;
                    }
                }
                if (!listContainsGrad) {
                    model.dodajGrad(new Grad(IDGetter.gradIDProperty(), new SimpleStringProperty(naziv),
                            new SimpleIntegerProperty(Integer.parseInt(brStanovnikaTextField.getText())),
                            (Drzava) drzavaChoiceBox.getSelectionModel().getSelectedItem()));
                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Grad vec postoji");
                    alert.setHeaderText("Grad koji ste unijeli vec se ranije nalazi u listi");
                    alert.setContentText("Da niste htjeli izmijeniti grad?");
                    alert.showAndWait();
                }
            } else {
                Grad g = GeografijaDAO.getTrenutniGrad();
                g.setDrzava((Drzava) drzavaChoiceBox.getSelectionModel().getSelectedItem());
                g.setNaziv(nazivTextField.getText());
                g.setBrojStanovnika(Integer.parseInt(brStanovnikaTextField.getText()));
                GeografijaDAO.setTrenutniGrad(g);
            }
        }
    }

    public void exitOnClick(ActionEvent actionEvent) {
        Node n = (Node) actionEvent.getSource();
        Stage s = (Stage) n.getScene().getWindow();
        s.close();
    }
}
