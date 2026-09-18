module com.example.evaluacion_2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.evaluacion_2 to javafx.fxml;
    opens com.example.evaluacion_2.Controller to javafx.fxml;
    exports com.example.evaluacion_2;
    exports com.example.evaluacion_2.Controller;
}
