/*** Jasmin Reynoso
 *   CMSC 401 Spring 2021
 *   
 *   This program takes in a user input of n and creates an array of n length which the 
 *   elements are also user inputed and outputs the elements in order of first largest, 
 *   first smallest, second largest, second smallest, third largest... etc...
 *   
 ***/
import java.util.*;

public class cmsc401 {
    private static final Scanner scanner = new Scanner(System.in);

    // Please use these methods to take inputs and write outputs.
    private static Integer readInt() {
        return scanner.nextInt();
    }

    private static String readString() {
        return scanner.next();
    }

    private static Integer[] readIntegerArray(int size) {
        Integer[] array = new Integer[size];
        for (int i = 0; i < size; i++) {
            array[i] = readInt();
        }
        return array;
    }

    private static void printInt(int a) {
        System.out.println(a);
    }

    private static void printString(String s) {
        System.out.println(s);
    }
    

    public static void main(String[] args) {

//        // reading an Integer
        Integer a = readInt();
//        // writing int output
//        printInt(a);
//
//        // reading a String
//        String s = readString();
//        // writing string output
//        printString(s);
//
//        // reading an Integer Array (you should provide the size)
        Integer[] listOfIntegers = readIntegerArray(a);
        boolean max = true;
        for (int x = 0; x < a; x++) 
        { 
            // Find the minimum element in unsorted array 
            int targ = x;
            if(max) {
            	for (int y = x + 1; y < a; y++) 
                    if (listOfIntegers[y] > listOfIntegers[targ]) 
                        targ = y; 
            			max = false;
            } else {
            for (int y = x + 1; y < a; y++) 
                if (listOfIntegers[y] < listOfIntegers[targ]) 
                    targ = y;
            		max = true;
            }
            // Swap the found minimum element with the first 
            // element 
            int temp = listOfIntegers[targ]; 
            listOfIntegers[targ] = listOfIntegers[x]; 
            listOfIntegers[x] = temp; 
    }
        for(int num: listOfIntegers) {
        	printInt(num);
        }
}
}
