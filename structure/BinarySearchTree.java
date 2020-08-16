package structure;

import java.util.LinkedList;
import java.util.List;

public class BinarySearchTree {

	private Node root;

	private class Node {

		private int key;
		private String value;
		private Node left;
		private Node right;

		public Node(int key, String value) {
			this.key = key;
			this.value = value;
		}

	}

	private int size(Node x) {
		if (x == null) {
			return 0;
		}
		return size(x.left) + size(x.right) + 1;
	}

	public int size() {
		return size(root);
	}

	private String get(Node x, int key) {
		if (x == null) {
			return null;
		}
		if (key < x.key) {
			return get(x.left, key);
		} else if (key > x.key) {
			return get(x.right, key);
		} else {
			return x.value;
		}
	}

	public String get(int key) {
		return get(root, key);
	}

	private Node put(Node x, int key, String value) {
		if (x == null) {
			return new Node(key, value);
		}
		if (key < x.key) {
			x.left = put(x.left, key, value);
		} else if (key > x.key) {
			x.right = put(x.right, key, value);
		} else {
			x.value = value;
		}
		return x;
	}

	public void put(int key, String value) {
		root = put(root, key, value);
	}

	private Node min(Node x) {
		if (x.left == null) {
			return x;
		}
		return min(x.left);
	}

	public int min() {
		return min(root).key;
	}

	private Node max(Node x) {
		if (x.right == null) {
			return x;
		}
		return max(x.right);
	}

	public int max() {
		return max(root).key;
	}

	private Node select(Node x, int i) {
		if (x == null) {
			return null;
		}
		int t = size(x.left);
		if (t > i) {
			return select(x.left, i);
		} else if (t < i) {
			return select(x.right, i - t - 1);
		} else {
			return x;
		}
	}

	public int select(int i) {
		return select(root, i).key;
	}

	private int rank(Node x, int key) {
		if (x == null) {
			return 0;
		}
		if (key < x.key) {
			return rank(x.left, key);
		} else if (key > x.key) {
			return 1 + size(x.left) + rank(x.right, key);
		} else {
			return size(x.left);
		}
	}

	public int rank(int key) {
		return rank(root, key);
	}

	private Node deleteMin(Node x) {
		if (x.left == null) {
			return x.right;
		}
		x.left = deleteMin(x.left);
		return x;
	}

	public void deleteMin() {
		root = deleteMin(root);
	}

	private Node delete(Node x, int key) {
		if (x == null) {
			return null;
		}
		if (key < x.key) {
			x.left = delete(x.left, key);
		} else if (key > x.key) {
			x.right = delete(x.right, key);
		} else {
			if (x.right == null) {
				return x.left;
			}
			if (x.left == null) {
				return x.right;
			}
			Node t = x;
			x = min(t.right);
			x.right = deleteMin(t.right);
			x.left = t.left;
		}
		return x;
	}

	public void delete(int key) {
		root = delete(root, key);
	}

	private void keys(Node x, List<Integer> list, int m, int n) {
		if (x == null) {
			return;
		}
		if (m < x.key) {
			keys(x.left, list, m, n);
		}
		if (m <= x.key && n >= x.key) {
			list.add(x.key);
		}
		if (n > x.key) {
			keys(x.right, list, m, n);
		}
	}

	public List<Integer> keys(int m, int n) {
		List<Integer> list = new LinkedList<>();
		keys(root, list, m, n);
		return list;
	}

	public List<Integer> keys() {
		return keys(min(), max());
	}

}
