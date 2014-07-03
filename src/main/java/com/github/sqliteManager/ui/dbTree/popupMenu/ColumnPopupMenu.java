package com.github.sqliteManager.ui.dbTree.popupMenu;

import com.github.sqliteManager.core.SQLiteEngine;
import com.github.sqliteManager.ui.dbTree.DBTreeEngine;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by alexander on 01/07/14.
 */
public class ColumnPopupMenu extends DBTreePopupMenu {
    private static final String RENAME_COLUMN_LABEL = "Rename column";
    private static final String DELETE_COLUMN_LABEL = "Delete column";
    private JPopupMenu menu;
    private JTree tree;
    private SQLiteEngine sqLiteEngine;
    private DBTreeEngine dbTreeEngine;
    private String clickedItem;
    private String clickedItemParent;

    public ColumnPopupMenu(JTree tree, SQLiteEngine sqLiteEngine, DBTreeEngine dbTreeEngine, String clickedItem, String clickedItemParent) {
        super(tree, sqLiteEngine, dbTreeEngine);
        this.menu = new JPopupMenu();
        this.tree = tree;
        this.sqLiteEngine = sqLiteEngine;
        this.dbTreeEngine = dbTreeEngine;
        this.clickedItem = clickedItem;
        this.clickedItemParent = clickedItemParent;
        addMenuItems();
    }

    public void addMenuItems() {
        addMenuItem(menu, RENAME_COLUMN_LABEL, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        addMenuItem(menu, DELETE_COLUMN_LABEL, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public JPopupMenu getMenu() {
        return menu;
    }
}
