/*
Author: Scott Field
Date: 09/12/2023
Version: 1.0
Purpose: 
Revise the program in Listing 21.7. (Create a program that counts the number of Java Keywords in the program)
If a keyword is in a comment or in a string, don’t count it. 
Pass the Java file name from the command line. Assume the Java source code is correct and line comments and paragraph comments do not overlap.
*/

import java.util.*;
import java.io.*;

public class CountKeyWords{
public static void main(String[] args) throws Exception {
        //Input from command line
        String filename = "invalid file name";
        
        //Set the file equal to the name given from the command line 
        try{
            filename = args[0];
        }catch(Exception e){
            System.out.println("Error no java file was provided as an argument in the command line, please provide one");
            System.exit(1);
        }
        
        File file = new File(filename);
        if (file.exists()) {
        System.out.println("The number of keywords in " + filename
            + " is " + countKeywords(file));
        }
        else {
            System.out.println("File " + filename + " does not exist");
        }
    }

public static int countKeywords(File file) throws Exception {
    // Array of all Java keywords + true, false and null
    String[] keywordString = 
        {
            "abstract", "assert", "boolean",
            "break", "byte", "case", "catch", "char", "class", "const",
            "continue", "default", "do", "double", "else", "enum",
            "extends", "for", "final", "finally", "float", "goto",
            "if", "implements", "import", "instanceof", "int",
            "interface", "long", "native", "new", "package", "private",
            "protected", "public", "return", "short", "static",
            "strictfp", "super", "switch", "synchronized", "this",
            "throw", "throws", "transient", "try", "void", "volatile",
            "while", "true", "false", "null"
        };
    

    Set<String> keywordSet = new HashSet<>(Arrays.asList(keywordString));
    int count = 0;
    //boolean to track if line is a multi line comment
    boolean inComment = false;
    //boolean to track if line is a string
    boolean inString = false;

    Scanner input = new Scanner(file);
    
    while (input.hasNextLine()){
        String line = input.nextLine();

        //if the line is empty skip it
        if (line.isEmpty() == true){
            continue;
        }

        //If the line is within a multi line comment
        if (inComment){
            //check for the */ character to signify if the multi line comment has ended
            if (line.contains("*/")){
                //if so you are no longer in a comment
                inComment = false;
                //set the line equal to everything after the end of the multi line comment on that line (if anything)
                if (line.endsWith("*/") == false){
                    line = line.substring(line.indexOf("*/") + 2);
                }
            //If The comment hasn't ended
            }else{
                //continue skipping lines until the end of the multi line comment is reached
                continue;
            }
        
        //If the preceding line is not within a unclosed multi line comment check to see if this one is
        }else{
            //check if the line is within a single line comment
            
            //if the line starts with a single line comment, skip it
            if (line.startsWith("//")){
                continue;
            
            //otherwise set the line equal to everything before the start of the single line comment
            }else if (line.contains("//")){
                line = line.substring(0,line.indexOf("//") - 1);
            }

            //check if the line starts a new multi line comment

            //if it does, and it doesn't end that comment on the same line, indicate the next line is a comment, and remove any commented text on the current line
            if (line.contains("/*") && (line.contains("*/") == false)){
                //set the line equal to everything before the start of the multi line comment
                line = line.substring(0,line.indexOf("/*") - 1);
                //indicate the next line is within the comment
                inComment = true;
                continue;
            
            //otherwise remove the comment from the line
            }else{
                //for each multi line comment on the single line
                while (line.contains("/*") && line.contains("*/")){
                    //replace the multi line comment with an empty space
                    line = line.replace(line.substring((line.indexOf("/*")) , (line.indexOf("*/") + 2)) , "");
                }
            }
        }

        //Split the remaining line into words (by tabs and spaces)
        ArrayList<String> wordsList = new ArrayList<String>(Arrays.asList(line.split("\\s+")));
        //Remove any empty or whitespace Strings from the contents
        wordsList.removeIf(str -> str.trim().isEmpty());
        //Set variable ot store each word in the list
        String currentWord = "";    
        //If the line contains a string or strings, remove each string from the line
        for(int i = 0; i < wordsList.size(); i++){
            //get the current word
            currentWord = wordsList.get(i);

            //if the word is within a string
            if (inString){
                //System.out.println("Iteration " + i + " removed " + currentWord); //for debugging

                //remove the word (by setting it to an empty string)
                //wordsList.set(i,"");
                wordsList.remove(i);
                //decrement the index to account for the smaller list
                i--;
                //if the word contains a second " then the end of the string has been reached
                if (currentWord.contains("\"")){
                    inString = false;
                }
            }else if (currentWord.contains("\"")){ 
                //System.out.println("Iteration " + i + " removed " + currentWord); //for debugging
                //if the word contains with " then it is within a string
                inString = true;
                //remove the first word containg the "
                wordsList.remove(i);
                //decrement the index to account for the smaller list
                i--;
            }
        }
        
        //check each word, adding 1 to the count for each keyword found
        for (String word: wordsList){
            if (keywordSet.contains(word)){
            System.out.println("Program contains: " + word); //added for debugging
            count++;
            }
        }

    }
    //close scanner
    input.close();
    //return result;
    return count;
  }
}


