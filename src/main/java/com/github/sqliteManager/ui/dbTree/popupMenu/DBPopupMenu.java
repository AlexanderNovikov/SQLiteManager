package com.github.sqliteManager.ui.dbTree.popupMenu;

import com.github.sqliteManager.core.SQLiteEngine;
import com.github.sqliteManager.ui.dbTree.DBTreeEngine;
import com.github.sqliteManager.ui.dbTree.dialogs.CreateTableDialog;
import com.github.sqliteManager.ui.dbTree.errors.WrongNameError;

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

    public DBPopupMenu(JTree tree, SQLiteEngine sqLiteEngine, DBTreeEngine dbTreeEngine) {
        super(tree, sqLiteEngine, dbTreeEngine);
        this.menu = new JPopupMenu();
        this.sqLiteEngine = sqLiteEngine;
        this.dbTreeEngine = dbTreeEngine;
        addMenuItems();
    }

    public void addMenuItems() {
        addMenuItem(menu, ADD_TABLE_LABEL, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tableName = new CreateTableDialog().getInput();
                if (tableName != null && tableName.length() > 0) {
                    dbTreeEngine.createTable(tableName);
                } else {
                    new WrongNameError();
                }
            }
        });
    }

    public JPopupMenu getMenu() {
        return menu;
    }
}
