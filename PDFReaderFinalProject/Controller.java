/*
Author: Scott Field
Name: Controller
Date: 09/21/2023
Version: 1.0
Purpose:
A class for storing all of the methods to be used when
the buttons in the program are pressed
*/

public class Controller{
    //Controller shouldn't need any member variables because it is just storing functions for the various buttons that the UI will contain later
    public Controller(){
    }

    //Function to increase the size of the pdf by a set amount
    public void onZoomInButtonPressed(PDFViewer fileViewer){
        //zooming in and zooming out are not yet implemented
    }

    //Function to decrease the size of the pdf by a set amount
    public void onZoomOutButtonPressed(){
        //zooming in and zooming out are not yet implemented
    }

    //Function to increase or decrease the size of a pdf by a user given zoom value
    public void onSetZoomPressed(double zoomValue){
        //zooming in by an inputed value has not yet been implemented
    }

    //Function to load the next page
    public void onNextPageButtonPressed(PDFViewer fileViewer){
        fileViewer.incrementPage();
        //load next page is not yet implemented
    }

    //Function to load the next page
    public void onPreviousPageButtonPressed(PDFViewer fileViewer){
        fileViewer.decrementPage();
        //load previous page is not yet implemented
    }

    //Function to open a new pdf file from local directory
    public void onOpenButtonPressed(){
        //load file from local directory is not yet implemented
    }

    //Function to open a new pdf file from a web url
    public void onOpenFromURLButtonPressed(){
        //load file from web url is not yet implemented
    }

    //Function to save a currently opened pdf file to a local directory (and also rename it)
    public void onSaveAsButtonPressed(){
        //save currently opened pdf file is not yet implemented
    }

    //Function to close the currently opened pdf file
    public void onCloseButtonPressed(){
        //close currently opened pdf file is not yet implemented
    }

    //Function to close the program
    public void onExitButtonPressed(){
        //close program is not yet implemented
    }

}
