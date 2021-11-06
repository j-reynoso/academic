/* Jasmin Reynoso
 * December 1st 2019
 * CMSC 256 Fall 2019
 * Project 6
 * 
 * This program tests the functionality of the methods in MyGraph.
 */


package cmsc256;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class MyGraphTest {

	@Test
	public void testIsGraphConnected1() throws FileNotFoundException {
		MyGraph mg = new MyGraph();
		boolean output = mg.isGraphConnected("/Users/jasminreynoso/eclipse-workspace/Project 6/src/cmsc256/firstgraph");
		boolean expectedOutput = true;
		assertEquals(output, expectedOutput);
	}
	@Test
	public void testIsGraphConnected2() throws FileNotFoundException {
		MyGraph mg = new MyGraph();
		boolean output = mg.isGraphConnected("/Users/jasminreynoso/eclipse-workspace/Project 6/src/cmsc256/secondgraph");
		boolean expectedOutput = false;
		assertEquals(output, expectedOutput);
	}
	@Test
	public void testIsGraphConnected3() throws FileNotFoundException {
		MyGraph mg = new MyGraph();
		boolean output = mg.isGraphConnected("/Users/jasminreynoso/eclipse-workspace/Project 6/src/cmsc256/thirdgraph");
		FileNotFoundException e = new FileNotFoundException(); 
		assertEquals(output, e);
	}
	@Test
	public void testIsGraphConnected4() throws FileNotFoundException {
		MyGraph mg = new MyGraph();
		boolean output = mg.isGraphConnected("/Users/jasminreynoso/eclipse-workspace/Project 6/src/cmsc256/fourthgraph");
		boolean expectedOutput = false;
		assertEquals(output, expectedOutput);
	}
	@Test
	public void testGetConnectedComponents1() throws FileNotFoundException {
		MyGraph mg = new MyGraph();
		mg.isGraphConnected("/Users/jasminreynoso/eclipse-workspace/Project 6/src/cmsc256/firstgraph");
		List<List<Integer>> output = mg.getConnectedComponents();
		List<List<Integer>> expectedOutput = new ArrayList<List<Integer>>();
		List<Integer> listOne = new ArrayList<Integer>();
		for(int x = 0; x < 6; x++) {
			listOne.add(x);
		}
		expectedOutput.add(listOne);
		assertEquals(output, expectedOutput);
		
	}
	@Test
	public void testGetConnectedComponents2() throws FileNotFoundException {
		MyGraph mg = new MyGraph();
		mg.isGraphConnected("/Users/jasminreynoso/eclipse-workspace/Project 6/src/cmsc256/secondgraph");
		List<List<Integer>> output = mg.getConnectedComponents();
		List<List<Integer>> expectedOutput = new ArrayList<List<Integer>>();
		List<Integer> listOne = new ArrayList<Integer>();
		listOne.add(null);
		expectedOutput.add(listOne);
		assertEquals(output, expectedOutput);
		
	}
	@Test
	public void testGetConnectedComponents3() throws FileNotFoundException {
		MyGraph mg = new MyGraph();
		mg.isGraphConnected("/Users/jasminreynoso/eclipse-workspace/Project 6/src/cmsc256/thirdgraph");
		List<List<Integer>> output = mg.getConnectedComponents();
		List<List<Integer>> expectedOutput = new ArrayList<List<Integer>>();
		List<Integer> listOne = new ArrayList<Integer>();
		listOne.add(0);
		listOne.add(2);
		listOne.add(3);
		List<Integer> listTwo = new ArrayList<Integer>();
		listTwo.add(1);
		List<Integer> listThree = new ArrayList<Integer>();
		listThree.add(4);
		listThree.add(5);
		expectedOutput.add(listOne);
		expectedOutput.add(listTwo);
		expectedOutput.add(listThree);
		assertEquals(output, expectedOutput); 
	}
	@Test
	public void testGetConnectedComponents4() throws FileNotFoundException {
		MyGraph mg = new MyGraph();
		mg.isGraphConnected("/Users/jasminreynoso/eclipse-workspace/Project 6/src/cmsc256/fourthgraph");
		List<List<Integer>> output = mg.getConnectedComponents();
		List<List<Integer>> expectedOutput = new ArrayList<List<Integer>>();
		assertEquals(output, expectedOutput); 
	}
	@Test
	public void testGetPath() throws FileNotFoundException {
		MyGraph mg = new MyGraph();
		boolean outputConnected = mg.isGraphConnected("/Users/jasminreynoso/eclipse-workspace/Project 6/src/cmsc256/firstgraph");
		List<Integer> output = mg.getPath(1, 5);
		List<Integer> listOne = new ArrayList<Integer>();
		listOne.add(1);
		listOne.add(3);
		listOne.add(5);
		assertEquals(output, listOne);
	}
	@Test
	public void testGetPath2() throws FileNotFoundException {
		MyGraph mg = new MyGraph();
		boolean outputConnected = mg.isGraphConnected("/Users/jasminreynoso/eclipse-workspace/Project 6/src/cmsc256/secondgraph");
		List<Integer> output = mg.getPath(1, 1);
		List<Integer> listOne = new ArrayList<Integer>();
		assertEquals(output, listOne);
	}
	@Test
	public void testGetPath3() throws FileNotFoundException {
		MyGraph mg = new MyGraph();
		boolean outputConnected = mg.isGraphConnected("/Users/jasminreynoso/eclipse-workspace/Project 6/src/cmsc256/thirdgraph");
		List<Integer> output = mg.getPath(4, 5);
		List<Integer> listOne = new ArrayList<Integer>();
		listOne.add(4);
		listOne.add(5);
		assertEquals(output, listOne);
	}
	@Test
	public void testGetPath4() throws FileNotFoundException {
		MyGraph mg = new MyGraph();
		boolean outputConnected = mg.isGraphConnected("/Users/jasminreynoso/eclipse-workspace/Project 6/src/cmsc256/fourthgraph");
		List<Integer> output = mg.getPath(1, 2);
		assertEquals(output, null);
	}
	@Test
	public void testIsCyclic() throws FileNotFoundException {
		MyGraph mg = new MyGraph();
		boolean outputConnected = mg.isGraphConnected("/Users/jasminreynoso/eclipse-workspace/Project 6/src/cmsc256/firstgraph");
		boolean output = mg.isCyclic();
		boolean expectedOutput = true;
		assertEquals(output, expectedOutput);
	}
	@Test
	public void testIsCyclic2() throws FileNotFoundException {
		MyGraph mg = new MyGraph();
		boolean outputConnected = mg.isGraphConnected("/Users/jasminreynoso/eclipse-workspace/Project 6/src/cmsc256/secondgraph");
		boolean output = mg.isCyclic();
		boolean expectedOutput = true;
		assertEquals(output, expectedOutput);
	}
	@Test
	public void testIsCyclic3() throws FileNotFoundException {
		MyGraph mg = new MyGraph();
		boolean outputConnected = mg.isGraphConnected("/Users/jasminreynoso/eclipse-workspace/Project 6/src/cmsc256/thirdgraph");
		boolean output = mg.isCyclic();
		boolean expectedOutput = true;
		assertEquals(output, expectedOutput);
	}
	@Test
	public void testIsCyclic4() throws FileNotFoundException {
		MyGraph mg = new MyGraph();
		boolean outputConnected = mg.isGraphConnected("/Users/jasminreynoso/eclipse-workspace/Project 6/src/cmsc256/fourthgraph");
		boolean output = mg.isCyclic();
		boolean expectedOutput = false;
		assertEquals(output, expectedOutput);
	}
	@SuppressWarnings("unchecked")
	@Test
	public void testFindCycle() throws FileNotFoundException {
		MyGraph mg = new MyGraph();
		boolean outputConnected = mg.isGraphConnected("/Users/jasminreynoso/eclipse-workspace/Project 6/src/cmsc256/firstgraph");
		List output = mg.findCycle(5);
		List expectedOutput = new ArrayList();
		expectedOutput.add(4);
		expectedOutput.add(2);
		expectedOutput.add(0);
		expectedOutput.add(1);
		expectedOutput.add(3);
		expectedOutput.add(5);
		assertEquals(output, expectedOutput);
	}
	@Test
	public void testFindCycle2() throws FileNotFoundException {
		MyGraph mg = new MyGraph();
		boolean outputConnected = mg.isGraphConnected("/Users/jasminreynoso/eclipse-workspace/Project 6/src/cmsc256/secondgraph");
		List output = mg.findCycle(1);
		List expectedOutput = new ArrayList();
		assertEquals(output, null);
	}
	@Test
	public void testFindCycle3() throws FileNotFoundException {
		MyGraph mg = new MyGraph();
		boolean outputConnected = mg.isGraphConnected("/Users/jasminreynoso/eclipse-workspace/Project 6/src/cmsc256/thirdgraph");
		List output = mg.findCycle(5);
		List expectedOutput = new ArrayList();
		expectedOutput.add(5);
		expectedOutput.add(4);
		assertEquals(output, expectedOutput);
	}
	@Test
	public void testFindCycle4() throws FileNotFoundException {
		MyGraph mg = new MyGraph();
		boolean outputConnected = mg.isGraphConnected("/Users/jasminreynoso/eclipse-workspace/Project 6/src/cmsc256/fourthgraph");
		List output = mg.findCycle(10);
		List expectedOutput = new ArrayList();
		assertEquals(output, null);
	}
}
