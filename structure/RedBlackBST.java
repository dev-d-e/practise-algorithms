package structure;

public class RedBlackBST {

	private Node root;

	private class Node {

		private int key;
		private String value;
		private Node left;
		private Node right;
		private boolean color;

		public Node(int key, String value, boolean color) {
			this.key = key;
			this.value = value;
			this.color = color;
		}

	}

	private boolean isRed(Node x) {
		if (x == null) {
			return false;
		}
		return x.color == true;
	}

	private Node rotateLeft(Node x) {
		Node t = x.right;
		x.right = t.left;
		t.left = x;
		t.color = x.color;
		x.color = true;
		return t;
	}

	private Node rotateRight(Node x) {
		Node t = x.left;
		x.left = t.right;
		t.right = x;
		t.color = x.color;
		x.color = true;
		return t;
	}

	private void flipColors(Node x) {
		x.color = true;
		x.left.color = false;
		x.right.color = false;
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
			return new Node(key, value, true);
		}
		if (key < x.key) {
			x.left = put(x.left, key, value);
		} else if (key > x.key) {
			x.right = put(x.right, key, value);
		} else {
			x.value = value;
		}
		if (isRed(x.right) && !isRed(x.left)) {
			x = rotateLeft(x);
		}
		if (isRed(x.left) && isRed(x.left.left)) {
			x = rotateRight(x);
		}
		if (isRed(x.left) && isRed(x.right)) {
			flipColors(x);
		}
		return x;
	}

	public void put(int key, String value) {
		root = put(root, key, value);
		root.color = false;
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

}
