package com.github.sqliteManager.ui;

import com.github.sqliteManager.core.models.MyDefaultMutableTreeNode;
import com.github.sqliteManager.core.models.MyJTable;
import com.github.sqliteManager.ui.dbTree.DBTreeEngine;
import com.github.sqliteManager.ui.fileChooser.FileChooser;
import com.github.sqliteManager.ui.sqlField.SQLField;
import com.github.sqliteManager.ui.valuesList.ValuesListPanel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;


/**
 * Created by alexander on 6/29/14.
 */
public class MainWindow extends JPanel {
    public static final String PROGRAM_NAME = "SQLite Manager";
    private static final double RESIZE_WEIGHT = 0.26;
    private static final String TREE_NAME = "tree";
    private MyDefaultMutableTreeNode root;
    private DefaultTreeModel treeModel;
    private JTree tree;
    private DefaultTableModel tableModel = new DefaultTableModel();
    private MyJTable table = new MyJTable(tableModel);
    private DBTreeEngine treeEngine;

    public MainWindow() {
        JFrame mainFrame = new JFrame(PROGRAM_NAME);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        mainFrame.setPreferredSize(new Dimension((int) screenSize.getWidth(), (int) screenSize.getHeight()));
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setLeftComponent(addLeftPart());
        this.treeEngine = new DBTreeEngine(root, tree, treeModel, table, tableModel);
        new MainMenu(mainFrame, treeEngine);

        splitPane.setRightComponent(addRightPart());
        splitPane.setOneTouchExpandable(true);
        splitPane.setResizeWeight(RESIZE_WEIGHT);

        mainFrame.add(splitPane, BorderLayout.CENTER);
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.pack();
        mainFrame.setVisible(true);
    }

    private JPanel addLeftPart() {
        JPanel leftPart = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        leftPart.add(addDBPanel(), constraints);
        return leftPart;
    }

    private JSplitPane addRightPart() {
        JSplitPane rightPart = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        rightPart.setResizeWeight(0.3);
        rightPart.setTopComponent(new SQLField(treeEngine).getSQLField());
        rightPart.setBottomComponent(new ValuesListPanel(table, treeEngine).getValuesListPanel());
        rightPart.setOneTouchExpandable(true);
        return rightPart;
    }

    private JScrollPane addDBPanel() {
        JPanel treePanel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        root = new MyDefaultMutableTreeNode();
        treeModel = new DefaultTreeModel(root);
        tree = new JTree(treeModel);
        tree.setEditable(false);
        tree.setName(TREE_NAME);
        treePanel.add(tree, constraints);
        return new JScrollPane(treePanel);
    }

}
