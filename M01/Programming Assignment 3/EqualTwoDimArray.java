/*
Author: Scott Field
Name: EqualTwoDimArray
Version: 1.0
Purpose: Prompt the user to enter two 3 x 3 arrays of integers (Note Each Array Is Entered On One Line)
Then test if the two are identical
*/

import java.util.Arrays;
import java.util.Scanner;

public class EqualTwoDimArray {
    public static boolean equals(int[][] m1, int[][] m2){
        //if the length is different return false
        if (m1.length != m2.length) {
            return false;
        }

        //Convert The Two Dimensional Lists To One Dimensional Lists
        int[] flattenedListm1 = flattenList(m1);
        int[] flattenedListm2 = flattenList(m2);

        //Sort both lists into having their elements ordered from smallest to largest to make comparison simpler
        Arrays.sort(flattenedListm1);
        Arrays.sort(flattenedListm2);

        //Compare Each Element To See If They Are Different
        for (int i = 0; i < flattenedListm1.length; i++){
            if (flattenedListm1[i] != flattenedListm2[i]){
                return false;
            }
        }  
        return true;
    }
    
    //convert the two dimensional array into a one dimensional array
    public static int[] flattenList(int[][] list){
        //Add The Length Of The List To The Length Of The Lists It Contains
        int totalLength = 0;
        for (int i = 0; i < list.length; i++) {
            for (int j = 0; j < list[i].length; j++) {
                totalLength++;
            }
        }
        //Create The New List With The Correct Size
        int[] flattenedList = new int[totalLength];
        int index = 0;
        /* 
        for (int[] innerList : list) {
            for (int value : innerList) {
                flattenedList[index++] = value;
            }
        }
        */
        //Populate The One Dimensional List With The Values Of The Two Dimensional List
        for (int p = 0; p < list.length; p++){
            for (int l = 0; l < list[p].length; l++){
                //set the value
                flattenedList[index] = list[p][l];
                //increment the index
                index+=1;
            }
        }

        //return the one dimensional list
        return flattenedList;
    }

    public static void main(String[] args){
        int size = 3;
        int [][] tableOne = new int[size][size];
        int [][] tableTwo = new int[size][size];
       
        //Get User Input
        System.out.print("Enter list1: ");
        Scanner scanner = new Scanner(System.in);

        //Populate Table One With Input
        for (int outerIndex = 0; outerIndex < size; outerIndex++){
            for (int innerIndex = 0; innerIndex < size; innerIndex++){
                //Add It To The Table
                tableOne[outerIndex][innerIndex] = scanner.nextInt();
            }
        }

        System.out.println();
        System.out.print("Enter list2: ");

        //Populate Table Two With Input
        for (int outerIndex = 0; outerIndex < size; outerIndex++){
            for (int innerIndex = 0; innerIndex < size; innerIndex++){
                //Add It To The Table
                tableTwo[outerIndex][innerIndex] = scanner.nextInt();
            }
        }

        //close scanner
        scanner.close();

        //test if the lists are equal
        if (equals(tableOne,tableTwo)){
            System.out.println("The two arrays are identical");
        }else{
            System.out.println("The two arrays are not identical");
        }

    }
}
