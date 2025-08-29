/*
Author: Scott Field
Name: Access_Update_Table
Date: 09/27/2023
Version: 1.0
Purpose: 
Write a program that views, inserts, and updates staff
information stored in a database, as shown in Figure 34.27a. The View button displays a
record with a specified ID. The Insert button inserts a new record. The Update button
updates the record for the specified ID. 

Instructions:
The Staff table is created as follows:
create table Staff ( id char(9) not
null, lastName varchar(15),
firstName varchar(15),
 mi char(1), address varchar(20),
city varchar(20), state char(2),
telephone char(10), email
varchar(40), primary key (id) );
*/

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;

import java.util.LinkedHashMap;
import java.sql.*;

public class Main extends Application {  
   //Get The Database Data
   private final String DB_URL = "jdbc:mysql://localhost:3306/test";
   private final String USER = "root";
   private final String PASS = "";

   //Function to constrain the textFields to the length of the database characters
   public static void addLengthFormatter(TextField textField, int length) {
        //Create the text formatter
        TextFormatter<String> formatter = new TextFormatter<>(change -> {
            //get the text 
            String newText = change.getControlNewText();
            //check if the text contains 0 to length digits 
            if (newText.matches(".{0," + length + "}")) {
                //if so set the formatter to the changed text
                return change;
            } else {
                //if not set the formatter to not change the text
                return null;
            }
        });
        //set the textField by the formatter
        textField.setTextFormatter(formatter);
    }

   //Function to add the string within a textfield to a map and set the key equal to the column name (if the string is not empty or containing only spaces) 
   private LinkedHashMap<String,String> putData(LinkedHashMap<String,String> myMap, TextField myField, String myKey){
      String myText = myField.getText().trim();
      if (!myText.equals("")){
        //Add Single Quotes Since Every Variable In The Database Is A Character or Varchar
        myMap.put(myKey, "'"+ myText +"'");
      }
      return myMap;
   }

   //Add Any Valid Input (Not Empty Or Only String) To The HashMap From The TextFields (For The View Button)
   private LinkedHashMap<String,String> getInputMap(TextField idTextField){
      LinkedHashMap<String,String> myMap = new LinkedHashMap<String,String>();
      return putData(myMap,idTextField,"id");
   }

   //Add Any Valid Input (Not Empty Or Only String) To The HashMap From The TextFields (For The Insert Button)
   private LinkedHashMap<String,String> getInputMap(TextField idTextField, TextField lastNameTextField, TextField firstNameTextField, TextField miTextField, TextField addressTextField, TextField cityTextField, TextField stateTextField, TextField telephoneTextField){
      LinkedHashMap<String, String> dataMap = new LinkedHashMap<String, String>();
      
      //Check The TextFields For Valid Input, Then Add Any Valid Input, With Its Columns As Keys To The Map
      dataMap = putData(dataMap,idTextField,"id");
      dataMap = putData(dataMap,lastNameTextField,"lastName");
      dataMap = putData(dataMap,firstNameTextField,"firstName");
      dataMap = putData(dataMap,miTextField,"mi");
      dataMap = putData(dataMap,addressTextField,"address");
      dataMap = putData(dataMap,cityTextField,"city");
      dataMap = putData(dataMap,stateTextField,"state");
      dataMap = putData(dataMap,telephoneTextField,"telephone");
      
      return dataMap;
   }

   //Set The Clear Button Function
   private void clearFields(Text resultText, TextField...fields){
    //first clear the result Label
    resultText.setText("");
    //After taking any number of textFields
    for (TextField field: fields){
      //clear each one
      field.clear();
    }
   } 

