package com.example.javaapplication;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.button.Button;

@Route("/hello")
public class MainView extends VerticalLayout {
    public MainView()
    {
        var Button = new Button("Hello");
        add(Button);
    }
}
