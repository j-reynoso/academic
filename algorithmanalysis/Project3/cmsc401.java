// Jasmin Reynoso
/*
Reads (from standard input) the database of gas & motel costs, which is in the format 
below: 
o  The number of cities, N, in the first line. N>=3, N<=1000 
§  Richmond is city number 1, L.A. is city number 2 
o  The total number of direct highways between cities, M, in the second line. M>=2, 
M<=10000 
o  Subsequent  N-2  lines  provide  the  lowest  motel  price  for  each  of  N-2  cities 
(excluding Richmond (city 1) and L.A. (city 2)), each as a single line 
§  Each line consists of two numbers: city number (3...N), motel cost (1...200) 
o  Subsequent M lines provide gas prices for traveling direct highways between two 
cities, each as a single line  
§  Each  line  consists  of  three  numbers:  city  number  (1...N),  city  number 
(1...N), cost of gas for travel between the two cities (1...200) 
2.  Calculates the lowest possible total cost of driving from city 1 (Richmond) to city 2 (L.A.) 
o  Cost shouldn’t include a motel in Richmond, nor in L.A. 
3.  Prints that cost (only the cost, not the route) on the standard output 
 
*/
//
//
//
import java.util.*;
public class cmsc401 {
    private static final Scanner iscanner = new Scanner(System.in);
    private static final Scanner sscanner = new Scanner(System.in);
    // Please use these methods to take inputs and write outputs.
    private static Integer readInt() {
        return iscanner.nextInt();
    }
    private static String readString() {
        return sscanner.nextLine();
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
    
    static int minDistance(int totalDist[], Boolean sptSet[], int cities)
    {
        // Initialize min value
        int min = Integer.MAX_VALUE;
        int min_index = -1;
  
        for (int c = 0; c < cities; c++)
            if (sptSet[c] == false && totalDist[c] <= min) {
                min = totalDist[c];
                min_index = c;
            }
  
        return min_index;
    }
    
    private static int calcPath(int src, int dest, int cities, int[] motelPrices, int[][] gasPrices) {
    	int total = Integer.MAX_VALUE;
    	Boolean sptSet[] = new Boolean[cities];
    	int totalDist[] = new int[cities];
    	for(int x = 0; x < cities; x++) {
    		totalDist[x] = Integer.MAX_VALUE;
    		sptSet[x] = false;
    	}
    	
    	totalDist[src] = 0;
    	//finding path
    	for(int i = 0; i < cities; i++) {
    		int m = minDistance(totalDist, sptSet, cities);
    		sptSet[m] = true;
    		
    		//update totalDist value 
    		for(int j = 0; j < cities; j++) {
    			if(!sptSet[j] && gasPrices[m][j] != 0 && totalDist[m] != Integer.MAX_VALUE && totalDist[m] + gasPrices[m][j] + motelPrices[j] < totalDist[j])
    			{
    				totalDist[j] = totalDist[m] + gasPrices[m][j];
    				totalDist[j] += motelPrices[j];
    			}
    			
    		}
    	}
   /* 	for(int x = 0; x < totalDist.length; x++) {
    		if(totalDist[x] < total && !(totalDist[x] == 0)) {
    			total = totalDist[x];
    		}
    	} */
    	return totalDist[0];
    }
    public static void main(String[] args) {
        // reading an Integer
        int numOfCities = readInt();
        int numOfDH = readInt();
        int[] motelPrices = new int[numOfCities];
        motelPrices[0] = 0;
        motelPrices[1] = 0;
        for(int x = 0; x < numOfCities - 2; x++) {
        	String data = readString();
        	String delims = "[ ]+";
        	String[] tokens = data.split(delims);
        	int city = Integer.parseInt(tokens[0]);
        	int motelPrice = Integer.parseInt(tokens[1]);
        	motelPrices[city - 1] = motelPrice;
        }
        int[][] gasPrices = new int[numOfCities][numOfCities];
        for(int x = 0; x < numOfDH; x++) {
        	String data = readString();
        	String delims = "[ ]+";
        	String[] tokens = data.split(delims);
        	int citysrc = Integer.parseInt(tokens[0]);
        	int citydst = Integer.parseInt(tokens[1]);
        	int gas = Integer.parseInt(tokens[2]);
        	gasPrices[citysrc - 1][citydst - 1] = gas;
        	gasPrices[citydst - 1][citysrc - 1] = gas;
        }
        
        printInt(calcPath(1, 2, numOfCities, motelPrices, gasPrices));
        
        // writing int output
//        printInt(a);
//
        // reading a String
       // String s = readString();
//        // writing string output
//        printString(s);
//
//        // reading an Integer Array (you should provide the size)
//        Integer[] listOfIntegers = readIntegerArray(5);
        // write your code here
    }
}
