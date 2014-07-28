package com.github.sqliteManager.ui;

import com.github.sqliteManager.core.models.MyDefaultMutableTreeNode;
import com.github.sqliteManager.ui.dbTree.DBTreeEngine;
import com.github.sqliteManager.ui.fileChooser.FileChooser;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;

/**
 * Created by alexander on 6/29/14.
 */
public class MainWindow extends JPanel {
    public static final String PROGRAM_NAME = "SQLite Manager";
    public static final String CLEAN_BUTTON_LABEL = "Clean";
    public static final String EXECUTE_BUTTON_LABEL = "Execute";
    private MyDefaultMutableTreeNode root;
    private DefaultTreeModel treeModel;
    private JTree tree;
    private DefaultTableModel tableModel = new DefaultTableModel();
    private JTable table = new JTable(tableModel);
    private static final FileChooser fileChooser = new FileChooser();


    public MainWindow() {
        JFrame frame = new JFrame(PROGRAM_NAME);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setPreferredSize(new Dimension((int)screenSize.getWidth(), (int)screenSize.getHeight()));

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setLeftComponent(addLeftPart());
        splitPane.setRightComponent(addRightPart());
        splitPane.setOneTouchExpandable(true);
        splitPane.setResizeWeight(0.26);

        DBTreeEngine treeEngine = new DBTreeEngine(root, tree, treeModel, table, tableModel); /*//TODO rewrite using setters too much args in constructor*/
        new MainMenu(frame, tree, treeModel, root, fileChooser, treeEngine); //TODO rewrite using setters too much args in constructor

        frame.add(splitPane, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
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
        rightPart.setTopComponent(addSQLTextField());
        rightPart.setBottomComponent(addValuesList());
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
        treePanel.add(tree, constraints);
        return new JScrollPane(treePanel);
    }

    private JPanel addSQLTextField() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        JTextPane textPane = new JTextPane();
        JScrollPane scrollTextPane = new JScrollPane(textPane);

        JPanel buttonPanel = new JPanel(new GridBagLayout());
        JButton buttonExecute = new JButton(CLEAN_BUTTON_LABEL);
        JButton buttonClean = new JButton(EXECUTE_BUTTON_LABEL);
        buttonPanel.add(buttonExecute);
        buttonPanel.add(buttonClean);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 1.0;
        constraints.weighty = 0.9;
        constraints.gridy = 0;
        panel.add(scrollTextPane, constraints);
        constraints.weighty = 0.0;
        constraints.gridy = 1;
        panel.add(buttonPanel, constraints);

        return panel;
    }

    private JScrollPane addValuesList() {
        return new JScrollPane(table);
    }
}
