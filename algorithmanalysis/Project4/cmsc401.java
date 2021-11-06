//Jasmin Reynoso
//Programming Assmt 4
/*
Build a simple calculator program that supports: 
•  Integer variables 
•  Integer constants/values 
•  three operations: addition, subtraction, multiplication,  
•  printing out variable’s value 
The calculator takes instruction as lines from Standard Input (i.e., System.in in java).   
*/
//401 Spring '21

import java.util.*;

public class cmsc401 {
    private static final Scanner scanner = new Scanner(System.in);

    // Please use these methods to take inputs and write outputs.
    private static Integer readInt() {
        return scanner.nextInt();
    }

    private static String readString() {
        return scanner.nextLine();
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
    	
         HashMap<String, Integer> hm = new HashMap(1000);
         while(scanner.hasNext()) {
        	 String input = readString();
        	 if(input.compareToIgnoreCase("quit") == 0) {
        		 System.exit(0);
        	 }
             String delims = "[ ]";
             String []tokens = input.split(delims);
             if(hm.containsKey(tokens[0].substring(1))) {
            	 System.out.println("ERROR");
             }
             else if(!tokens[2].contains("x")) {
            	hm.put((tokens[0].substring(1, 2)), Integer.parseInt(tokens[2]));
        	 	System.out.println(Integer.parseInt(tokens[2]));
             }
             else if(tokens[2].contains("x") && tokens[4].contains("x")) {

            	 if(hm.containsKey((tokens[2].substring(1, 2))) && hm.containsKey((tokens[4].substring(1, 2)))) {
        		 char op = tokens[3].charAt(0);
        		 int x = (int) hm.get((tokens[2].substring(1)));
     		 	 int y = (int) hm.get((tokens[4].substring(1)));
        		 	switch(op) {
        		 	case '*':
        		 		int prod = x * y;
        		 		hm.put((tokens[0].substring(1, 2)), prod);
        		 		System.out.println(prod);
        		 		break;
        		 	case '+':
        		 		int sum = x + y;
        		 		hm.put((tokens[0].substring(1, 2)), sum);
        		 		System.out.println(sum);
        		 		break;
        		 	case '-':
        		 		int dif = x - y;
        		 		hm.put((tokens[0].substring(1, 2)), dif);
        		 		System.out.println(dif);
	        			 break;
        		 				}
            	 	} 	else {
        	    		System.out.println("ERROR");
        	    		}
         }
         }
    }
}
