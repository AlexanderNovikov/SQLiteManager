package com.github.sqliteManager.ui.dbTree.popupMenu;

import com.github.sqliteManager.core.sqlite.SQLiteEngine;
import com.github.sqliteManager.core.models.Table;
import com.github.sqliteManager.ui.dbTree.DBTreeEngine;
import com.github.sqliteManager.ui.dbTree.dialogs.CreateTableDialog;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by alexander on 01/07/14.
 */
public class DBPopupMenu extends DBTreePopupMenu {
    private static final String ADD_TABLE_LABEL = "Add table";
    private JPopupMenu menu;
    private SQLiteEngine sqLiteEngine;
    private DBTreeEngine dbTreeEngine;

    public DBPopupMenu() {
        this.menu = new JPopupMenu();
        addMenuItems();
    }

    public void addMenuItems() {
        addMenuItem(menu, ADD_TABLE_LABEL, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Table table = new CreateTableDialog().getTable();
                dbTreeEngine.createTable(table);
            }
        });
    }

    public JPopupMenu getMenu() {
        return menu;
    }

    public void setMenu(JPopupMenu menu) {
        this.menu = menu;
    }

    public void setSqLiteEngine(SQLiteEngine sqLiteEngine) {
        this.sqLiteEngine = sqLiteEngine;
    }

    public void setDbTreeEngine(DBTreeEngine dbTreeEngine) {
        this.dbTreeEngine = dbTreeEngine;
    }
}
