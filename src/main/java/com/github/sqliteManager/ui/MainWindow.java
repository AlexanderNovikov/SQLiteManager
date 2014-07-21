package com.github.sqliteManager.ui;

import com.github.sqliteManager.core.models.MyDefaultMutableTreeNode;
import com.github.sqliteManager.ui.dbTree.DBTreeEngine;
import com.github.sqliteManager.ui.fileChooser.FileChooser;
import com.github.sqliteManager.ui.valuesList.ValuesList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;

/**
 * Created by alexander on 6/29/14.
 */
public class MainWindow {
    public static final String PROGRAM_NAME = "SQLite Manager";
    public static final String CLEAN_BUTTON_LABEL = "Clean";
    public static final String EXECUTE_BUTTON_LABEL = "Execute";
    private JFrame frame;
    private JPanel mainPanel, leftPart ,rightPart;
    private GridBagConstraints constraints;
    private MyDefaultMutableTreeNode root;
    private DefaultTreeModel treeModel;
    private JTree tree;
    private DefaultTableModel tableModel = new DefaultTableModel();
    private JTable table = new JTable(tableModel);
    private JButton buttonExecute, buttonClean;
    private JTextPane textPane1;
    private static final FileChooser fileChooser = new FileChooser();
    private DBTreeEngine treeEngine;


    public MainWindow() {
        frame = new JFrame(PROGRAM_NAME);
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

        addDBPanel(leftPart);
        treeEngine = new DBTreeEngine(root, tree, treeModel, table, tableModel); //TODO rewrite using setters too much args in constructor
        new MainMenu(frame, tree, treeModel, root, fileChooser, treeEngine); //TODO rewrite using setters too much args in constructor
        addSQLTextField(rightPart);
        addSQLButtons(rightPart);
        addValuesList(rightPart);

        frame.getContentPane().add(mainPanel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void addDBPanel(JPanel parentPanel) {
        root = new MyDefaultMutableTreeNode();
        treeModel = new DefaultTreeModel(root);
        tree = new JTree(treeModel);
        tree.setEditable(true);
        JScrollPane scrollPane = new JScrollPane(tree);
        parentPanel.add(scrollPane, constraints);
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
        buttonExecute = new JButton(CLEAN_BUTTON_LABEL);
        buttonClean = new JButton(EXECUTE_BUTTON_LABEL);
        buttonPanel.add(buttonExecute);
        buttonPanel.add(buttonClean);
        constraints.weightx = 1.0;
        constraints.weighty = 0.01;
        constraints.gridx = 0;
        constraints.gridy = 1;
        parentPanel.add(buttonPanel, constraints);
    }

    private void addValuesList(JPanel parentPanel) {
        JScrollPane scrollPane = new JScrollPane(table);
        constraints.weightx = 1.0;
        constraints.weighty = 0.7;
        constraints.gridx = 0;
        constraints.gridy = 2;
        parentPanel.add(scrollPane, constraints);
    }
}