   //Set The View Button Function
   private String viewOperation(LinkedHashMap<String,String> data) throws SQLException{
    //If the id is present
    if (data.get("id") != null){
      //Connect To The Database
      Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
      //Create The Statement
      Statement stmt = conn.createStatement();
      //Set The Default Query (Show Everything)
      String query = "SELECT * FROM Staff WHERE id =";
      //Set The String To Store The Result Of The Query
      String result = "";

      //Modify The Query By The Keys and Data in The Map
      query += data.get("id");

      //Execute The Query
      ResultSet viewResultSet = stmt.executeQuery(query);
      //If The Query Returns Something
      if (viewResultSet.next()){
        //Iterate Through The Results And Print Them

        // Get the column names from the ResultSet metadata
        ResultSetMetaData metaData = viewResultSet.getMetaData();
        int columnCount = metaData.getColumnCount();

        int[] columnWidths = new int[columnCount];
        String columnResult = "";
        String valueResult = "";

        //Calculate the width of each column and column header
        for (int i = 1; i <= columnCount; i++) {
          String value = viewResultSet.getString(i);
          String column = metaData.getColumnName(i);
          int valueWidth;
          int titleWidth;

          //get the width of the column title and value
          if (value == null){
            valueWidth = 4;
          }else{
            valueWidth = value.length();
          }
          titleWidth = column.length();

          //set whichever is longer as the width of the column
          if (valueWidth >= titleWidth){
            columnWidths[i-1] = valueWidth;
            //add the difference in spaces to the title string
            
          }else{
            columnWidths[i-1] = titleWidth;
          }
        }

        //Set The Column Headers By The Width Of The Data
        for (int i = 1; i <= columnCount; i++) {
          columnResult += String.format("%-" + (columnWidths[i - 1] + 2) + "s", metaData.getColumnName(i));
        }
        
        //Get a string of spaces equal to the length of the columns
        valueResult = String.format("%" + (columnResult.length()) + "s", "");
        //Insert each value at the point where the columns begin.
        String[] columns = {"id","lastName","firstName","mi","address","city","state","telephone","email"};
        for (int i = 1; i <= columnCount; i++) {
          String value = viewResultSet.getString(i);
          int insertPoint = columnResult.indexOf(columns[i-1]);
          valueResult = valueResult.substring(0,insertPoint) + value + valueResult.substring(insertPoint);
        }

        //set the resulting rows
        result = columnResult + "\n" + valueResult;
        
        //close the connection
        conn.close();
        //finally return the result of the query
        return result;
      //if nothing is found  
      }else{
        conn.close();
        return "Record not found";
      }
    //if no id is provided  
    }else{
      //return an error message
      return "id must be specified";
    }
   }

   //Set The Insert Button Function
   private String insertOperation(LinkedHashMap<String,String> data) throws SQLException{
    //If the id is present
    if (data.get("id") != null){
      //Connect To The Database
      Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
      //Create The Statement
      Statement stmt = conn.createStatement();
      //Set The PlaceHolder Query (:columns) and (:strings) will be replaced
      String query = "INSERT INTO Staff (:columns) VALUES (:strings) ";

      //Get The Columns And Values From The Map
      String[] columns = data.keySet().toArray(new String[data.size()]);
      String[] values = data.values().toArray(new String[data.size()]);

      //Add commas between all of the different values
      String insertColumns = String.join(",",columns);
      String insertValues  = String.join(",",values);
      
      //replace the placeholder strings with the actual values
      query = query.replace(":columns",insertColumns);
      query = query.replace(":strings",insertValues);
      
      //Execute The Query
      int rowsAffected = stmt.executeUpdate(query);
      conn.close();
      return  Integer.toString(rowsAffected) + " rows affected";
      
    //if no id is provided  
    }else{
      //return an error message
      return "id must be specified";
    }
   }
   
   //Set The Update Button Function
   private String updateOperation(LinkedHashMap<String,String> data) throws SQLException{
    //If the id is present
    if (data.get("id") != null){
      //if their is anything to update (Then the map will contain more than simply the id)
      if (data.size() > 1){
        //Connect To The Database
        Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
        //Create The Statement
        Statement stmt = conn.createStatement();
        //Set The PlaceHolder Query (:columns) and (:strings) will be replaced
        String query = "UPDATE Staff SET :column=value Where ID = :id";

        //Get The Columns And Values From The Map
        String[] columns = data.keySet().toArray(new String[data.size()]);
        String[] values = data.values().toArray(new String[data.size()]);
        String[] columnValueArray = new String[values.length - 1];

        //convert the columns and values to a column = value list
        for (int i = 0; i < columnValueArray.length; i++){
          //Uses index + 1 to skip the id key as it will not be updated
          columnValueArray[i] = columns[i + 1] + "=" + values[i + 1];
        }
        
        //set the strings to be used in the query
        String idString = data.get("id");
        String columnValueString = String.join(",",columnValueArray);

        //replace the placeholder strings with the actual values
        query = query.replace(":column=value",columnValueString);
        query = query.replace(":id",idString);
        
        //Execute The Query
        int rowsAffected = stmt.executeUpdate(query);
        conn.close();
        return  Integer.toString(rowsAffected) + " rows affected";
      }
      else{
        return "must specify which columns to update";
      }
    //if no id is provided  
    }else{
      //return an error message
      return "id must be specified";
    }
   }
   

   //Override The Start Method
   @Override 
   public void start(Stage primaryStage) {   
        //Set The Grid 
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(0, 0, 0, 0));

        //Create The Text To Store The Results Of The Operation
        Text resultText = new Text();
        grid.add(resultText, 0, 0,6,1);
        //Change The Font To One With Even Spacing
        Font font = Font.font("Monospaced", FontWeight.NORMAL, FontPosture.REGULAR, 12);
        resultText.setFont(font);

