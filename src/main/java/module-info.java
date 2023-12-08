module com.example.javaapplication {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.dataformat.xml;
    requires exp4j;

    opens com.example.javaapplication to javafx.fxml;
    exports com.example.javaapplication;
}