package com.github.sqlitEngine.core;

import javax.swing.*;
import java.io.File;

/**
 * Created by alexander on 6/28/14.
 */
public class Main {
    private JPanel MainWindow;
    private JTree labelTree;
    private JComboBox comboBox1;

    private static JMenuBar menuBar;

    private static void addJMenu(JMenuBar menuBar, String menuName) {
        JMenu menu = new JMenu(menuName);
        menuBar.add(menu);
    }
    private static void addJMenuItem(JMenu menu, String menuItemName) {
        JMenuItem menuItem = new JMenuItem(menuItemName); //TODO Add action
        menu.add(menuItem);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Main");
        frame.setContentPane(new Main().MainWindow);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        menuBar = new JMenuBar();
        addJMenu(menuBar, "File");
        addJMenuItem(menuBar.getMenu(0), "Open");
        addJMenuItem(menuBar.getMenu(0), "Close");
        addJMenu(menuBar, "Edit");
        addJMenu(menuBar, "Help");
        frame.setJMenuBar(menuBar);
    }

}
