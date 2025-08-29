/*
Author: Scott Field
Name: Show_Images_Grid
Date: 09/20/2023
Version: 1.0
Purpose: 
Create a program that displays the provided 4 images in a grid
Dependencies:
This program requires JavaFX and all of its libraries, the -jar file was run with the --enable-preview argument
ex: java --module-path "path to JavaFX lib folder" --add-modules javafx.controls,javafx.base,javafx.fxml,javafx.graphics,javafx.media,javafx.web -jar --enable-preview Module_Five_Program_One.jar
*/

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        // Create The GridPane layout
        GridPane grid = new GridPane();
        //Set The Border Of The Grid
        grid.setPadding(new Insets(10)); 
        //Set The Horizontal Gap Between Grid Items
        grid.setHgap(10);
        //Set The Vertical Gap Between Grid Items
        grid.setVgap(10);

        //Add The Images To the ImagViews 
        ImageView canadaFlag  = new ImageView( new Image ("image/ca.gif"      ));
        ImageView chinaFlag   = new ImageView( new Image ("image/china.gif"   ));
        ImageView englandFlag = new ImageView( new Image ("image/uk.gif"      ));
        ImageView americaFlag = new ImageView( new Image ("image/us.gif"      ));

        // Add the ImageViews to the Grid
        grid.add(canadaFlag , 0, 0);
        grid.add(chinaFlag  , 1, 0);
        grid.add(englandFlag, 0, 1);
        grid.add(americaFlag, 1, 1);

        // Add The Grid To The Scene
        Scene scene = new Scene(grid);

        // Set The Window Title
        primaryStage.setTitle("Display Images");
        // Set The Window Scene
        primaryStage.setScene(scene);
        // Show The Scene
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
