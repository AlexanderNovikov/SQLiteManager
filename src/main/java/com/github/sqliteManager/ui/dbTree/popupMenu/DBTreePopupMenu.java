package com.github.sqliteManager.ui.dbTree.popupMenu;

import com.github.sqliteManager.core.SQLiteEngine;
import com.github.sqliteManager.ui.dbTree.DBTreeEngine;

import javax.swing.*;
import javax.swing.tree.TreePath;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by alexander on 01/07/14.
 */
public class DBTreePopupMenu extends JPopupMenu {
    private static final String DATABASE_LABEL = "Database";
    private static final String TABLE_LABEL = "Table";
    private static final String COLUMN_LABEL = "Column";
    private JTree tree;
    private SQLiteEngine sqLiteEngine;
    private DBTreeEngine dbTreeEngine;

    public DBTreePopupMenu(JTree tree, SQLiteEngine sqLiteEngine, DBTreeEngine dbTreeEngine) {
        this.tree = tree;
        this.sqLiteEngine = sqLiteEngine;
        this.dbTreeEngine = dbTreeEngine;
        addPopupMenu();
    }

    public void addPopupMenu() {
        tree.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    TreePath path = tree.getPathForLocation(e.getX(), e.getY());
                    if (path != null) {
                        String[] clickedItemLabel = path.getLastPathComponent().toString().split(":");
                        String clickedItemName = clickedItemLabel[1].toString().substring(0, clickedItemLabel[1].length() - clickedItemLabel[1].toString().split(" ").length);
                        if (clickedItemLabel[0].equals(DATABASE_LABEL)) {
                            DBPopupMenu menu = new DBPopupMenu(tree, sqLiteEngine, dbTreeEngine);
                            menu.getMenu().show(tree, e.getX(), e.getY());
                        } else if (clickedItemLabel[0].equals(TABLE_LABEL)) {
                            TablePopupMenu menu = new TablePopupMenu(tree, sqLiteEngine, dbTreeEngine, clickedItemName, null);
                            menu.getMenu().show(tree, e.getX(), e.getY());
                        } else if (clickedItemLabel[0].equals(COLUMN_LABEL)) {
                            ColumnPopupMenu menu = new ColumnPopupMenu(tree, sqLiteEngine, dbTreeEngine, clickedItemName, null);
                            menu.getMenu().show(tree, e.getX(), e.getY());
                        }
                    }
                }
            }
        });
    }

    public void addMenuItem(JPopupMenu menu, String menuItemName, ActionListener action) {
        JMenuItem menuItem = new JMenuItem(menuItemName);
        menuItem.addActionListener(action);
        menu.add(menuItem);
    }
}
