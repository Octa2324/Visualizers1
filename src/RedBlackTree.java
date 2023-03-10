

public class RedBlackTree {
    private static final boolean RED = true;
    private static final boolean BLACK = false;
    
    private Node root;

    private boolean isRed(Node node) {
        if (node == null) {
            return BLACK;
        }
        return node.color == RED;
    }

    public void insert(int key) {
        root = insert(root, key);
        root.color = BLACK;
    }

    private Node insert(Node node, int key) {
        if (node == null) {
            return new Node(key);
        }

        if (key < node.key) {
            node.left = insert(node.left, key);
        } else if (key > node.key) {
            node.right = insert(node.right, key);
        } else {
            node.key = key;
        }

        if (isRed(node.right) && !isRed(node.left)) {
            node = rotateLeft(node);
        }
        if (isRed(node.left) && isRed(node.left.left)) {
            node = rotateRight(node);
        }
        if (isRed(node.left) && isRed(node.right)) {
            flipColors(node);
        }

        return node;
    }

    private Node rotateLeft(Node node) {
        Node right = node.right;
        node.right = right.left;
        right.left = node;
        right.color = node.color;
        node.color = RED;
        return right;
    }

    private Node rotateRight(Node node) {
        Node left = node.left;
        node.left = left.right;
        left.right = node;
        left.color = node.color;
        node.color = RED;
        return left;
    }

    private void flipColors(Node node) {
        node.color = !node.color;
        node.left.color = !node.left.color;
        node.right.color = !node.right.color;
    }
    
    public void printTree() {
        printTree(root);
    }

    private void printTree(Node node) {
        if (node == null) {
            return;
        }
        printTree(node.left);
        System.out.printf("%d (%s)", node.key, node.color ? "R" : "B");
        printTree(node.right);
    }

	public Node getRoot() {
		return this.root;
	}
	public void delete(int key) {
	    if (root == null) {
	        return;
	    }

	    Node current = root;
	    Node parent = null;
	    boolean isLeftChild = true;

	    while (current.key != key) {
	        parent = current;
	        if (key < current.key) {
	            isLeftChild = true;
	            current = current.left;
	        } else {
	            isLeftChild = false;
	            current = current.right;
	        }
	        if (current == null) {
	            return;
	        }
	    }

	    if (current.left == null && current.right == null) {
	        if (current == root) {
	            root = null;
	        } else if (isLeftChild) {
	            parent.left = null;
	        } else {
	            parent.right = null;
	        }
	    } else if (current.right == null) {
	        if (current == root) {
	            root = current.left;
	        } else if (isLeftChild) {
	            parent.left = current.left;
	        } else {
	            parent.right = current.left;
	        }
	    } else if (current.left == null) {
	        if (current == root) {
	            root = current.right;
	        } else if (isLeftChild) {
	            parent.left = current.right;
	        } else {
	            parent.right = current.right;
	        }
	    } else {
	        Node successor = getSuccessor(current);
	        if (current == root) {
	            root = successor;
	        } else if (isLeftChild) {
	            parent.left = successor;
	        } else {
	            parent.right = successor;
	        }
	        successor.left = current.left;
	    }

	    fixTree(root);
	}

	private Node getSuccessor(Node node) {
	    Node parent = node;
	    Node s = node;
	    Node current = node.right;
	    while (current != null) {
	        parent = s;
	        s = current;
	        current = current.left;
	    }

	    if (s != node.right) {
	        parent.left = s.right;
	        s.right = node.right;
	    }
	    return s;
	}

	private void fixTree(Node node) {
	    if (node == null) {
	        return;
	    }

	    if (isRed(node.right) && !isRed(node.left)) {
	        node = rotateLeft(node);
	    }
	    if (isRed(node.left) && isRed(node.left.left)) {
	        node = rotateRight(node);
	    }
	    if (isRed(node.left) && isRed(node.right)) {
	        flipColors(node);
	    }

	    fixTree(node.left);
	    fixTree(node.right);
	}

}
