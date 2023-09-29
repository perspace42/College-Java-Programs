/*
Author: Scott Field
Name: Access_Update_Table
Date: 09/29/2023
Version: 1.0
Purpose: 
Use the Math.random() method to generate random numbers for each record.
Create a dialog box that contains DBConnectionPanel, discussed in Exercise
34.3. Use this dialog box to connect to the database. When you click the Connect
to Database button in Figure 35.5a, the dialog box in Figure 35.5b is displayed.

Instructions:
The Temp table is created as follows:
create table Temp(num1 double, num2 double, num3 double)
*/

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.control.ComboBox;

import java.sql.*;

public class Main extends Application {
   //flag variables
   boolean isConnectScreenClosed = true;
   static Connection connection;
   static boolean isConnectionEstablished;

   public static void batchUpdateDatabase(Label updateStatus, Label displayResults){
    //get the start of the operation
    long startTime = System.currentTimeMillis();
    //Attempt The Update
    try{
      PreparedStatement statement = connection.prepareStatement("INSERT INTO Temp VALUES (?, ?, ?)");
        //Add A Random Double To Each Of The 3 Doubles 1000 Times
        for (int i = 0; i < 1000; i++) {
            statement.setDouble(1, Math.random());
            statement.setDouble(2, Math.random());
            statement.setDouble(3, Math.random());
            //Add The Statment To The Batch
            statement.addBatch();
        }
        //perform the batch update
        statement.executeBatch();
        //close the statement
        statement.close();
    //If An Error Occurs Catch What It Is
    }catch (SQLException e) {
        e.printStackTrace();
        //Indicate That The Update Failed
        updateStatus.setText("Batch Update Failed");
        return;
    }
    //get the end time of the operation
    long endTime = System.currentTimeMillis();
    //get the time the operation took
    long elapsedTime = endTime - startTime;
    //Indicate That The Update Succeeded
    updateStatus.setText("Batch Update Succeeded");
    //Add The Results Of The Batch Update 
    displayResults.setText(displayResults.getText() + "Batch Update Completed\nThe elapsed time is " + elapsedTime + "\n");
  }

  public static void nonBatchUpdateDatabase(Label updateStatus, Label displayResults){
    //get the start of the operation
    long startTime = System.currentTimeMillis();
    //Attempt The Update
    try{
      PreparedStatement statement = connection.prepareStatement("INSERT INTO Temp VALUES (?, ?, ?)");
        //Add A Random Double To Each Of The 3 Doubles 1000 Times
        for (int i = 0; i < 1000; i++) {
            statement.setDouble(1, Math.random());
            statement.setDouble(2, Math.random());
            statement.setDouble(3, Math.random());
            //Add The Statment To The Batch
            statement.executeUpdate();
        }
        //close the statement
        statement.close();
    //If An Error Occurs Catch What It Is
    }catch (SQLException e) {
        e.printStackTrace();
        //Indicate That The Update Failed
        updateStatus.setText("Non Batch Update Failed");
        return;
    }
    //get the end time of the operation
    long endTime = System.currentTimeMillis();
    //get the time the operation took
    long elapsedTime = endTime - startTime;
    //Indicate That The Update Succeeded
    updateStatus.setText("Non Batch Update Succeeded");
    //Add The Results Of The Batch Update 
    displayResults.setText(displayResults.getText() + "Non Batch Update Completed\nThe elapsed time is " + elapsedTime + "\n");
  }
    
   

