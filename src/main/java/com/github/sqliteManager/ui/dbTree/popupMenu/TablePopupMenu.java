package com.github.sqliteManager.ui.dbTree.popupMenu;

import com.github.sqliteManager.core.SQLiteEngine;
import com.github.sqliteManager.core.models.Column;
import com.github.sqliteManager.ui.dbTree.DBTreeEngine;
import com.github.sqliteManager.ui.dbTree.dialogs.CreateColumnDialog;
import com.github.sqliteManager.ui.dbTree.dialogs.DeleteTableDialog;
import com.github.sqliteManager.ui.dbTree.dialogs.RenameTableDialog;
import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

/**
 * Created by alexander on 01/07/14.
 */
public class TablePopupMenu extends DBTreePopupMenu {
    private static final String ADD_COLUMN_LABEL = "Add column";
    private static final String RENAME_TABLE_LABEL = "Rename table";
    private static final String VIEW_CONTENT_LABEL = "View content";
    private static final String DELETE_TABLE_LABEL = "Delete table";
    private JPopupMenu menu;
    private DBTreeEngine dbTreeEngine;
    private String clickedItem;
    private HashMap<Integer, JMenuItem> contentSubMenu;

    public TablePopupMenu() {
        this.menu = new JPopupMenu();
        addMenuItems();
    }

    public void addMenuItems() {
        addMenuItem(menu, ADD_COLUMN_LABEL, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Column column = new CreateColumnDialog().getColumn();
                dbTreeEngine.createColumn(clickedItem, column.getColumnName(), column.getColumnType(), column.isNotNull(), column.getColumnDefaultValue());
            }
        });
        addMenuItem(menu, RENAME_TABLE_LABEL, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = new RenameTableDialog().getInput();
                if (input != null && input.length() > 0) {
                    dbTreeEngine.renameTable(clickedItem, input);
                }
            }
        });
        addSubMenuItem(menu, VIEW_CONTENT_LABEL, getContentSubMenu());
        addMenuItem(menu, DELETE_TABLE_LABEL, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int option = new DeleteTableDialog().getOption();
                if (option == JOptionPane.YES_OPTION) {
                    dbTreeEngine.removeTable(clickedItem);
                }
            }
        });
    }

    public JPopupMenu getMenu() {
        return menu;
    }

    public HashMap<Integer, JMenuItem> getContentSubMenu() {
        contentSubMenu = new HashMap<Integer, JMenuItem>();
        int i = 0;
        JMenuItem menuItem10 = new JMenuItem("10");
        menuItem10.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        contentSubMenu.put(i++, menuItem10);
        JMenuItem menuItem100 = new JMenuItem("100");
        contentSubMenu.put(i++, menuItem100);
        JMenuItem menuItem1000 = new JMenuItem("1000");
        contentSubMenu.put(i++, menuItem1000);
        JMenuItem menuItemCustom = new JMenuItem("Custom");
        contentSubMenu.put(i++, menuItemCustom);
        JMenuItem menuItemAll = new JMenuItem("All");
        contentSubMenu.put(i++, menuItemAll);
        return contentSubMenu;
    }

    public void setMenu(JPopupMenu menu) {
        this.menu = menu;
    }

    public void setDbTreeEngine(DBTreeEngine dbTreeEngine) {
        this.dbTreeEngine = dbTreeEngine;
    }

    public void setClickedItem(String clickedItem) {
        this.clickedItem = clickedItem;
    }
}
