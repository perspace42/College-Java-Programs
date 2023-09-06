/*
Author: Scott Field
Date: 09/05/2023
Purpose:
Convert the Rational Class To Use the BigInteger for numerator and denominator Instead Of Integers
Instructions:
Write a test program that prompts the user to enter two rational Numbers and display the results as shown

Enter the first rational number: 3 454 
Enter the second rational number: 7 2389 
3/454 + 7/2389 = 10345/1084606
3/454 – 7/2389 = 3989/1084606
3/454 * 7/2389 = 21/1084606
3/454 / 7/2389 = 7167/3178
7/2389 is 0.0029300962745918793

*/

import java.math.BigInteger;
import java.util.Scanner;

public class Rational extends Number implements Comparable<Rational> {
    // Data fields for numerator and denominator
    private BigInteger numerator =  BigInteger.ZERO;
    private BigInteger denominator = BigInteger.ONE;

    /** Construct a rational with default properties */
    public Rational() {
      this(BigInteger.ZERO, BigInteger.ONE);
    }

    /** Construct a rational with specified numerator and denominator */
    public Rational(BigInteger numerator, BigInteger denominator) {
      BigInteger gcd = gcd(numerator, denominator);
      this.numerator = (  (denominator.compareTo(BigInteger.ZERO) > 0 ? BigInteger.ONE  : BigInteger.valueOf(-1)).multiply(numerator) ).divide(gcd);
      this.denominator = denominator.abs().divide(gcd);
    }

    /** Find GCD of two numbers */
    private static BigInteger gcd(BigInteger n, BigInteger d) {
      BigInteger n1 = n.abs();
      BigInteger n2 = d.abs();
      BigInteger gcd = BigInteger.ONE;
      
      //Cov
      for (BigInteger k = BigInteger.ONE; k.compareTo(n1) <= 0 && k.compareTo(n2) <= 0; k = k.add(BigInteger.ONE)) {

       if (n1.mod(k).equals(BigInteger.ZERO) && n2.mod(k).equals(BigInteger.ZERO))
         gcd = k;
      }

      return gcd;
    }

    /** Return numerator */
    public BigInteger getNumerator() {
      return numerator;
    }

    /** Return denominator */
    public BigInteger getDenominator() {
      return denominator;
    }

    /** Add a rational number to this rational */
    public Rational add(Rational secondRational) {
      BigInteger n = (numerator.multiply(secondRational.getDenominator())).add(denominator.multiply(secondRational.getNumerator()));
      BigInteger d = denominator.multiply(secondRational.getDenominator());
      return new Rational(n, d);
    }

    /** Subtract a rational number from this rational */
    public Rational subtract(Rational secondRational) {
      BigInteger n = (numerator.multiply(secondRational.getDenominator())).subtract(denominator.multiply(secondRational.getNumerator()));
      BigInteger d = denominator.multiply(secondRational.getDenominator());
      return new Rational(n, d);
    }

    /** Multiply a rational number by this rational */
    public Rational multiply(Rational secondRational) {
      BigInteger n = numerator.multiply(secondRational.getNumerator());
      BigInteger d = denominator.multiply(secondRational.getDenominator());
      return new Rational(n, d);
    }

    /** Divide a rational number by this rational */
    public Rational divide(Rational secondRational) {
      BigInteger n = numerator.multiply(secondRational.getDenominator());
      BigInteger d = denominator.multiply(secondRational.getNumerator());
      return new Rational(n, d);
    }

    @Override
    public String toString() {
      if (denominator.equals(BigInteger.ONE))
        return numerator + "";
      else
        return numerator + "/" + denominator;
    }

    @Override // Override the equals method in the Object class
    public boolean equals(Object other) {
      if ((this.subtract((Rational)(other))).getNumerator().equals(BigInteger.ZERO))
        return true;
      else
        return false;
    }

    @Override // Implement the abstract intValue method in Number
    public int intValue() {
      return (int)doubleValue();
    }

    @Override // Implement the abstract floatValue method in Number
    public float floatValue() {
      return (float)doubleValue();
    }

    @Override // Implement the doubleValue method in Number
    public double doubleValue() {
      return numerator.doubleValue() *  1.0 / denominator.doubleValue();
    }

    @Override // Implement the abstract longValue method in Number
    public long longValue() {
      return (long)doubleValue();
    }

    @Override // Implement the compareTo method in Comparable
    public int compareTo(Rational o) {
      if (this.subtract(o).getNumerator().compareTo(BigInteger.ZERO) > 0)
        return 1;
      else if (this.subtract(o).getNumerator().compareTo(BigInteger.ZERO) < 0)
        return -1;
      else
        return 0;
    }

    public static void main(String[] args){
      //Initialize Variables For Input Validation
      String inputString;
      String firstOrSecond = "first";
      String [] inputNumbers = new String[2];
      Boolean isValid = false;

      //Initialize Scanner
      Scanner scan = new Scanner(System.in);

      //BigIntegers To Store Rational Numerator And Denominator
      BigInteger numerator;
      BigInteger denominator;

      //Integers To Check That Input Only Contains Numbers
      int inputNumerator;
      int inputDenominator;

      //Initialize Rationals
      Rational firstFraction = new Rational();
      Rational secondFraction = new Rational();


      //Loop Twice To Get Two Rational Numbers
      for (int count = 1; count <=2; count++){
        isValid = false;
        //Input Validation Loop
        while (isValid == false){
          System.out.println("Enter the " + firstOrSecond + " rational number:");
          inputString = scan.nextLine();
          inputNumbers = inputString.split(" ");
          
          //If only 2 numbers have been entered
          if (inputNumbers.length == 2){
            try{
              inputNumerator = Integer.parseInt(inputNumbers[0]);
              inputDenominator = Integer.parseInt(inputNumbers[1]);
              //Assume Input Is Valid Unless An Exception Is Thrown
              isValid = true;
            }catch (NumberFormatException exception) {
              System.out.println("A rational number consists of two integers, please enter two integers (This Loop Will Repat Until Valid Input Is Entered)");
              //If An Error Is Raised Input Is Not Valid
              isValid = false;
            }
          }else{
            System.out.println("A rational number consists of two integers, please enter two integers (This Loop Will Repat Until Valid Input Is Entered)");
            //If The Input Contains More Than Tow Integers Input Is Not Valid
            isValid = false;
          }
          //If The Input Is Valid Assign It To The Correct Variable
          if (isValid){
            numerator = new BigInteger(inputNumbers[0]);
            denominator = new BigInteger(inputNumbers[1]);
            
            //Assign Rational Object Values To Rational Object Variables
            if (count == 1){
              firstFraction = new Rational(numerator,denominator);
              firstOrSecond = "second";
            }else{
              secondFraction = new Rational(numerator,denominator);
            }
          }
        }
      }

      //close scanner
      scan.close();

      //Print Rational Class Test Values
      
      //Addition Test
      System.out.println(firstFraction + " + " + secondFraction + " = " + firstFraction.add(secondFraction));
      //Subtraction Test
      System.out.println(firstFraction + " - " + secondFraction + " = " + firstFraction.subtract(secondFraction));
      //Multiplication Test
      System.out.println(firstFraction + " * " + secondFraction + " = " + firstFraction.multiply(secondFraction));
      //Division Test
      System.out.println(firstFraction + " / " + secondFraction + " = " + firstFraction.divide(secondFraction));
      //Convert To Double Test
      System.out.println(secondFraction + " is " + secondFraction.doubleValue());
    }
}
