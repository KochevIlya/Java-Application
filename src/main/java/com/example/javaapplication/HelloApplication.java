package com.example.javaapplication;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
//        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
//        stage.setTitle("Hello!");
//        stage.setScene(scene);
//        stage.show();
    }

    public static void main(String[] args) {
       // launch();

        String readerName = new String("");
        Reader reader = new Reader(readerName);
        Finder finder = new Finder(reader);
        finder.find(reader);
        Calculator calculator = new Calculator();
        Result result = calculator.calculate(finder);

        Replacer replacer = new Replacer();

        String writerName = new String("");


        Writer writer = new Writer(writerName);
        writer.write(replacer);
        System.exit(0);
    }
}