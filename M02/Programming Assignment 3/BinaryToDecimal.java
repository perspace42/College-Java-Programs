/*
Author: Scott Field
Name: BinaryToDecimal Exception Test
Version: 1.0
Directions: 
    Define a custom exception called BinaryFormatException. Implement the bin2Dec method to throw a BinaryFormatException if the string is not a binary string.
*/

import java.lang.Math;

public class BinaryToDecimal{

    //Define Custom Exception
    public class BinaryFormatException extends Exception {
        public BinaryFormatException(String binaryString) {
            super(binaryString + " Is Not A Binary String");
        }
    }

    //Convert Binary To Decimal
    public static int bin2Dec(String binaryString){
        //initialize number
        int number = 0;
        int currentNumber = 0;

        //try catch block
        try {
            //If The String Does Not Consist Only Of Zeros and/or Ones
            if (!binaryString.matches("[01]+|0+|1+")){
                //Throw The Exception
                throw new BinaryToDecimal().new BinaryFormatException(binaryString);
            }

        //Output The Error Message From The Exception
        }catch (BinaryFormatException exception){
            //Error Message To Console
            System.out.println(exception.getMessage());
            //Exit The Program
            System.exit(1);
        }

        //Otherwise Convert The Binary String To A Base 10 Number 
        for (int i = 0; i < binaryString.length(); i++) {
            int currentDigit = Character.getNumericValue(binaryString.charAt(i));
            //Formula To Convert Binary Digits To A Base 10 Number
            currentNumber = (int) ((currentDigit) * (Math.pow(2, i)));
            number += currentNumber;

            //System.out.println("currentDigit: " + currentDigit + " * " + "2^" + i + " = " + currentNumber); //line for testing each iteration
        }

        return number;
    }

    //test binary to base 10 integer function
    public static void main(String[] args) throws BinaryFormatException{
        //Valid Binary Function
        System.out.println("001 to binary = " + bin2Dec("001"));
        System.out.print("Strawberry is not a binary number and throws the exception: ");
        System.out.print(bin2Dec("Strawberry"));
    }

}