/* Jasmin Reynoso
For the input of type “0 A B” there should be no output – just internal update of the data 
structures. 
For the input of type “1 A B”, there should be one line of output to System.out, with 0 if A and B 
are not “connected”, and 1 if they are. 
**Disjointed Sets Approach**
Spring 2021
 */
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

//        // reading an Integer
//       Integer a = readInt();
//        // writing int output
//        printInt(a);
//
//        // reading a String
        String input = readString();
        String delims = "[ ]+";
        String[] tokens = input.split(delims);
        int numOfCitizens = Integer.parseInt(tokens[1]);
        int[] ints = new int[numOfCitizens];
        for(int x = 0; x < numOfCitizens; x++) {
        	ints[x] = x;
        	}
        DisjointSet ds = new DisjointSet();
        ds.makeSet(ints);
        int numOfLines = Integer.parseInt(tokens[0]);
        int a;
        int b;
        int n;
        for(int x = 1; x <= numOfLines; x++) {
            String line = readString();
            String[] request = line.split(delims);
            n = Integer.parseInt(request[0]);
            a = Integer.parseInt(request[1]);
            b = Integer.parseInt(request[2]);
            if(n == 0) {
            	ds.Union(a, b);
            	} else if(n == 1) {
            if(ds.Find(a) == ds.Find(b)) {
            		printInt(1);
            		} else {
            		printInt(0);
            		}
            	}
            }
        }
//        // writing string out
//        printString(s);
//
//        // reading an Integer Array (you should provide the size)
//        Integer[] listOfIntegers = readIntegerArray(5);

    }
//A class to represent a disjoint set
class DisjointSet
{
 private Map<Integer, Integer> parent = new HashMap();

 // perform MakeSet operation
 public void makeSet(int[] universe)
 {
     // create `n` disjoint sets (one for each item)
     for (int i: universe) {
         parent.put(i, i);
     }
 }

 // Find the root of the set in which element `k` belongs
 public int Find(int k)
 {
     // if `k` is root
     if (parent.get(k) == k) {
         return k;
     }

     // recur for the parent until we find the root
     return Find(parent.get(k));
 }

 // Perform Union of two subsets
 public void Union(int a, int b)
 {
     // find the root of the sets in which elements
     // `x` and `y` belongs
     int x = Find(a);
     int y = Find(b);

     parent.put(x, y);
 }
}
