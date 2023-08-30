/*
Author: Scott Field
Name: MyDate Class Test
Version: 1.0
Directions:
    Design a class named MyDate. The class contains:
        The data fields year, month, and day that represent a date. month is 0-based, i.e., 0 is for January.

        A no-arg constructor that creates a MyDate object for the current date.

        A constructor that constructs a MyDate object with a specified elapsed time since midnight, January 1, 1970, in milliseconds.

        A constructor that constructs a MyDate object with the specified year, month, and day.

        Three getter methods for the data fields year, month, and day, respectively.

        A method named setDate(long elapsedTime) that sets a new date for the object using the elapsed time.
Instructions:
    Create two MyDate objects (using new MyDate() and new MyDate(34355555133101L)) and displays their year, month, and day.
*/

public class myDate {
    //class variables
    private int year;
    private int month;
    private int day;

    //No Argument Constructor For Current Date
    public myDate() {
        long elapsedTime = System.currentTimeMillis();
        this.setDate(elapsedTime);
        this.day -= 1; //the day is always of by 1 when getting the current day (This line fixes that problem)
    }

    //Constructor with elapsed time since Epoch (1970)
    public myDate(long elapsedTime) {
        this.setDate(elapsedTime);
    }

    // Constructor with specified year, month, and day
    public myDate(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    // Getter methods
    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    // Set date using elapsed time since epoch
    public void setDate(long elapsedTime) {
        //variable initialization

        //variable of total days passed since 1970
        long days = (elapsedTime / 86400000); // formula: elapsedTime (Milliseconds) / ( 1000 (Seconds) * 60 (Minutes) * 60 (Hours) * 24 (Days) = 86400000 )
        this.year = 1970; // Epoch year
        this.day = 1; // Epoch day
        this.month = 0; // Epoch Month (January Is 0 In Current Format)
        int[] daysInMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31}; //Number Of Days In Each Month From January To December

        //Calculate the years by adding 1 year for every normal year of 365 days and for every leap year 366 days
        while (days >= 365) {
            if (isLeapYear(year)) {
                days -= 366;
            }else{
                days -= 365;
            }
            //Increment Year
            year++;
        }

        // Calculate the month by adding 1 month for each year and leap year
        while (days >= daysInMonth[month]) {
            //If The Month Is Febuary And The Year Is A Leap Year
            if (month == 1 && isLeapYear(year)) {
                //Then Subtract The Days By The 29 Days Febuary Is During A Leap Year
                if (days >= 29) {
                    days -= 29;
                }
            //Otherwise Subtract the days by the amount of days in the Month from the list and continue to the next month 
            }else{
                days -= daysInMonth[month];
            }
            //Increment Month
            month++;
        }

        //Set the day value (month and year are already set)
        this.day += (int) days;
    }

    // Check if a year is a leap year
    public static boolean isLeapYear(int year) {
        //set the flag variable to false at start
        boolean flag = false;
        //If the year is evenly divisible by 4 then continue evaluation
        if (year % 4 == 0){
            //if the year is evenly divisibly by 4 and is not evenly divisible by 100 then it is a leap year
            if (year % 100 != 0){
                //the year is a leap year
                flag = true;
            //if the year is evenly divisibly by 4 and 100 check if it is evenly divisible by 400
            }else if (year % 400 != 0){
                //if it is then the year is a leap year
                flag = true;
            }
        }
        return flag;
    }

    public static void main(String[] args){
        //Define myDate With Empty Constructor
        myDate noArg = new myDate();
        //Define myDate With Elapsed Seconds
        myDate oneArg = new myDate(34355555133101L);
        //Output myDate With Empty Constructor
        System.out.println("Todays Date Is: ");
        System.out.println("Year: " + noArg.getYear());
        System.out.println("Month: " + noArg.getMonth());
        System.out.println("Day: " + noArg.getDay());

        //Output myDate With Milliseconds Constructor
        System.out.println("34355555133101L Milliseconds Past The Epoch Date Of January 1st 1970 Is: ");
        System.out.println("Year: " + oneArg.getYear());
        System.out.println("Month: " + oneArg.getMonth());
        System.out.println("Day: " + oneArg.getDay());

    }
}
