import javax.swing.*;
import java.awt.*;

public class BSTVisualizer extends JFrame {

    private static final int WIDTH = 600;
    private static final int HEIGHT = 300;
    private static final int TOP_MARGIN = 50;
    private static final int LEFT_MARGIN = 50;
    private static final int BOTTOM_MARGIN = 50;
    private static final int RIGHT_MARGIN = 50;
    private static final int HORIZONTAL_SPACING = 50;
    private static final int VERTICAL_SPACING = 50;
    private static final int NODE_WIDTH = 50;
    private static final int NODE_HEIGHT = 20;
    private static final Color NODE_COLOR = Color.GRAY;
    private static final Color TEXT_COLOR = Color.WHITE;

    private BSTNode root;

    public BSTVisualizer(BSTNode root) {
        this.root = root;

        setTitle("BST Visualizer");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        drawTree(g, root, WIDTH / 2, TOP_MARGIN, HORIZONTAL_SPACING);
    }

    private void drawTree(Graphics g, BSTNode node, int x, int y, int spacing) {
        if (node == null) {
            return;
        }

        g.setColor(NODE_COLOR);
        g.fillOval(x - NODE_WIDTH / 2, y - NODE_HEIGHT / 2, NODE_WIDTH, NODE_HEIGHT);
        g.setColor(TEXT_COLOR);
        g.drawString(node.getKey() + "", x - NODE_WIDTH / 4, y + NODE_HEIGHT / 4);

        int xS = x - spacing;
        int yS = y + VERTICAL_SPACING;
        int xD = x + spacing;
        int yD = y + VERTICAL_SPACING;

        drawTree(g, node.getLeft(), xS, yS, spacing / 2);
        drawTree(g, node.getRight(), xD, yD, spacing / 2);

        g.setColor(NODE_COLOR);
        g.drawLine(x, y, xS, yS);
        g.drawLine(x, y, xD, yD);
    }

    public static void main(String[] args) {
        BSTNode root = new BSTNode(8);
        root.insert(3);
        root.insert(10);
        root.insert(1);
        root.insert(6);
        root.insert(14);
      
       // root.delete(3,null);
        System.out.println(root.Maximum());
        System.out.println(root.Minimum());
        
        BSTVisualizer visualizer = new BSTVisualizer(root);
    }
}

class BSTNode {
    private int key;
    private BSTNode left;
    private BSTNode right;

    public BSTNode(int value) {
        this.key = value;
        this.left = null;
        this.right = null;
    }

    public int getKey() {
        return key;
    }

    public BSTNode getLeft() {
        return left;
    }

    public void setLeft(BSTNode left) {
        this.left = left;
    }

    public BSTNode getRight() {
        return right;
    }

    public void setRight(BSTNode right) {
        this.right = right;
    }
    public void insert(int value) {
        if (value < this.key) {
            if (left == null) {
                left = new BSTNode(value);
            } else {
                left.insert(value);
            }
        } else {
            if (right == null) {
                right = new BSTNode(value);
            } else {
                right.insert(value);
            }
        }
    }
    public void delete(int value, BSTNode parent) {
        BSTNode current = this;
        while (current != null && current.key != value) {
            parent = current;
            if (value < current.key) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        if (current == null) {
            return;
        }
        if (current.left == null && current.right == null) {
            if (parent == null) {
                this.key = 0;
                this.left = null;
                this.right = null;
            } else if (parent.left == current) {
                parent.left = null;
            } else {
                parent.right = null;
            }
        }
        else if (current.left == null || current.right == null) {
            BSTNode child = (current.left != null) ? current.left : current.right;
            if (parent == null) {
                this.key = child.key;
                this.left = child.left;
                this.right = child.right;
            } else if (parent.left == current) {
                parent.left = child;
            } else {
                parent.right = child;
            }
        }
        else {
            BSTNode successor = Successor(current);
            if (parent == null) {
                this.key = successor.key;
                this.left = current.left;
                this.right = successor.right;
            } else if (parent.left == current) {
                parent.left = successor;
            } else {
                parent.right = successor;
            }
            successor.left = current.left;
        }}
    public BSTNode Successor(BSTNode node) {
        BSTNode parent = node;
        BSTNode current = node.right;
        while (current.left != null) {
            parent = current;
            current = current.left;
        }
        if (current != node.right) {
            parent.left = current.right;
            current.right = node.right;
        }
        return current;
    }
    
    public BSTNode Predecessor(int val) {
        BSTNode current = this;
        BSTNode predecessor = null;
        while (current != null) {
            if (current.key == val) {
                if (current.left != null) {
                    predecessor = current.left;
                    while (predecessor.right != null) {
                        predecessor = predecessor.right;
                    }
                }
                break;
            } else if (val < current.key) {
                current = current.left;
            } else {
                predecessor = current;
                current = current.right;
            }
        }
        return predecessor;
    }
    
    public int Minimum() {
        BSTNode current = this;
        while (current.left != null) {
            current = current.left;
        }
        return current.key;
    }
    public int Maximum() {
        BSTNode current = this;
        while (current.right != null) {
            current = current.right;
        }
        return current.key;
    }
}
