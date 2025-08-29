/*
Author: Scott Field
Name: Triangle Class Test
Version: 1.0
Directions:
    Design a class named Triangle that extends ­GeometricObject. The class contains:

    Three double data fields named side1, side2, and side3 with default values 1.0 to denote three sides of a triangle.

    A no-arg constructor that creates a default triangle.

    A constructor that creates a triangle with the specified side1, side2, and side3.

    The accessor methods for all three data fields.

    A method named getArea() that returns the area of this triangle.

    A method named getPerimeter() that returns the perimeter of this triangle.

    A method named toString() that returns a string description for the triangle.
Instructions:
    Write a test program that prompts the user to enter three sides of the triangle, a color, and a Boolean value to indicate whether the triangle is filled. 
    
    The program should create a Triangle object with these sides and set the color and filled properties using the input. 
    
    The program should display the area, perimeter, color, and true or false to indicate whether it is filled or not.
 */

import java.lang.Math;
import java.util.Scanner;

public class Triangle extends GeometricObject {
    private double side1;
    private double side2;
    private double side3;

    //Set Triangle To Default Values
    public Triangle(){
        this.side1 = 1.0;
        this.side2 = 1.0;
        this.side3 = 1.0;
    }

    //Set Triangle To Given Values
    public Triangle(double newSide1, double newSide2, double newSide3){
        this.side1 = newSide1;
        this.side2 = newSide2;
        this.side3 = newSide3;
    }

    //Accessor Methods

    //Get And Set Side One
    public double getSide1(){
        return this.side1;
    }

    public void setSide1(double newSide1){
        this.side1 = newSide1;
    }

    //Get And Set Side Two
    public double getSide2(){
        return this.side2;
    }

    public void setSide2(double newSide2){
        this.side2 = newSide2;
    }

    //Get And Set Side 3
    public double getSide3(){
        return this.side3;
    }

    public void setSide3(double newSide3){
        this.side3 = newSide3;
    }

    public double getArea() {
       //get s for the formula
       double s = (side1 + side2 + side3) / 2;
       double area = Math.sqrt(s * (s - side1) * (s - side2) * (s - side3));
       return area;
    }

    //return perimeter
    public double getPerimeter() {
        return side1 + side2 + side3;
    }

    //return a description of the Triangle
    public String toString(){
        return "Triangle:\nside1 = " + side1 + "\nside2 = " + side2 + "\nside3 = " + side3 + "\ncolor = " + this.getColor() + "\nfilled = " + this.isFilled()
        + "\nArea: " + this.getArea() + "\nPerimeter: " + this.getPerimeter();
    }

    //Test Program
    public static void main(String[] args){
        //Welcome Message
        System.out.println("Please Enter The Three Sides Of A Triangle Then The Program Will Dislay That Triangles:\n area, perimeter, color, and true or false to indicate whether it is filled");

        //Input Validation Loop
        Scanner scan = new Scanner(System.in);
        boolean isValid; //Store If Input Has Been Validated
        String inputString; //Store The Inputed String
        String color; //Stor The Triangle Color
        double inputList[] = new double[3]; //Store The Inputed Sides Converted To Doubles
        boolean isFilled = true; //must be initialized to avoid error


        //Get Input For All 3 Sides
        for (int sideNumber = 0; sideNumber < 3; sideNumber++){
            isValid = false;
            while (isValid == false){
                System.out.println("Enter the length of side: " + (sideNumber + 1));
                inputString = scan.nextLine();
                
                //Attempt To Convert The Inputed String To A Double
                try{
                    inputList[sideNumber] = Double.parseDouble(inputString);
                    isValid = true; //Assume Input Is Correct Unless Exception Is Thrown

                //If There Is A Problem Output An Error Message And Continue The loop
                }catch(NumberFormatException exception){
                    System.out.println(inputString + " is not a valid number, this loop will continue until a valid number is entered");
                    isValid = false; //If An Exception Occurs Input Is Not Valid
                }
            }
        }
        
        //Get Input For Color (As new colors are made on a consistent basis their was no simple way to create a validation loop)
        System.out.println("Please Enter The Color Of The Triangle: ");
        color = scan.nextLine(); 

        //Input Validation Loop For (Filled / Not Filled Triangle)
        isValid = false;
        while (isValid == false){
            //Get Input For Filled Or Not Filled
            System.out.println("Enter true, to indicate that the triangle is filled or Enter false, to indicate that the triangle is not filled");
            inputString = scan.nextLine();

            //If The User Indicates That The Triangle Should Be Filled
            if (inputString.toLowerCase().equals("true")){
                isFilled = true;
                isValid = true;
            
            //If The User Indicates That The Triangle Shouldn't Be Filled
            }else if (inputString.toLowerCase().equals("false")){
                isFilled = false;
                isValid = true;
            
            //If The Input Is not 'true' or 'false' output an error message
            }else{
                System.out.println("The input: " + inputString + " is not true or false input must be either true or false to indicate filled or unfilled.");
            }
        }

        //close scanner
        scan.close();

        //Declare Triangle From User Input
        Triangle myTriangle = new Triangle(inputList[0],inputList[1],inputList[2]);
        myTriangle.setFilled(isFilled);
        myTriangle.setColor(color);

        //Output The Triangle Values
        System.out.println("\n" + myTriangle);
        
    }
    
}
