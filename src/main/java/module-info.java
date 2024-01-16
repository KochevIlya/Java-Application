module com.example.javaapplication {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.dataformat.xml;
    requires exp4j;
    requires java.xml.bind;
    requires lombok;
    requires java.desktop;
    requires flow.server;
    requires vaadin.ordered.layout.flow;
    requires vaadin.button.flow;
    requires org.json;
    requires vaadin.spring;
    requires spring.boot;
    requires spring.boot.autoconfigure;
    requires spring.context;
    requires vaadin.upload.flow;
    requires vaadin.combo.box.flow;
    requires vaadin.notification.flow;
    requires com.sun.jna.platform;
    requires vaadin.checkbox.flow;
    requires vaadin.dialog.flow;
    requires vaadin.text.field.flow;

    opens com.example.javaapplication to javafx.fxml, java.xml.bind;
    exports com.example.javaapplication;
}