package com.github.sqliteManager.ui.dbTree.popupMenu;

import com.github.sqliteManager.core.SQLiteEngine;
import com.github.sqliteManager.core.models.JSubMenuItem;
import com.github.sqliteManager.ui.dbTree.DBTreeEngine;
import com.github.sqliteManager.ui.valuesList.ValuesList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;

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
    private DBPopupMenu dbPopupMenu;
    private TablePopupMenu tablePopupMenu;
    private ColumnPopupMenu columnPopupMenu;
    private JTable table;
    private DefaultTableModel tableModel;
    private ValuesList valuesList;

    public DBTreePopupMenu() {
    }

    public DBTreePopupMenu(JTree tree, SQLiteEngine sqLiteEngine, DBTreeEngine dbTreeEngine, JTable table, DefaultTableModel tableModel) {
        this.tree = tree;
        this.sqLiteEngine = sqLiteEngine;
        this.dbTreeEngine = dbTreeEngine;
        this.dbPopupMenu = new DBPopupMenu();
        this.tablePopupMenu = new TablePopupMenu();
        this.columnPopupMenu = new ColumnPopupMenu();
        this.table = table;
        this.tableModel = tableModel;
        this.valuesList = new ValuesList(this.table, this.tableModel);
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
                        if (clickedItemLabel[0].equals(DATABASE_LABEL)) {
                            dbPopupMenu.setDbTreeEngine(dbTreeEngine);
                            dbPopupMenu.getMenu().show(tree, e.getX(), e.getY());
                        } else if (clickedItemLabel[0].equals(TABLE_LABEL)) {
                            tablePopupMenu.setDbTreeEngine(dbTreeEngine);
                            tablePopupMenu.setValuesList(valuesList);
                            tablePopupMenu.setClickedItem(getClickedItemName(path));
                            tablePopupMenu.getMenu().show(tree, e.getX(), e.getY());
                        } else if (clickedItemLabel[0].equals(COLUMN_LABEL)) {
                            columnPopupMenu.setDbTreeEngine(dbTreeEngine);
                            columnPopupMenu.setClickedItem(getClickedItemName(path));
                            columnPopupMenu.setClickedItemParent(getClickedItemParentName(path));
                            columnPopupMenu.getMenu().show(tree, e.getX(), e.getY());
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

    public void addSubMenuItem(JPopupMenu menu, String menuItemName, ArrayList<JSubMenuItem> subItemsList) {
        JMenu subMenu = new JMenu(menuItemName);
        for (JSubMenuItem menuItem : subItemsList) {
            subMenu.add(menuItem);
        }
        menu.add(subMenu);
    }

    private String getClickedItemName(TreePath path) {
        String lastPart = path.getLastPathComponent().toString().split(":")[1].toString();
        String clickedItemName = null;
        if (lastPart.lastIndexOf(" (") != -1) {
            clickedItemName = lastPart.substring(0, lastPart.lastIndexOf(" ("));
        } else {
            clickedItemName = lastPart;
        }
        System.out.println(clickedItemName);
        return clickedItemName;
    }

    private String getClickedItemParentName(TreePath path) {
        String lastPart = path.getParentPath().getLastPathComponent().toString().split(":")[1].toString();
        String clickedParentItemName = lastPart.substring(0, lastPart.lastIndexOf(" ("));
        System.out.println(clickedParentItemName);
        return clickedParentItemName;
    }
}
