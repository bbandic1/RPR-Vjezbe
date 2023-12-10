module com.example.kalkulator {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.scripting;


    opens com.example.kalkulator to javafx.fxml;
    exports com.example.kalkulator;
}