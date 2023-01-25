module com.example.huevos {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;

    opens com.example.huevos to javafx.fxml;
    exports com.example.huevos;
    exports com.example.huevos.controllers;
    opens com.example.huevos.controllers to javafx.fxml;
}