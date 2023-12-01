module com.example.javaapplication {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;

    opens com.example.javaapplication to javafx.fxml;
    exports com.example.javaapplication;
}