   public static boolean connectToDatabase(String jdbcDriver, String databaseUrl, String username, String password, Text connectionStatus) {
      boolean connectionMade = false;
      try{
        //Attempt to register the jdbcDriver with the DriverManager class
        Class.forName(jdbcDriver);
        //Attempt to establish the connection using the DriverManager, databaseUrl, username and password
        connection = DriverManager.getConnection(databaseUrl, username, password);
        //If No Errors Result Connection Is Successfull
        connectionMade = true;
        connectionStatus.setText("Connected to " + databaseUrl);
      //Output If There Was A Problem Registering The JDBC driver
      }catch(ClassNotFoundException e){
        //pass the error message to the display widget
        connectionStatus.setText("Failed to load JDBC driver: " + jdbcDriver);
        //pass the error message to the console
        System.out.println("Failed to load JDBC driver: " + jdbcDriver);
      //Output If There Was A Problem Connecting To The Database
      }catch (SQLException e) {
        //pass the error message to the display widget
        connectionStatus.setText("Failed to connect to database: " + databaseUrl);
        //pass the error message to the console
        System.out.println("Failed to connect to database: " + databaseUrl);
      }
      return connectionMade;
   } 

   //load the screen to connect to the database
   public Scene connectScreen(){
      //indicate that the connect screen is now open
      isConnectScreenClosed = false;

      //create the widgets to display the connection status
      Text connectionStatus = new Text();

      //create the widgets to allow the user to select the JDBC Driver they want to use
      Label jdbcDrive = new Label("JDBC Drive");
      ComboBox<String> jdbcDriveComboBox = new ComboBox<>();
      jdbcDriveComboBox.getItems().addAll("com.mysql.jdbc.Driver");
      //set the contents to editable to allow the user to add their own drivers
      jdbcDriveComboBox.setEditable(true);
      

      //create the widgets to allow the user to select the database URL they want to use
      Label databaseUrl = new Label("Database URL");
      ComboBox<String> databaseUrlComboBox = new ComboBox<>();
      databaseUrlComboBox.getItems().addAll("jdbc:mysql://localhost:3306/javabook");
      //set the contents to editable to allow the user to add their own drivers
      databaseUrlComboBox.setEditable(true);

      //create the widgets to store the username
      Label username = new Label("Username");
      TextField usernameEntry = new TextField("root");
      //create the widgets to store the password
      Label password = new Label("Password");
      TextField passwordEntry = new TextField();

      //create the buttons
      Button connectToDB = new Button("Connect to DB");
      connectToDB.setAlignment(Pos.TOP_RIGHT);
      Button closeDialog = new Button("Close Dialog");

      // Create the GridPane layout
      GridPane gridPane = new GridPane();
      gridPane.setAlignment(Pos.TOP_CENTER);
      gridPane.setHgap(10);
      gridPane.setVgap(10);

      // Add the Elements to the GridPane layout
      gridPane.add(connectionStatus,0,0,2,1);
      gridPane.add(jdbcDrive,0,1); 
      gridPane.add(jdbcDriveComboBox,1,1);
      gridPane.add(databaseUrl,0,2);
      gridPane.add(databaseUrlComboBox,1,2);
      gridPane.add(username,0,3);
      gridPane.add(usernameEntry,1,3);
      gridPane.add(password,0,4);
      gridPane.add(passwordEntry,1,4);
      gridPane.add(connectToDB,1,5);
      gridPane.add(closeDialog,0,6,2,1);

      //Align the connectToDB button to the Right of its cell
      GridPane.setHalignment(connectToDB, javafx.geometry.HPos.RIGHT);
      //Align the closeDialog button to the Center of its cell
      GridPane.setHalignment(closeDialog, javafx.geometry.HPos.CENTER);

      //Event Listeners
      closeDialog.setOnAction(event -> {
        Stage stage = (Stage) closeDialog.getScene().getWindow();
        //set the flag variable to indicate the connectScreen Page has been closed
        isConnectScreenClosed = true;
        //close the stage
        stage.close();
      });

      connectToDB.setOnAction(event ->{
        //get the values for the connection
        String jdbcDriverString = jdbcDriveComboBox.getValue();
        String databaseUrlString = databaseUrlComboBox.getValue();
        String usernameString = usernameEntry.getText();
        String passwordString = passwordEntry.getText();
        //attempt to connect to the database
        isConnectionEstablished = connectToDatabase(jdbcDriverString,databaseUrlString,usernameString,passwordString,connectionStatus);
      });

      // Add the GridPane to the Scene and show it
      Scene scene = new Scene(gridPane, 400, 250);
      return scene;
   }

