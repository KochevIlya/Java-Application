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
    requires org.eclipse.persistence.moxy;
    requires org.json;
    requires vaadin.spring;
    requires spring.boot;
    requires spring.boot.autoconfigure;
    requires spring.context;

    opens com.example.javaapplication to javafx.fxml, java.xml.bind;
    exports com.example.javaapplication;
}