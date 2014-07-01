package com.github.sqliteManager.ui;

import com.github.sqliteManager.core.SQLiteEngine;
import com.github.sqliteManager.ui.dbTree.DBTreeEngine;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Created by alexander on 01/07/14.
 */
public class MainMenu {
    private JFrame frame;
    private JTree tree;
    private DefaultTreeModel treeModel;
    private DefaultMutableTreeNode root;
    private JMenuBar mainMenu;
    private SQLiteEngine sqLiteEngine;
    private File selectedFile;
    private DBTreeEngine treeEngine;

    public MainMenu(JFrame frame, JTree tree, DefaultTreeModel treeModel, DefaultMutableTreeNode root) {
        this.frame = frame;
        this.tree = tree;
        this.treeModel = treeModel;
        this.root = root;
        initMainMenu();
    }

    private void initMainMenu() {
        mainMenu = new JMenuBar();
        addMenu(mainMenu, "File");
        addMenuItem(mainMenu.getMenu(0), "Open database", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FileChooser fileChooser = new FileChooser();
                selectedFile = fileChooser.getSelectedFile();
                if (selectedFile != null) {
                    sqLiteEngine = new SQLiteEngine(selectedFile);
                    sqLiteEngine.openDB();
                    treeEngine = new DBTreeEngine(sqLiteEngine, root, tree, treeModel);
                    treeEngine.cleanDBTree();
                    treeEngine.fillDBTree();
                    treeEngine.addPopupMenu();
                }
            }
        });
        addMenuItem(mainMenu.getMenu(0), "Close database", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sqLiteEngine.closeDB();
                treeEngine.cleanDBTree();
            }
        });
        addMenuItem(mainMenu.getMenu(0), "Exit program", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        addMenu(mainMenu, "Edit");
        addMenu(mainMenu, "Help");
        frame.setJMenuBar(mainMenu);
    }

    private void addMenu(JMenuBar menuBar, String menuName) {
        JMenu menu = new JMenu(menuName);
        menuBar.add(menu);
    }
    private void addMenuItem(JMenu menu, String menuItemName, ActionListener action) {
        JMenuItem menuItem = new JMenuItem(menuItemName);
        menuItem.addActionListener(action);
        menu.add(menuItem);
    }
}
