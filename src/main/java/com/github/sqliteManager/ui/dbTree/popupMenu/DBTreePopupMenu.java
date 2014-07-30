package com.github.sqliteManager.ui.dbTree.popupMenu;

import com.github.sqliteManager.core.SQLiteEngine;
import com.github.sqliteManager.core.models.*;
import com.github.sqliteManager.ui.dbTree.DBTreeEngine;
import com.github.sqliteManager.ui.valuesList.ValuesList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * Created by alexander on 01/07/14.
 */
public class DBTreePopupMenu extends JPopupMenu {
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
                    Object component = tree.getPathForLocation(e.getX(), e.getY()).getLastPathComponent();
                    if (component != null) {
                        if (component instanceof DefaultMutableTreeNode) {
                            switch (((DefaultMutableTreeNode) component).getLevel()) {
                                case 0:
                                    Database db = (Database) ((DefaultMutableTreeNode) component).getUserObject();
                                    dbPopupMenu.setDbTreeEngine(dbTreeEngine);
                                    dbPopupMenu.getMenu().show(tree, e.getX(), e.getY());
                                    break;
                                case 1:
                                    Table tb = (Table) ((DefaultMutableTreeNode) component).getUserObject();
                                    tablePopupMenu.setDbTreeEngine(dbTreeEngine);
                                    tablePopupMenu.setValuesList(valuesList);
                                    tablePopupMenu.setClickedItem(tb);
                                    tablePopupMenu.getMenu().show(tree, e.getX(), e.getY());
                                    break;
                                case 2:
                                    Column col = (Column) ((DefaultMutableTreeNode) component).getUserObject();
                                    columnPopupMenu.setDbTreeEngine(dbTreeEngine);
                                    columnPopupMenu.setClickedItem(col);
                                    columnPopupMenu.setClickedItemParent(((Table)((MyDefaultMutableTreeNode)((DefaultMutableTreeNode) component).getParent()).getUserObject()));
                                    columnPopupMenu.getMenu().show(tree, e.getX(), e.getY());
                            }
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
}
