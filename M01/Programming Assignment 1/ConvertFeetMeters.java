/*
Author: Scott Field
Name: ConvertFeetMetersTable
Version: 1.0
Purpose: Using the methods footToMeter and meterToFoot display a table 
converting between meters and feet  
*/

public class ConvertFeetMeters {
    //Define conversion methods

    // Convert from feet to meters 
    public static double footToMeter(double foot){
        return 0.305 * foot;
    }

    // Convert from meters to feet (requires BigDecimal to get correct)
    public static double meterToFoot(double meter){
        return 3.279 * meter; //Currently: 3.278695333333333 Returns Book Given Values Except For 50 and 60, Instructor Informed That Book Was Incorrect; Use 3.279 Instead
    } 

    public static void main(String[] args){
        
        //Start Variables For The Table
        double feetValue = 1.0;
        double meterValue = 20.0;
        //Increment Values For The Table
        double feetIncrement = 1.0;
        double meterIncrement = 5.0;
        //Store Resulting Values
        String metersResult;
        String feetResult;

        //Number Of Rows In The Table
        int numRows = 10;
    
        // Print The Table Header
        System.out.println(                 "Feet\t\tMeters\t\t|\tMeters\t\tFeet"                 );
        //Print The Dividing Line
        System.out.println("-----------------------------------------------------------------------");

        for (int i = 0;i < numRows;i++){
            //Calculate Conversion And Format Result To 3 Decimal Places
            feetResult = String.format("%3.3f",meterToFoot(meterValue));
            metersResult = String.format("%3.3f",footToMeter(feetValue));
            //Output The Table Row
            System.out.println(feetValue + "\t\t" + metersResult + "\t\t|\t" + meterValue + "\t\t" + feetResult);
            //increment foot and meter values
            feetValue += feetIncrement;
            meterValue += meterIncrement;
        }
 
    }
}
