package com.example.vjezba10_11;

import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class DrzavaController {
    public TextField nazivTextField;
    public ChoiceBox glGradChoice;
    Drzava drzava = new Drzava();
    boolean poljeIspravno;
    private GeografijaDAO model = GeografijaDAO.getInstance();

    @FXML
    public void initialize() {
        drzava.nazivProperty().bindBidirectional(nazivTextField.textProperty());
        nazivTextField.getStyleClass().add("poljeNijeIspravno");
        poljeIspravno = false;
        //drzava.getGlavniGrad().nazivProperty().bindBidirectional(glGradChoice.converterProperty());
        glGradChoice.setItems(model.getGradovi());
        glGradChoice.getSelectionModel().select(1);
        drzava.nazivProperty().addListener((obs, oV, nV) -> {
            if(nazivTextField.getText().trim().isEmpty()) {
                nazivTextField.getStyleClass().removeAll("poljeJeIspravno");
                nazivTextField.getStyleClass().add("poljeNijeIspravno");
                poljeIspravno = false;
            } else {
                poljeIspravno = true;
                nazivTextField.getStyleClass().removeAll("poljeNijeIspravno");
                nazivTextField.getStyleClass().add("poljeJeIspravno");
            }
        });
    }

    public void dodajOnClick(ActionEvent actionEvent) {
        if(!poljeIspravno) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Neispravni podaci");
            alert.setHeaderText("Niste isravno popunili polje");
            alert.setContentText("Naziv ne smije biti prazan");
            alert.showAndWait();
        } else {
            boolean listContainsDrzava = false;
            String naziv = nazivTextField.getText();
            for (var name : model.drzava()) {
                if (name.getNaziv().equals(naziv)) {
                    listContainsDrzava = true;
                }
            }
            if (!listContainsDrzava) {
                model.dodajDrzavu(new Drzava(IDGetter.drzavaIDProperty(), new SimpleStringProperty(naziv),
                        (Grad) glGradChoice.getSelectionModel().getSelectedItem()));
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Unesena drzava vec postoji");
                alert.setHeaderText("Drzava koju ste unijeli vec je ranije unesena");
                alert.showAndWait();
            }
        }
    }

    public void exitOnClick(ActionEvent actionEvent) {
        Node node = (Node) actionEvent.getSource();
        Stage s = (Stage) node.getScene().getWindow();
        s.close();
    }
}
