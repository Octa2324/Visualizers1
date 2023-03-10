
public class Node {
	
	 private static final boolean RED = true;
	 private static final boolean BLACK = false;
	    
        int key;
        Node left;
        Node right;
        boolean color;

        Node(int key) {
            this.key = key;
            this.color = RED;
        }
    }