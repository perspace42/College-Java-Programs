/*
Author: Scott Field
Name: CreditCardValid
Version: 1.0
Purpose: Using a long integer or a String input from the user display whether the entered
number is valid as a credit card number using the Mod 10 check.
*/

import java.util.Scanner;

public class CreditCardValid {
    // Return true if the card number is valid
    public static boolean isValid(String cardNumber){
        if (prefixMatched(cardNumber,cardNumber.charAt(0))){
            int total = sumOfDoubleEvenPlace(cardNumber) + sumOfOddPlace(cardNumber);
            if (total % 10 == 0){
                return true;
            }
        }
        return false;
    }

    // Get the result from Step 2 
    public static int sumOfDoubleEvenPlace(String number){
        int total = 0;
        String currentString;
        char currentChar;
        int currentInt;
        int index = 0;

        //Current For Loop Logic
        //Execute while getPrefix does not return the original string
        while(((getPrefix(number,index)).equals(number)) != true) {
            currentString = getPrefix(number,index);
            currentChar = currentString.charAt(0);
            //convert to integer
            currentInt = Character.getNumericValue(currentChar);
            //multiply the integer by two
            currentInt *= 2;
            //if the integer has two digits add those digits together to create a single digit number (which is then assigned to the current digit)
            currentInt = getDigit(currentInt);
            //add the current integer to the step one numbers
            total += currentInt;
            //increment the index
            index += 2;
        }

        return total;
    }

    // Return this number if it is a single digit, otherwise, return the sum of the two digits
    public static int getDigit(int number){
        int onesDigit;
        int tensDigit;
        if (number >= 10){
            onesDigit = number % 10;
            tensDigit = number / 10;
            //set number equal to the 10s place plus the 1s place
            number = onesDigit + tensDigit;
        }
        return number;
    }

    // Return sum of odd-place digits in number
    public static int sumOfOddPlace(String number){
        int total = 0;
        String currentString;
        char currentChar;
        int currentInt;
        int index = 1;
        
        //Current For Loop Logic
        while (((getPrefix(number,index)).equals(number)) != true){
            currentString = getPrefix(number,index);
            //get the character
            currentChar = currentString.charAt(0);
            //convert to integer
            currentInt = Character.getNumericValue(currentChar);
            //add the integer to the step three numbers
            total += currentInt;
            //increment the index
            index += 2;
        }
        return total;
    }

    
    //test if the first digit is an acceptable prefix
    //Acceptable prefixes are 37 , 4 , 5 , 6
    public static boolean prefixMatched(String number, char d){
        if ((d == '3' && number.charAt(1) == '7') || d == '4' || d == '5' || d == '6' ){
            return true;
        }
        return false;
    }

    //return the requested character at position k if it is within the String otherwiser return the full string
    public static String getPrefix(String number, int k){
        //if the requested character at position k is outside of the range
        if (k >= getSize(number)){
            //return the full number
            return number;
        }
        //otherwise return the requested character at position k (As a String)
        return number.substring(k,k+1);
    }

    //return the size of the String
    public static int getSize(String number){
        return number.length();
    }


    public static void main(String[] args){
        //Create Scanner    
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter The Credit Card Number And The Program Will Validate It\n:");
        String cardString =  scanner.nextLine();
        //close the scanner
        scanner.close();

        //Check if the credit card number is valid
        if (isValid(cardString)){
            System.out.println(cardString +" is valid");
        }else{
            System.out.println(cardString + " is invalid");
        }
    }
}
