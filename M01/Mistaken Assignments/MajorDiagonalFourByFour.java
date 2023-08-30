/*
Author: Scott Field
Name: MajorDiagonalFourByFour
Version: 1.0
Purpose: Using the method sumMajorDiagonal method find
the sum of the Major Diagonal
*/

import java.util.Scanner;

public class MajorDiagonalFourByFour {

    //Find The Sum Of The Major Diagonal
    public static double sumMajorDiagonal(double[][] m){
       //Set Data Size
       int size = 4; 
       //Store Current Sum
       double sum = 0;
    
       //Add [0][0] + [1][1] + [2][2] + [3][3] To Add The Major Diagonal
       for (int index = 0; index < size; index++){
         sum += m[index][index];
       }

       //Return Value
       return sum;
    }

    public static void main(String[] args){
        //Define Variables
        int size = 4;
        //Define Table (4 x 4)
        double[][] table = new double[size][size];
        
        //Store User Input
        Scanner scanner = new Scanner(System.in);
        double input;

        //Welcome Message
        System.out.println("This program will output the sum of the Major Diagonal of a 4 x 4 table.");
        System.out.println("Please Enter A Table Row By Row With 4 Numbers In Each Row Separated By Spaces\n:");
       
        
        //Get User Input Row By Row
        for (int outerIndex = 0; outerIndex < size; outerIndex++){
            //Iterate Acroos Each Number In The Row
            for (int innerIndex = 0; innerIndex < size; innerIndex++){
                //Get The Number
                input = scanner.nextDouble();
                //Add It To The Table
                table[outerIndex][innerIndex] = input;
            }
        }

        //close scanner
        scanner.close();

        //output the major diagonal
        System.out.println("The sum of the major diagonal is: " + sumMajorDiagonal(table));

    }
}
