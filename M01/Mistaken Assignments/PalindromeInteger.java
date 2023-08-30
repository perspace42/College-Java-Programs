/*
Author: Scott Field
Name: PalindromeInteger
Version: 1.0
Purpose: Using the methods reverse and isPalindrome determine
if an entered integer by the user is a Palindrome
*/

//import libraries
import java.util.Scanner;

public class PalindromeInteger {
    
    // Return the reversal of an integer, e.g., reverse(456) returns 654
    public static int reverse(int number){
        //Store Reversed Number
        int reversedNum = 0;
        //Store Digit;
        int currentDigit = 0;
        //Iterate Across Number (Divide By 10 After Each Iteration To Remove The Furthest Right Digit After It Is Used)
        for(;number != 0; number /= 10){
            //Find Current Digit Using Number % 10 To Find The Furthest Right Digit
            currentDigit = number % 10;
            //Add Digit Back To The Number By Multiplying Current Reversed Number By 10 And Then Adding The Furthest Right Digit
            reversedNum = reversedNum * 10 + currentDigit;
        }
        return reversedNum;
    }

    // Return true if number is a palindrome
    public static boolean isPalindrome(int number){
        return (number == reverse(number));
    }

    public static void main(String[] args){
        //Define Variables

        //Get User Input
        Scanner scanner = new Scanner(System.in);
        //Store User Input
        int inputNumber;

        //Welcome Message
        System.out.println("Please Enter An Integer And The Program Will Output Whether Or Not It Is A Palindrome\n:");
        inputNumber = scanner.nextInt();
        
        //Find If The Inputed Number Is A Palindrome
        if (isPalindrome(inputNumber)){
            System.out.println("The number: " + inputNumber + " is a Palindrome.");
        }else{
            System.out.println("The number: " + inputNumber + " is not a Palindrome." );
        }
        
        //Close Scanner
        scanner.close();
        
    }
}
