package structure;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class EdgeWeightedGraph {

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
	public EdgeWeightedGraph(int vertices) {
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
		adj[k].add(new Edge(k, j, w));
		edges++;
		allEdges.add(new Edge(j, k, w));
	}

	public List<Edge> adj(int i) {
		return adj[i];
	}

	private boolean[] marked;

	private Edge[] edgeTo;

	private double[] distTo;

	private PriorityQueue<Edge> order;

	private void visit(int i) {
		marked[i] = true;
		for (Edge e : adj(i)) {
			if (marked[e.k]) {
				continue;
			}
			if (e.w < distTo[e.k]) {
				order.remove(edgeTo[e.k]);
				edgeTo[e.k] = e;
				distTo[e.k] = e.w;
				order.add(e);
			}
		}
	}

	public List<Edge> primMST() {
		marked = new boolean[vertices];
		edgeTo = new Edge[vertices];
		distTo = new double[vertices];
		for (int i = 0; i < vertices; i++) {
			distTo[i] = Double.POSITIVE_INFINITY;
		}
		order = new PriorityQueue<>(comparator);
		order.add(new Edge(0, 0, 0.0));
		while (order.size() > 0) {
			visit(order.poll().k);
		}
		List<Edge> MST = new ArrayList<>();
		for (int i = 0; i < edgeTo.length; i++) {
			if (edgeTo[i] != null) {
				MST.add(edgeTo[i]);
			}
		}
		return MST;
	}

	private int[] cc;

	private void union(int j, int k) {
		if (cc[j] != cc[k]) {
			int c = cc[k];
			for (int i = 0; i < vertices; i++) {
				if (cc[i] == c) {
					cc[i] = cc[j];
				}
			}
		}
	}

	public List<Edge> kruskalMST() {
		cc = new int[vertices];
		for (int i = 0; i < vertices; i++) {
			cc[i] = i;
		}
		order = new PriorityQueue<>(comparator);
		for (Edge e : allEdges) {
			order.add(e);
		}
		List<Edge> MST = new ArrayList<>();
		while (order.size() > 0 && MST.size() < vertices - 1) {
			Edge e = order.poll();
			if (cc[e.j] != cc[e.k]) {
				union(e.j, e.k);
				MST.add(e);
			}
		}
		return MST;
	}

}
