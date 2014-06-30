package com.github.sqliteManager.gui;

import com.github.sqliteManager.core.SQLiteEngine;
import com.github.sqliteManager.core.models.Column;
import com.github.sqliteManager.core.models.Table;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;

/**
 * Created by alexander on 6/29/14.
 */
public class MainWindow {
    private JFrame frame;
    private GridBagConstraints constraints;
    private DefaultMutableTreeNode root;
    private DefaultTreeModel treeModel;
    private JTree tree;
    private JList list;
    private JButton buttonExecute;
    private JButton buttonClean;
    private JTextPane textPane1;
    private JPanel mainPanel;
    private JPanel leftPart;
    private JPanel rightPart;
    private JMenuBar menuBar;
    private SQLiteEngine sqLiteEngine;

    public void initMainWindow() {
        frame = new JFrame("MainWindow");
        frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        mainPanel = new JPanel(new GridBagLayout());
        constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weighty = 1.0;
        leftPart = new JPanel(new GridBagLayout());
        leftPart.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1, true));
        rightPart = new JPanel(new GridBagLayout());
        rightPart.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1, true));

        constraints.weightx = 0.2;
        mainPanel.add(leftPart, constraints);
        constraints.weightx = 0.8;
        mainPanel.add(rightPart,constraints);

        initMenu(frame);
        sqLiteEngine = new SQLiteEngine("database.sqlite");
        sqLiteEngine.openDB();
        addDBPanel(leftPart);
        addSQLTextField(rightPart);
        addSQLButtons(rightPart);
        addValuestList(rightPart);

        frame.getContentPane().add(mainPanel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void initMenu(JFrame frame) {
        menuBar = new JMenuBar();
        addJMenu(menuBar, "File");
        addJMenuItem(menuBar.getMenu(0), "Open");
        addJMenuItem(menuBar.getMenu(0), "Close");
        addJMenu(menuBar, "Edit");
        addJMenu(menuBar, "Help");
        frame.setJMenuBar(menuBar);
    }

    private void addJMenu(JMenuBar menuBar, String menuName) {
        JMenu menu = new JMenu(menuName);
        menuBar.add(menu);
    }
    private void addJMenuItem(JMenu menu, String menuItemName) {
        JMenuItem menuItem = new JMenuItem(menuItemName); //TODO Add action
        menu.add(menuItem);
    }

    private void addDBPanel(JPanel parentPanel) {
        root = new DefaultMutableTreeNode(sqLiteEngine.getFileName());
        this.fillDBTree(root);
        treeModel = new DefaultTreeModel(root);
        tree = new JTree(treeModel);
        JScrollPane scrollPane = new JScrollPane(tree);
        parentPanel.add(scrollPane, constraints);
    }

    private void fillDBTree(DefaultMutableTreeNode root) {
        for (Table table : sqLiteEngine.getTableList().values()) {
            DefaultMutableTreeNode tableNode = new DefaultMutableTreeNode(table.getTableName());
            root.add(tableNode);
            for (Column column : sqLiteEngine.getColumnList(table).values()) {
                DefaultMutableTreeNode columnNode = new DefaultMutableTreeNode(column.getColumnName());
                tableNode.add(columnNode);
            }
        }
    }

    private void addSQLTextField(JPanel parentPanel) {
        textPane1 = new JTextPane();
        JScrollPane scrollPane = new JScrollPane(textPane1);
        constraints.weightx = 1.0;
        constraints.weighty = 0.3;
        constraints.gridx = 0;
        constraints.gridy = 0;
        parentPanel.add(scrollPane, constraints);
    }

    private void addSQLButtons(JPanel parentPanel) {
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        buttonExecute = new JButton("Clean");
        buttonClean = new JButton("Execute");
        buttonPanel.add(buttonExecute);
        buttonPanel.add(buttonClean);
        constraints.weightx = 1.0;
        constraints.weighty = 0.01;
        constraints.gridx = 0;
        constraints.gridy = 1;
        parentPanel.add(buttonPanel, constraints);
    }

    private void addValuestList(JPanel parentPanel) {
        list = new JList();
        JScrollPane scrollPane = new JScrollPane(list);
        constraints.weightx = 1.0;
        constraints.weighty = 0.7;
        constraints.gridx = 0;
        constraints.gridy = 2;
        parentPanel.add(scrollPane, constraints);
    }

}
