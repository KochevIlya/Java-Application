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

        String readerName = new String("input1.xml");
        String writerName = new String("output.txt");

        Result result = new Result();
try {
    Decider decider = new Decider();
    decider.makeDecision(readerName, writerName, result);
}
catch(Exception e)
{
    System.exit(0);
}
        Reader reader = new Reader(readerName);
        reader.read(result);

        Finder finder = new Finder();
        finder.find(result);
try {
    Calculator calculator = new Calculator();
    calculator.calculate(result);
}
catch(Exception e)
{
    System.exit(0);
}
        Replacer replacer = new Replacer();
        replacer.replace(result);

        Writer writer = new Writer(writerName);
        writer.write(result);
        System.exit(0);
    }
}