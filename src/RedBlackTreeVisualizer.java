import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class RedBlackTreeVisualizer extends JFrame {
    private RedBlackTree tree;
    private int radius = 20;
    private int vGap = 50;

    public RedBlackTreeVisualizer() {
        tree = new RedBlackTree();

        JButton btInsert = new JButton("Insert");
        btInsert.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	 //int key = (int)(Math.random() * 100);
                 //tree.insert(key);
            	tree.insert(15);
            	tree.insert(2);
            	tree.insert(22);
            	tree.insert(10);
            	tree.insert(18);
            	tree.insert(3);
                repaint();
            }
        });
        
        JButton btDelete = new JButton("Delete");
        btDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //int key = (int)(Math.random() * 100);
                //tree.delete(key);
            	tree.delete(10);
            	tree.delete(15);
                repaint();
            }
        });
        
        JPanel panel = new JPanel();
        panel.add(btInsert);
        panel.add(btDelete);
        add(panel, BorderLayout.SOUTH);
        
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        displayTree(g, tree.getRoot(), getWidth() / 2, radius+60, radius+50);
    }

    private void displayTree(Graphics g, Node root, int x, int y, int radius) {
        if (root == null) {
            return;
        }

        g.setColor(root.color ? Color.RED : Color.GRAY);
        g.fillOval(x - radius, y - radius, 2 * radius, 2 * radius);

        g.setColor(Color.BLACK);
        g.drawString(root.key + "", x - 6, y + 4);

        if (root.left != null) {
            connectLeftChild(g, x, y, radius, x - radius / 2, y + radius + vGap);
            displayTree(g, root.left, x - radius / 2, y + radius + vGap, radius / 2);
        }

        if (root.right != null) {
            connectRightChild(g, x, y, radius, x + radius / 2, y + radius + vGap);
            displayTree(g, root.right, x + radius / 2, y + radius + vGap, radius / 2);
        }
    }

    private void connectLeftChild(Graphics g, int x1, int y1, int radius, int x2, int y2) {
        double d = Math.sqrt(vGap * vGap + (radius / 2.0) * (radius / 2.0));
        int x11 = (int)(x1 - radius * (vGap / d));
        int y11 = (int)(y1 + radius * (radius / 2.0 / d));
        int x21 = (int)(x2 - radius * (vGap / d));
        int y21 = (int)(y2 - radius * (radius / 2.0 / d));
    }

    private void connectRightChild(Graphics g, int x1, int y1, int radius, int x2, int y2) {
        double d = Math.sqrt(vGap * vGap + (radius / 2.0) * (radius / 2.0));
        int x11 = (int)(x1 + radius * (vGap / d));
        int y11 = (int)(y1 + radius * (radius / 2.0 / d));
        int x21 = (int)(x2 + radius * (vGap / d));
        int y21 = (int)(y2 - radius * (radius / 2.0 / d));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new RedBlackTreeVisualizer();
            frame.setTitle("RedBlackTreeVisualizer");
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(new Dimension(600, 600));
            frame.setVisible(true);
        });
    }
}

