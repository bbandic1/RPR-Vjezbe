module com.example.vjezba10_11 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.vjezba10_11 to javafx.fxml;
    exports com.example.vjezba10_11;
}