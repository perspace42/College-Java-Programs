/*
Author: Scott Field
Name: ScrollBar Color Set Text
Date: 09/20/2023
Version: 1.0
Purpose: 
Use scroll bars or sliders to select the color for a text, as shown in Figure 16.43b. 
Four horizontal scroll bars are used for selecting the colors: red, green, blue, and opacity percentages.
Dependencies:
This program requires JavaFX and all of its libraries, the -jar file was run with the --enable-preview argument
ex: java --module-path "path to JavaFX lib folder" --add-modules javafx.controls,javafx.base,javafx.fxml,javafx.graphics,javafx.media,javafx.web -jar --enable-preview Module_Five_Program_Three.jar
*/

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.paint.Color;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.control.ScrollBar;

public class Main extends Application {

    //Function for adding the event listener to each of the ScrollBars
    private ScrollBar addColorListener(ScrollBar thisScrollBar, ScrollBar redScrollBar, ScrollBar greenScrollBar, ScrollBar blueScrollBar, ScrollBar opacityScrollBar, Label colorTextLabel){
        thisScrollBar.valueProperty().addListener((observable, oldValue, newValue) -> {
            int red = (int) redScrollBar.getValue();
            int green = (int) greenScrollBar.getValue();
            int blue = (int) blueScrollBar.getValue();
            double opacity = opacityScrollBar.getValue();
            colorTextLabel.setTextFill(Color.rgb(red, green, blue, opacity));
        });
        return thisScrollBar;
    }
    
    @Override
    public void start(Stage primaryStage) {
        //Set The Label To Be Changed By The Scrollbars
        Label colorTextLabel = new Label("Show Colors");
        colorTextLabel.setPadding(new Insets(0,0,10,0));
        
        //Set The Scrollbars
        ScrollBar redScrollBar = new ScrollBar();;
        redScrollBar.maxProperty().set(255);
        
        ScrollBar greenScrollBar = new ScrollBar();
        greenScrollBar.maxProperty().set(255);

        ScrollBar blueScrollBar = new ScrollBar();
        blueScrollBar.maxProperty().set(255);

        ScrollBar opacityScrollBar = new ScrollBar();
        opacityScrollBar.maxProperty().set(1);
        //set the value so the user can actually see the text on startup
        opacityScrollBar.valueProperty().set(1);

        //Set The Event Listeners For The ScrollBars
        redScrollBar = addColorListener(redScrollBar, redScrollBar, greenScrollBar, blueScrollBar, opacityScrollBar, colorTextLabel);
        greenScrollBar = addColorListener(greenScrollBar, redScrollBar, greenScrollBar, blueScrollBar, opacityScrollBar, colorTextLabel);
        blueScrollBar = addColorListener(blueScrollBar, redScrollBar, greenScrollBar, blueScrollBar, opacityScrollBar, colorTextLabel);
        opacityScrollBar = addColorListener(opacityScrollBar, redScrollBar, greenScrollBar, blueScrollBar, opacityScrollBar, colorTextLabel);

        //Set The Pane To Store The Widgets
        GridPane pane = new GridPane();
        pane.setPadding(new Insets(10.0,10.0,10.0,10.0));
        pane.setAlignment(Pos.CENTER);

        //Set The Widgets In The Pane

        //Add the title label
        GridPane.setColumnSpan(colorTextLabel, 2);
        GridPane.setHalignment(colorTextLabel, javafx.geometry.HPos.CENTER);

        pane.add(colorTextLabel,0,0);

        //Add The scrollbars and labels
        pane.add(new Label("Red"),0,1);
        pane.add(redScrollBar,1,1);

        pane.add(new Label("Green"),0,2);
        pane.add(greenScrollBar,1,2);

        pane.add(new Label("Blue"),0,3);
        pane.add(blueScrollBar,1,3);

        pane.add(new Label("Opacity"),0,4);
        pane.add(opacityScrollBar,1,4);

        //Add The Pane To The Scene
        Scene scene = new Scene(pane,400,400);

        //Set The Scene In The Stage
        primaryStage.setTitle("ScrollBar Color Set Text");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
