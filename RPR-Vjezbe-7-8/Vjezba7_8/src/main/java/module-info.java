module com.example.vjezba {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.vjezba to javafx.fxml;
    exports com.example.vjezba;
}