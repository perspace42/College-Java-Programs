/*
Author: Scott Field
Name: Main
Date: 09/21/2023
Version: 1.0
Purpose:
A class for setting up the UI and the program
main loop using JavaFX
*/

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.control.Button;

import java.io.File;


public class Main extends Application {
    @Override
    public void start(Stage primaryStage){

        //If those tests don't result in errors run the main program

        //Set the Controller
        Controller binding = new Controller();

        //Load The Image using the PDFReader
        PDFReader fileReader = new PDFReader(new File("temp/HeroClix_Core_Rules.pdf"));

        //Set The Image to be displayed using the PDFViewer
        PDFViewer fileViewer = new PDFViewer(fileReader);

        //set the buttons to be cycle between pages
        Button previousPageButton = new Button("Previous");
        Button nextPageButton = new Button("Next");

        // Create the VBox to store the image and the buttons for the test
        VBox imageDisplay = new VBox();
        // Create the HBox to store the buttons for cycling between pages
        HBox buttonDisplay = new HBox(previousPageButton,nextPageButton);
        buttonDisplay.setAlignment(Pos.CENTER);

        imageDisplay.getChildren().addAll(fileViewer, buttonDisplay);

        //Set the PDFViewer to fit to the width and height of the Scene
        fileViewer.fitWidthProperty().bind(imageDisplay.widthProperty());
        fileViewer.fitHeightProperty().bind(imageDisplay.heightProperty().subtract(previousPageButton.heightProperty()));

        // Create the Scene and set it on the Stage
        Scene scene = new Scene(imageDisplay, 900, 900);
        //Set the Scene to the Stage and show the Stage
        primaryStage.setScene(scene);
        primaryStage.show();

        //Event Listeners
        //set the methods for previous and next page
        previousPageButton.setOnAction(e ->
            binding.onPreviousPageButtonPressed(fileViewer)
        );

        nextPageButton.setOnAction(e ->
            binding.onNextPageButtonPressed(fileViewer)
        );

    }

    public static void main(String[] args) {       
        //Run tests on startup
        testInvalidInput();
        testValidInput();
        //run main program
        launch(args);
    }

    //Method to test classes on startup
    public static void testInvalidInput(){
        System.out.println("Invalid Input Test (Program should continue even with incorrect input and all values should be zero)");
        //test class validation
        PDFReader testReader = new PDFReader(new File("temp/IncorrectFileType.txt"));
        PDFViewer testViewer = new PDFViewer(testReader);

        //test class data
        System.out.println("Current Page Height: " + testReader.getCurrentPageHeight());
        System.out.println("Current Page Width: " + testReader.getCurrentPageWidth());
        System.out.println("Current Page Number: " + testReader.getCurrentPageNumber());
    }

    public static void testValidInput(){
        System.out.println("Valid Input Test (Program should continue with correct input and only Page Number should be zero)");
        //test class validation
        PDFReader testReader = new PDFReader(new File("temp/HeroClix_Powers_Abilities.pdf"));
        PDFViewer testViewer = new PDFViewer(testReader);

        //test class data
        System.out.println("Current Page Height: " + testReader.getCurrentPageHeight());
        System.out.println("Current Page Width: " + testReader.getCurrentPageWidth());
        System.out.println("Current Page Number: " + testReader.getCurrentPageNumber());
    }

}
