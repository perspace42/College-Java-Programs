/*
Author: Scott Field
Date: 09/12/2023
Version: 1.0
Instructions:
Write a program to check whether a Java source-code file has correct pairs of grouping symbols. 
Pass the source-code file name as a command-line argument
*/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Stack;
import java.util.Scanner;

public class CheckJavaFile {

    public static void checkGroupSymbols(File javaFile, String javaFileName) throws FileNotFoundException{
        //Initialize Source File Loop Variables
        Stack<Character> groupStack = new Stack<>(); //For checking if closing symbols match opening symbols
        String line = "";
        boolean misMatchFound = false;

        //Initialize the scanner to read the file
        Scanner scan = new Scanner(javaFile); 
        
        //While their are any lines to be scanned
        while (scan.hasNextLine() && misMatchFound == false){
            //get the line
            line = scan.nextLine();
            
            //For each character in the line check for () {} [] to determine if the grouping symbols are correct
            for (char character : line.toCharArray()){
                //If an opening grouping symbol is found
                if (character == '(' || character == '{' || character == '['){
                    //push it to the stack
                    groupStack.push(character);
                }

                //If a closing symbol is found check if the opening symbol is in the stack, if not their has been an incorrect pairing
                else if (character == ')'){
                    //remove the opening symbol from the stack, check it, then if it doesn't match (or if there isn't an opening symbol) exit the program 
                    if (groupStack.isEmpty() || groupStack.pop() != '('){
                        //output the message
                        System.out.println(javaFileName + " grouping symbols are incorrect, missmatch found near: " + character);
                        //exit the loop
                        misMatchFound = true;
                        break;
                    }
                }
                else if (character == '}'){
                    //remove the opening symbol from the stack, check it, then if it doesn't match (or if there isn't an opening symbol) exit the program 
                    if (groupStack.isEmpty() || groupStack.pop() != '{'){
                        //output the message
                        System.out.println(javaFileName + " grouping symbols are incorrect, missmatch found near: " + character);
                        //exit loop
                        misMatchFound = true;
                        break;
                    }
                }
                else if (character == ']'){
                    //remove the opening symbol from the stack, check it, then if it doesn't match (or if there isn't an opening symbol) exit the program 
                    if (groupStack.isEmpty() || groupStack.pop() != '['){
                        //output the message
                        System.out.println(javaFileName + " grouping symbols are incorrect, missmatch found near: " + character);
                        //exit the loop
                        misMatchFound = true;
                        break;
                    }
                }   
            }
        }
        //close scanner when it is no longer needed
        scan.close();

        //if a mismatch hasn't been found, check for further mismatches
        if (misMatchFound == false){

            //if the stack isn't empty then the file contains opening group symbols without any closing group symbols (Therefore grouping symbols are incorrect)
            if (groupStack.isEmpty() == false){
                //Since the stack removes an opening symbol when it finds a correct closing symbol, the first element in the stack is the first one missing a closing symbol
                System.out.println(javaFileName + " grouping symbols are incorrect, mismatched: " + groupStack.firstElement() + " found.");
            
            //Otherwise the group symbols within the file is correct.
            }else{
                System.out.println("The grouping symbols within " + javaFileName + " are correct");
            }

        }
    } 

    public static void main(String[] args) throws FileNotFoundException{
        //Define Input Variables
        File javaFile = new File("");
        String javaFileName = "";
       
        //Get The File Name From The Command Line
        try{
            javaFileName = args[0];
        //If a File is not provided instruct the user to provide one
        }catch(Exception e){
            System.out.println("Error: A Java source code file path must be provided using the command line for the program to evaluate its group symbols, please try again");
            System.exit(1);
        }

        //Check if the File Name ends with the .java extension
        if (javaFileName.endsWith(".java") == false){
            //If not output the error message and ask the user to input again
            System.out.println("Error: Incorrect file type, The program requires a Java source code file path to evaluate its group symbols, please try again");
            //Exit the program
            System.exit(1);
        }

        //Attempt To Get The File From The File Path
        javaFile = new File(javaFileName);
        if (javaFile.exists() == false){
            System.out.println("Error: The Java source code file path: " + javaFileName + " is invalid, The program requires a valid Java source code file path to evaluate its group symbols, please try again");  
            //Exit the program
            System.exit(1);
        }
        
        //Check The File
        checkGroupSymbols(javaFile,javaFileName);
    }
}

