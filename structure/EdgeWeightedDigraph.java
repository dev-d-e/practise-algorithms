package structure;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class EdgeWeightedDigraph {

	private final int vertices;

	private int edges;

	private List<Edge>[] adj;

	private List<Edge> allEdges;

	private final Comparator<Edge> comparator = new Comparator<>() {

		@Override
		public int compare(Edge o1, Edge o2) {
			return o1.w > o2.w ? 1 : o1.w == o2.w ? 0 : -1;
		}

	};

	@SuppressWarnings("unchecked")
	public EdgeWeightedDigraph(int vertices) {
		this.vertices = vertices;
		this.edges = 0;
		adj = new List[vertices];
		for (int i = 0; i < vertices; i++) {
			adj[i] = new ArrayList<>();
		}
		allEdges = new ArrayList<>();
	}

	public class Edge {

		int j;
		int k;
		double w;

		Edge(int j, int k, double w) {
			this.j = j;
			this.k = k;
			this.w = w;
		}

	}

	public int vertices() {
		return vertices;
	}

	public int edges() {
		return edges;
	}

	public void addEdge(int j, int k, double w) {
		adj[j].add(new Edge(j, k, w));
		edges++;
		allEdges.add(new Edge(j, k, w));
	}

	public List<Edge> adj(int i) {
		return adj[i];
	}

	private Edge[] edgeTo;

	private double[] distTo;

	private PriorityQueue<Edge> order;

	private void relax(int i) {
		for (Edge e : adj(i)) {
			if (distTo[e.k] > distTo[e.j] + e.w) {
				order.remove(edgeTo[e.k]);
				distTo[e.k] = distTo[e.j] + e.w;
				edgeTo[e.k] = e;
				e.w = distTo[e.k];
				order.add(e);
			}
		}
	}

	public void dijkstraSP(int s) {
		edgeTo = new Edge[vertices];
		distTo = new double[vertices];
		for (int i = 0; i < vertices; i++) {
			distTo[i] = Double.POSITIVE_INFINITY;
		}
		distTo[s] = 0.0;
		order = new PriorityQueue<>(comparator);
		order.add(new Edge(s, s, 0.0));
		while (order.size() > 0) {
			relax(order.poll().k);
		}
	}

	public boolean hasPathTo(int i) {
		return distTo[i] < Double.POSITIVE_INFINITY;
	}

	public List<Edge> pathTo(int i) {
		if (!hasPathTo(i)) {
			return null;
		}
		LinkedList<Edge> list = new LinkedList<>();
		for (Edge e = edgeTo[i]; e != null; e = edgeTo[e.j]) {
			list.addFirst(e);
		}
		return list;
	}

	private boolean[] marked;

	private LinkedList<Integer> reversePost = new LinkedList<>();

	private void depthFirstOrder(int i) {
		marked[i] = true;
		for (Edge e : adj(i)) {
			if (!marked[e.k]) {
				depthFirstOrder(e.k);
			}
		}
		reversePost.addFirst(i);
	}

	public void acyclicSP(int s) {
		marked = new boolean[vertices];
		edgeTo = new Edge[vertices];
		distTo = new double[vertices];
		for (int i = 0; i < vertices; i++) {
			distTo[i] = Double.POSITIVE_INFINITY;
		}
		distTo[s] = 0.0;
		depthFirstOrder(s);
		for (int i : reversePost) {
			for (Edge e : adj(i)) {
				if (distTo[e.k] > distTo[e.j] + e.w) {
					distTo[e.k] = distTo[e.j] + e.w;
					edgeTo[e.k] = e;
				}
			}
		}
	}

	public void bellmanFordSP(int s) {
		edgeTo = new Edge[vertices];
		distTo = new double[vertices];
		for (int i = 0; i < vertices; i++) {
			distTo[i] = Double.POSITIVE_INFINITY;
		}
		distTo[s] = 0.0;
		for (int i = 0; i < vertices; i++) {
			for (int j = 0; j < vertices; j++) {
				for (Edge e : adj(j)) {
					if (distTo[e.k] > distTo[e.j] + e.w) {
						distTo[e.k] = distTo[e.j] + e.w;
						edgeTo[e.k] = e;
					}
				}
			}
		}
	}

}
