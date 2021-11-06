/* Jasmin Reynoso
 * December 1st 2019
 * CMSC 256 Fall 2019
 * Project 6
 * 
 * This program extends UnweightedGraph<V> and implements methods isGraphConnected(String fileName),
 * getConnectedComponents(), getPath(int origin, int destination), isCyclic(), and findCycle(int u) to
 * allow for functionality in looking through properties of graphs such as paths,
 * cycles, and edges.
 */


package cmsc256;

import java.io.*;
import java.util.*;

public class MyGraph<V> extends UnweightedGraph<V> {
	public static void main(String args[]) {
		MyGraph mg = new MyGraph();
		try {
			System.out.println(mg.isGraphConnected("/Users/jasminreynoso/eclipse-workspace/Project 6/src/cmsc256/firstgraph"));
			System.out.println(mg.getConnectedComponents() + " ");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	} 
	public MyGraph() {
		super();
	 }
	
	public MyGraph(V[] vertices, int[][] edges) {
		super(vertices, edges);
	}
	
	public MyGraph(List<V> vertices, List<Edge> edges) {
		 super(vertices, edges); 
	}
	 
	public MyGraph(List<Edge> edges, int numberOfVertices) {
		 super(edges, numberOfVertices);
	 }
	 
	public MyGraph(int[][] edges, int numberOfVertices) {
		 super(edges, numberOfVertices);
	 }
	
	
	/* PARAM: fileName
	 * RETURNS: boolean of whether or not graph is connected
	 * DOES: throws filenotfoundexception if file doesnt exist
	 * takes data from file and creates instance of MyGraph to insert data into
	*/
	public boolean isGraphConnected(String fileName) throws FileNotFoundException {
		if(fileName == null) {
			return false;
		}
		File graphFile = new File(fileName);
		if(!graphFile.exists()) {
			throw new FileNotFoundException();
		}
		Scanner graphReader = new Scanner(graphFile);
		int numOfVertices = Integer.parseInt(graphReader.nextLine());
		List<Edge> edgeList = new ArrayList<Edge>();
		while(graphReader.hasNext()) {
			String str = graphReader.nextLine();
			String[] strArray = str.split(" ");
			int vertex = Integer.parseInt(strArray[0]);
			for(int x = 1; x < strArray.length; x++) {
				Edge e = new Edge(vertex, Integer.parseInt(strArray[x]));
				edgeList.add(e);
				}
			}
		MyGraph<V> g = new MyGraph<V>(edgeList, numOfVertices);
		if(g.vertices.size() <= 1) {
			return false;
		}
		g.printEdges();
		SearchTree st = g.dfs(0);
		if(st.getNumberOfVerticesFound() == g.vertices.size()) {
			return true;
		}
		return false;
		}
	/* PARAM: none
	 * RETURNS: List of List of integers that represent vertices that are connected,
	 * these are in the same components
	 * DOES: traverses through vertices to see which are connected to each other,
	 * puts these into lists, then creates lists of these lists
	*/
	public List<List<Integer>> getConnectedComponents() {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		List<Integer> tempVert = (List<Integer>) vertices;
		int counter = 0;
		while(counter < tempVert.size()) {
			List<Integer> tempList = dfs(tempVert.get(counter)).getSearchOrder();
			result.add(tempList);
			tempVert.removeAll(tempList);
			counter++;
		}
		return result;
		
	}
	/* PARAM: origin of path and destination of path
	 * RETURNS: a list of integers which shows the order
	 * of the points to travel in to get the shortest path possible
	 * DOES: uses bfs algorithm to find the shortest path possible
	*/
	public List<Integer> getPath(int origin, int destination) {
		List<Integer> path = new ArrayList<Integer>();
		SearchTree st = this.bfs(origin);
		if(!st.getSearchOrder().contains(destination)) {
			return null;
		}
		path.addAll((Collection<? extends Integer>) this.bfs(destination).getPath(origin));
		return path;
	}
	/* PARAM: none
	 * RETURNS: boolean of whether or not this is cyclic
	 * DOES: recursively looks through vertices and its neighbors
	 * to figure out if the graph contains any cycles with use of helper method
	*/
	public boolean isCyclic() {
		List<Integer> allVertices = (List<Integer>) vertices;
		if(allVertices.size() == 0) {
			return false;
		}
		int u = 0;
		int[] parent = new int[vertices.size()];
		for (int i = 0; i < parent.length; i++) {
		      parent[i] = -1;  }
		boolean[] isVisited = new boolean[vertices.size()];
		return isCyclic(u, parent, allVertices, isVisited);
		
	}
	
	/* HELPER METHOD FOR ISCYCLIC()
	 * PARAM: int u for starting point, parent array of integers, list of integers which are
	 * vertices, boolean array of if a vertex was visited or not
	 * RETURNS: boolean of whether or not this is cyclic
	 * DOES: recursively looks through vertices and its neighbors
	 * to figure out if the graph contains any cycles with use of helper method
	*/
	private boolean isCyclic(int u, int[] parent, List<Integer> allVertices, boolean[] isVisited) {
		allVertices.remove(u);
		isVisited[u] = true;
		if(allVertices.size() <= 1) {
			return false;
		}
		for (Edge e : neighbors.get(u)) { // Note that e.u is v
		      if (!isVisited[e.v]) { // e.v is w in Listing 28.8
		        parent[e.v] = u; // The parent of w is v
		        isCyclic(e.v, parent, allVertices, isVisited); // Recursive search
		      } else if(isVisited[e.v] && parent[u] != e.v) {
		    	  return true;
		      }
		    }
		return false;
		}
	/* PARAM: int u which is a vertex for find cycle for 
	 * RETURNS: list of integers of the points in the cycle if there is one, null if there isnt
	 * DOES: traverses through all the vertices and if they have neighbors, checks
	 * thru the neighbors to find a vertex that is visited but not the parent 
	*/
	public List<Integer> findCycle(int u) {
		List<Integer> allVertices = (List<Integer>) vertices;
		int[] parent = new int[vertices.size()];
		for (int i = 0; i < parent.length; i++) {
		      parent[i] = -1;  }
		boolean[] isVisited = new boolean[vertices.size()];
		List<Integer> path = new ArrayList<Integer>();
		List<List<Edge>> copyAdjMat = neighbors;
		int counter = 0;
		Stack<Integer> s = new Stack<Integer>();
		while(isVisited[counter]) {
			s.push(allVertices.get(counter));
			path.add(allVertices.get(counter));
			isVisited[counter] = true;
			counter++;
		}
		while(!s.isEmpty()) {
			if(neighbors.get(s.peek()).isEmpty()) {
				s.pop();
			} else {
				for(Edge e : neighbors.get(s.peek()))
					if(!isVisited[s.peek()]) {
					parent[e.v] = u;
					s.push(e.v);
					isVisited[s.peek()] = true;
					path.add(s.peek());
				} else if(isVisited[s.capacity()] &&  e.v != parent[u]) {
					return path; 
				}
			}
		}
		return null;
	}
	}
