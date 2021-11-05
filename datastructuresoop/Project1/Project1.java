/**
 *  CMSC 256 Fall 2019
 *  Project 1
 *  [Reynoso], [Jasmin]
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Project1 {

    public static void main(String[] args) throws IOException {
    	Project1 obj = new Project1();
    	String[] argv = args;
    	File df = obj.getFile(obj.checkArgs(argv));
    	int numRec = obj.getNumRecords(df);
    	String[][] ds = obj.readFile(df, numRec);
    	int lowerBounds = 20;
    	int upperBounds = 30;
    	System.out.println("The tallest height is " + obj.findTallest(ds));
    	System.out.println("The row with the lowest weight is " + Arrays.toString(obj.findLightestRecord(ds)));
    	System.out.println("Average height for ages " + lowerBounds + "-" + upperBounds + " is " + obj.findAvgHeightByAgeRange(ds, lowerBounds, upperBounds));
    }

    /**
     *   Gets the file name from command line argument;
     *   If parameter is empty, call promptForFileName() method
     * @param argv  String array from command line argument
     * @return      the name of the data file
     */
    public String checkArgs(String[] argv) {
    	String arg = "";
    	if(argv.length == 0) {
    		arg = promptForFileName();
    	}
  
    	return arg;
    }

    /**
     * Prompt user to enter a file name
     * @return user entered file name
     */
    public String promptForFileName() {
    	Scanner kbReader = new Scanner(System.in);
    	System.out.print("Please enter the file name:");
    	String fileName = kbReader.nextLine();
    return fileName;
    }

    /**
     * Retrieve file with the given file name.
     * Prompts user if file cannot be opened or does not exist.
     * @param fileName  The name of the data file
     * @return          File object
     * @throws java.io.FileNotFoundException
     */
    public File getFile(String fileName) throws FileNotFoundException {
    	File fileObj = new File(fileName);
    	if(!fileObj.exists()) {
    		fileObj = getFile(promptForFileName());
    	} else {
    	return fileObj;
    	}
    	return fileObj;
    }
    
    public int getNumRecords(File file) throws FileNotFoundException {
    	Scanner fileReader = new Scanner(file);
    	int maxRow = 0;
    	while(fileReader.hasNextLine() && fileReader.nextLine() != null) {
    		maxRow++;
    	}
    	return maxRow;
    }

    /**
     * Reads the comma delimited file to extract the number data elements
     * provided in the second argument.
     * @param file          The File object
     * @param numRecords    The number of values to read from the input file
     * @return              2D array of data from the File
     * @throws IOException if any lines are missing data
     */
    public String[][] readFile(File file, int numRecords) throws IOException {
    	String[][] data = new String[numRecords][3];
    	Scanner lineReader = new Scanner(file).useDelimiter(","); // establishes delimiter of comma to easily traverse through line
   // 	lineReader.nextLine(); // moving file reader passed header
    	for(int x = 0; x < numRecords; x++) {
    		for(int y = 0; y < 3 && lineReader.hasNext(); y++) { // second condition for for loop to make sure file still has content left to scan
    			if(y < 2) { // if its the first two columns use delimiter to traverse through file
    			data[x][y] = lineReader.next();
    			}
    			else { // for third column there is no delimiter so program uses nextLine 
    			String str = lineReader.nextLine(); // stores rest of line into temp str
    			data[x][y] = str.substring(1, str.length()); //to avoid including comma, takes the substring of temp string
    			}
    			}
    		}
    	lineReader.close();
    	return data;
    	}

    /**
     * Determines the tallest height in the data set
     * Height is the second field in each row
     * @param db        2D array of data containing [age] [height] [weight]
     * @return          Maximum height value
     */
    public int findTallest(String[][] db) {
    	int tallest = 0;
    	for(int x = 0; x < db.length; x++) {
    		if(Integer.parseInt(db[x][1]) > tallest) {
    			tallest = Integer.parseInt(db[x][1]);
    		}
    	}
    	return tallest;
    }

    /**
     * Returns the values in the record that have the lowest weight
     * @param db        2D array of data containing [age] [height] [weight]
     * @return          Smallest weight value
     */
    public String[] findLightestRecord(String[][] db) {
    	int lightest = 9999;
    	int indexRow = 0;
    	String[] lightestRecords = new String[3]; // sets to array of 3 values for age, height, weight
    	for(int x = 0; x < db.length; x++) {
    		if(Integer.parseInt(db[x][2]) < lightest) {
    			indexRow = x; // keeps track of which row the lightest record is found in
    			lightest = Integer.parseInt(db[x][2]);
    		}
    	}
    	for(int x = 0; x < 3; x++) {
    		lightestRecords[x] = db[indexRow][x]; // storing different values from database into array 
    	}
    	return lightestRecords;
    }

    /**
     * Calculates the average height for all records with the given age range.
     * @param db            2D array of data containing [age] [height] [weight]
     * @param lowerBound    youngest age to include in the average
     * @param upperBound    oldest age to include in the average
     * @return              The average height for the given range or 0 if no
     *                      records match the filter criteria
     */
    public double findAvgHeightByAgeRange(String[][] db, int lowerBound, int upperBound) {
    	double heights = 0;
    	int total = 0;
    	for(int x = 0; x < db.length; x++) {
    			if(Integer.parseInt(db[x][0]) <= upperBound && Integer.parseInt(db[x][0]) >= lowerBound) {
    				total++;
    				heights += Integer.parseInt(db[x][1]);
    			}
    	}
    	/* checking to see if there is any values to calculate to avoid returning null */
    	if(total > 0) {
    	heights /= total;
    	return heights;
    	}
    	return 0;
    }
}
