import sun.text.resources.tr.JavaTimeSupplementary_tr;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;

/**
 * Created by alexander on 6/29/14.
 */
public class Test {
    public static void main(String[] args) {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("root");
        DefaultTreeModel treeModel = new DefaultTreeModel(root);
        DefaultMutableTreeNode root1 = new DefaultMutableTreeNode("root1");
        DefaultTreeModel treeModel1 = new DefaultTreeModel(root1);
        JTree tree = new JTree(treeModel);
        JTree tree1 = new JTree(treeModel1);

        // The JTree can get big, so allow it to scroll
        JScrollPane scrollpane = new JScrollPane(tree);
        scrollpane.setPreferredSize(new Dimension(100,100));
        JScrollPane scrollPane1 = new JScrollPane(tree1);

        JPanel mainPanel = new JPanel();
        JPanel leftPanel = new JPanel();
        JPanel rightPanel = new JPanel();

        mainPanel.add(leftPanel);
        mainPanel.add(rightPanel);

        leftPanel.add(scrollPane1);
        rightPanel.add(scrollpane);

        // Display it all in a window and make the window appear
        JFrame frame = new JFrame("FileTreeDemo");
        frame.setLayout(new GridLayout(2,1));
        frame.add(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000,1000);
        frame.setVisible(true);
    }
}
