package com.github.sqlitEngine.gui;

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
    private JTree tree1;
    private JList list1;
    private JButton button1;
    private JButton button2;
    private JTextPane textPane1;
    private JPanel mainPanel;
    private JPanel leftPart;
    private JPanel rightPart;
    private JMenuBar menuBar;

    public void initMainWindow() {
        frame = new JFrame("MainWindow");
        frame.setSize(800, 600);
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
        addDBPanel(leftPart);
        addSQLTextField(rightPart);
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
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("rooooooooot");
        DefaultTreeModel treeModel = new DefaultTreeModel(root);
        JPanel panel = new JPanel(new GridBagLayout());
        tree1 = new JTree(treeModel);
        JScrollPane scrollPane = new JScrollPane(tree1);
        panel.add(scrollPane, constraints);
        parentPanel.add(panel, constraints);
    }

    private void addSQLTextField(JPanel parentPanel) {
        textPane1 = new JTextPane();
        JPanel panel = new JPanel(new GridBagLayout());
        JScrollPane scrollPane = new JScrollPane(textPane1);
        constraints.weightx = 0.8;
        constraints.weighty = 1.0;
        panel.add(scrollPane, constraints);
        parentPanel.add(panel, constraints);
/*        JPanel buttonPanel = new JPanel(new GridBagLayout());
        button1 = new JButton("Clean");
        button2 = new JButton("Execute");
        buttonPanel.add(button1);
        buttonPanel.add(button2);
        constraints.weightx = 1.0;
        constraints.weighty = 0.01;
        parentPanel.add(buttonPanel, constraints);*/
    }

    private void addValuestList(JPanel parentPanel) {
        list1 = new JList();
        JPanel panel = new JPanel(new GridBagLayout());
        JScrollPane scrollPane = new JScrollPane(list1);
        constraints.weightx = 0.2;
        constraints.weighty = 1.0;
        panel.add(scrollPane, constraints);
        parentPanel.add(panel, constraints);
    }

}
