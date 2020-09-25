package structure;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Digraph {

	private final int vertices;

	private int edges;

	private List<Integer>[] adj;

	@SuppressWarnings("unchecked")
	public Digraph(int vertices) {
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
		edges++;
	}

	public List<Integer> adj(int i) {
		return adj[i];
	}

	public Digraph reverse() {
		Digraph r = new Digraph(vertices);
		for (int i = 0; i < vertices; i++) {
			for (int j : adj[i]) {
				r.addEdge(j, i);
			}
		}
		return r;
	}

	private boolean[] marked;

	private int[] edgeTo;

	private boolean[] onCycle;

	private LinkedList<Integer> cycle;

	private void depthFirstSearch(int i) {
		marked[i] = true;
		onCycle[i] = true;
		for (int j : adj(i)) {
			if (hasCycle()) {
				return;
			} else if (!marked[j]) {
				edgeTo[j] = i;
				depthFirstSearch(j);
			} else if (onCycle[j]) {
				cycle = new LinkedList<>();
				for (int k = i; k != j; k = edgeTo[k]) {
					cycle.addFirst(k);
				}
				cycle.addFirst(j);
				cycle.addFirst(i);
			}
		}
		onCycle[i] = false;
	}

	public boolean hasCycle() {
		return cycle != null;
	}

	public LinkedList<Integer> cycle() {
		return cycle;
	}

	public void directedCycle() {
		marked = new boolean[vertices];
		edgeTo = new int[vertices];
		onCycle = new boolean[vertices];
		for (int i = 0; i < vertices; i++) {
			if (!marked[i]) {
				depthFirstSearch(i);
			}
		}
	}

	private LinkedList<Integer> pre = new LinkedList<>();

	private LinkedList<Integer> post = new LinkedList<>();

	private LinkedList<Integer> reversePost = new LinkedList<>();

	public void depthFirstOrder() {
		marked = new boolean[vertices];
		for (int i = 0; i < vertices; i++) {
			if (!marked[i]) {
				depthFirstOrder(i);
			}
		}
	}

	private void depthFirstOrder(int i) {
		pre.add(i);
		marked[i] = true;
		for (int j : adj(i)) {
			if (!marked[j]) {
				depthFirstOrder(j);
			}
		}
		post.add(i);
		reversePost.addFirst(i);
	}

	public LinkedList<Integer> pre() {
		return pre;
	}

	public LinkedList<Integer> post() {
		return post;
	}

	public LinkedList<Integer> reversePost() {
		return reversePost;
	}

	private int[] scc;
	private int count;

	public void kosarajuSCC() {
		marked = new boolean[vertices];
		scc = new int[vertices];
		Digraph r = reverse();
		r.depthFirstOrder();
		for (int i : r.reversePost()) {
			if (!marked[i]) {
				kosarajuSCC(i);
				count++;
			}
		}
	}

	private void kosarajuSCC(int i) {
		marked[i] = true;
		scc[i] = count;
		for (int j : adj(i)) {
			if (!marked[j]) {
				kosarajuSCC(j);
			}
		}
	}

	public boolean stronglyConnected(int i, int j) {
		return scc[i] == scc[j];
	}

}