        //Create The Label and Input for the ID Field in the Table
        Label idLabel = new Label("ID:");
        grid.add(idLabel, 0, 1);
        TextField idTextField = new TextField();
        //constrain the input to 9 characters in length at max
        addLengthFormatter(idTextField, 9);
        grid.add(idTextField, 1, 1);

        //Create The Label and Input for the Last Name Field in the Table
        Label lastNameLabel = new Label("Last Name:");
        grid.add(lastNameLabel, 0, 2);
        TextField lastNameTextField = new TextField();
        //constrain the input to 15 characters in length at max
        addLengthFormatter(lastNameTextField, 15);
        grid.add(lastNameTextField, 1, 2);

        //Create The Label and Input for the First Name Field in the Table
        Label firstNameLabel = new Label("First Name:");
        grid.add(firstNameLabel, 2, 2);
        TextField firstNameTextField = new TextField();
        //constrain the input to 15 characters in length at max
        addLengthFormatter(firstNameTextField, 15);
        grid.add(firstNameTextField, 3, 2);

        //Create The Label and Input For The Middle Initial Field in the Table
        Label miLabel = new Label("Middle Initial:");
        grid.add(miLabel, 4, 2);
        TextField miTextField = new TextField();
        //constrain the input to 1 character in length at max
        addLengthFormatter(miTextField, 1);
        grid.add(miTextField, 5, 2);

        //Create The Label and Input For The Address Field in the Table
        Label addressLabel = new Label("Address:");
        grid.add(addressLabel, 0, 3);
        TextField addressTextField = new TextField();
        //constrain the input to 20 characters in length at max
        addLengthFormatter(addressTextField, 20);
        grid.add(addressTextField, 1, 3);

        //Create The Label and Input For The City Field in the Table
        Label cityLabel = new Label("City:");
        grid.add(cityLabel, 0, 4);
        TextField cityTextField = new TextField();
        //constrain the input to 20 characters in length at max
        addLengthFormatter(cityTextField, 20);
        grid.add(cityTextField, 1, 4);

        //Create The Label and Input For The State Field in the Table
        Label stateLabel = new Label("State:");
        grid.add(stateLabel, 2, 4);
        TextField stateTextField = new TextField();
        //constrain the input to 2 characters at max
        addLengthFormatter(stateTextField, 2);
        grid.add(stateTextField, 3, 4);

        //Create The Label and Input For The Telephone Field in the Table
        Label telephoneLabel = new Label("Telephone:");
        grid.add(telephoneLabel, 0, 5);
        TextField telephoneTextField = new TextField();
        //constrain the input to 10 characters at max
        addLengthFormatter(telephoneTextField, 10);
        grid.add(telephoneTextField, 1, 5);

        //Create The Buttons To Perform The Operations
        Button viewButton = new Button("View");
        Button insertButton = new Button("Insert");
        Button updateButton = new Button("Update");
        Button clearButton = new Button("Clear");

        //Create The Hbox To Store The Buttons
        HBox hbox = new HBox(10);
        hbox.setAlignment(Pos.CENTER);
        hbox.getChildren().addAll(viewButton, insertButton, updateButton, clearButton);
        grid.add(hbox, 3, 6);

        //Add The Event Listener To The Buttons
        clearButton.setOnAction(e ->
            clearFields(resultText,idTextField,lastNameTextField,firstNameTextField,miTextField,addressTextField,cityTextField,stateTextField,telephoneTextField)
        );

        viewButton.setOnAction(e-> {
          try{
            LinkedHashMap <String,String> data = getInputMap(idTextField);
            resultText.setText(viewOperation(data));
          }catch(SQLException error){
            error.printStackTrace();
          }
        });
        
        insertButton.setOnAction(e-> {
          try{
            LinkedHashMap <String,String> data = getInputMap(idTextField,lastNameTextField,firstNameTextField,miTextField,addressTextField,cityTextField,stateTextField,telephoneTextField);
            resultText.setText(insertOperation(data));
          }catch(SQLException error){
            resultText.setText("Error duplicate ID: " + idTextField.getText());
            error.printStackTrace();
          }
        });

        updateButton.setOnAction(e-> {
          try{
            LinkedHashMap <String,String> data = getInputMap(idTextField,lastNameTextField,firstNameTextField,miTextField,addressTextField,cityTextField,stateTextField,telephoneTextField);
            resultText.setText(updateOperation(data));
          }catch(SQLException error){
            resultText.setText("Error ID: " + idTextField.getText() + " not present");
            error.printStackTrace();
          }
        });
        

        //Add The Grid To The Scene And Show It
        Scene scene = new Scene(grid, 800, 325);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
  
}

