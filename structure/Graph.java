package structure;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Graph {

	private final int vertices;

	private int edges;

	private List<Integer>[] adj;

	@SuppressWarnings("unchecked")
	public Graph(int vertices) {
		this.vertices = vertices;
		this.edges = 0;
		adj = (List<Integer>[]) new List[vertices];
		for (int i = 0; i < vertices; i++) {
			adj[i] = new ArrayList<Integer>();
		}
	}

	public int vertices() {
		return vertices;
	}

	public int edges() {
		return edges;
	}

	public void addEdge(int j, int k) {
		adj[j].add(k);
		adj[k].add(j);
		edges++;
	}

	public List<Integer> adj(int i) {
		return adj[i];
	}

	private boolean[] marked;

	private int[] edgeTo;

	private int s;

	private void depthFirstSearch(int i) {
		marked[i] = true;
		for (int j : adj(i)) {
			if (!marked[j]) {
				edgeTo[j] = i;
				depthFirstSearch(j);
			}
		}
	}

	public void depthFirstPaths(int s) {
		this.s = s;
		marked = new boolean[vertices];
		edgeTo = new int[vertices];
		depthFirstSearch(s);
	}

	public boolean hasPathTo(int i) {
		return marked[i];
	}

	public List<Integer> pathTo(int i) {
		if (!hasPathTo(i)) {
			return null;
		}
		LinkedList<Integer> list = new LinkedList<>();
		for (int j = i; j != s; j = edgeTo[j]) {
			list.addFirst(j);
		}
		list.addFirst(s);
		return list;
	}

	private void breadthFirstSearch(int i) {
		LinkedList<Integer> queue = new LinkedList<>();
		marked[i] = true;
		queue.add(i);
		while (!queue.isEmpty()) {
			int j = queue.poll();
			for (int k : adj(j)) {
				if (!marked[k]) {
					edgeTo[k] = j;
					marked[k] = true;
					queue.add(k);
				}
			}
		}
	}

	public void breadthFirstPaths(int s) {
		this.s = s;
		marked = new boolean[vertices];
		edgeTo = new int[vertices];
		breadthFirstSearch(s);
	}

}
