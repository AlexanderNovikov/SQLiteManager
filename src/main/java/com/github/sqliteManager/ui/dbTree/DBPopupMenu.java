package com.github.sqliteManager.ui.dbTree;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by alexander on 01/07/14.
 */
public class DBPopupMenu extends DBTreePopupMenu {
    private static final String ADD_TABLE_LABEL = "Add table";
    private JPopupMenu menu;
    private DefaultMutableTreeNode root;
    private JTree tree;
    private DefaultTreeModel treeModel;
    private DBTreeEngine treeEngine;

    public DBPopupMenu(DefaultMutableTreeNode root, final JTree tree, DefaultTreeModel treeModel) {
        this.root = root;
        this.tree = tree;
        this.treeModel = treeModel;
        treeEngine = new DBTreeEngine(this.root, this.tree, this.treeModel);
        menu = new JPopupMenu();
        addMenuItem(menu, ADD_TABLE_LABEL, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                treeEngine.addNode("test");
            }
        });
    }

    public JPopupMenu getMenu() {
        return menu;
    }
}
