/*
Author: Scott Field
Name: Circle Color Change Using Mouse
Date: 09/20/2023
Version: 1.0
Purpose: 
Write a program that displays the color of a circle as black when the mouse button is pressed, and as white when the mouse button is released.
Dependencies:
This program requires JavaFX and all of its libraries, the -jar file was run with the --enable-preview argument
ex: java --module-path "path to JavaFX lib folder" --add-modules javafx.controls,javafx.base,javafx.fxml,javafx.graphics,javafx.media,javafx.web -jar --enable-preview Module_Five_Program_Two.jar
*/

import javafx.application.Application;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        //Set The Circle
        Circle circle = new Circle(200.0f,200.0f,100.0f);
        circle.setFill(Color.WHITE);
        circle.setStroke(Color.BLACK);

        //Set The Pane
        Pane pane = new Pane();
        pane.getChildren().add(circle);

        //Add The Pane To The Scene
        Scene scene = new Scene(pane,400,400);

        //Add The Event Listener To The Widget

        //If The Left Mouse Button Is Pressed 
        pane.setOnMousePressed(e ->{
            //Set the fill of the circle to black
            circle.setFill(Color.BLACK);
        });

        //If The Left Mouse Button Is Released
        pane.setOnMouseReleased(e ->{
            //set the fill of the circle to white
            circle.setFill(Color.WHITE);
        });

        //Set The Window To Show The Widgets
        primaryStage.setTitle("Mouse Click Circle Change");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
