module com.example.evaluacion_2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.evaluacion_2 to javafx.fxml;
    exports com.example.evaluacion_2;
}