   //Return The Exercise35_01 Stage
   public Scene homeScreen(){
      // Create the Label to store if the update succeeded
      Label updateStatus = new Label();
      // Create the Button to open the connection to the DB Connection window
      Button connectDatabaseBtn = new Button("Connect to Database");
      // Create the Label to display the results
      Label displayResults = new Label();
      // Configure the Labels custom dimensions
      displayResults.setPrefWidth(400);
      displayResults.setPrefHeight(100);
      BackgroundFill backgroundFill = new BackgroundFill(Color.WHITE, null, null);
      Background background = new Background(backgroundFill);
      displayResults.setBackground(background);
      displayResults.setStyle("-fx-border-color: black; -fx-border-width: 1px;");

      // Create the Button to test batch update
      Button batchUpdateBtn = new Button("Batch Update");
      // Create the Button to test non-batch update
      Button nonBatchUpdateBtn = new Button("Non-Batch Update");

      // Create the HBox to contain the batchUpdate and nonBatchUpdate buttons
      HBox buttonsHBox = new HBox(batchUpdateBtn, nonBatchUpdateBtn);
      buttonsHBox.setAlignment(Pos.CENTER);

      // Create the GridPane layout
      GridPane gridPane = new GridPane();
      gridPane.setAlignment(Pos.TOP_CENTER);
      gridPane.setHgap(10);
      gridPane.setVgap(10);

      // Add the child nodes to the GridPane
      gridPane.add(updateStatus, 0, 0);
      gridPane.add(connectDatabaseBtn, 1, 0);
      gridPane.add(displayResults, 0, 1, 2, 1);
      gridPane.add(buttonsHBox, 0, 2, 2, 1);

      //Align the connectDatabaseBtn left
      GridPane.setHalignment(connectDatabaseBtn, javafx.geometry.HPos.RIGHT);

      //Align the updateStatus label right
      GridPane.setHalignment(updateStatus, javafx.geometry.HPos.LEFT);

      //Add An Event Handler To Open The Connect To DB Window
      connectDatabaseBtn.setOnAction(event -> {
        //add a function here to return the connect to database stage
        //if the connection screen is not already open
        if (isConnectScreenClosed){
          //Create a new Stage object to represent the new window
          Stage databaseStage = new Stage();
          //Set the Scene to the content you want to display
          Scene databaseScene = connectScreen();
          databaseStage.setTitle("Connect to DB");
          
          //Alert the Program when the Stage is closed
          databaseStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            //Call The Close Dialog Function
            @Override
            public void handle(WindowEvent event){
              //set the flag to indicate the connect database screen is now closed
              isConnectScreenClosed = true;
            }
          }); 
          databaseStage.setScene(databaseScene);
          databaseStage.show();
        }
      });
      
      //Add An Event Handler To Run The Batch Update
      batchUpdateBtn.setOnAction(event ->{
        //If A Connection To The Database Is Established
        if (isConnectionEstablished){
          //attempt to batch update the database
          batchUpdateDatabase(updateStatus, displayResults);

        //Output Error Message If The Connection Has Not Been Established
        }else{
          updateStatus.setText("connect to a database first");
        }
      });

      //Add An Event Handler To Run The Non Batch Update
      nonBatchUpdateBtn.setOnAction(event ->{
        //If A Connection To The Database Is Established
        if (isConnectionEstablished){
          nonBatchUpdateDatabase(updateStatus, displayResults);
        }else{
          updateStatus.setText("connect to a database first");
        }
      });
      // Add the GridPane to the Scene and show it
      Scene scene = new Scene(gridPane, 400, 175);
      return scene;
   }  


   //Override The Start Method
   @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Exercise35_01");
        primaryStage.setScene(homeScreen());
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}




