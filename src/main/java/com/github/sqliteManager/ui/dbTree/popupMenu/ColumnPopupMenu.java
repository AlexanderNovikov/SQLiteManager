package com.github.sqliteManager.ui.dbTree.popupMenu;

import com.github.sqliteManager.core.models.Column;
import com.github.sqliteManager.core.models.Table;
import com.github.sqliteManager.ui.dbTree.DBTreeEngine;
import com.github.sqliteManager.ui.dbTree.dialogs.RenameColumnDialog;

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
    private DBTreeEngine dbTreeEngine;
    private Column clickedItem;
    private Table clickedItemParent;

    public ColumnPopupMenu() {
        this.menu = new JPopupMenu();
        addMenuItems();
    }

    public void addMenuItems() {
        addMenuItem(menu, RENAME_COLUMN_LABEL, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newColumnName = new RenameColumnDialog(clickedItem.getColumnName()).getNewName();
                    dbTreeEngine.renameColumn(clickedItemParent, clickedItem, newColumnName);
            }
        });
        addMenuItem(menu, "Change type", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        addMenuItem(menu, "Change default value", new ActionListener() {
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

    public void setMenu(JPopupMenu menu) {
        this.menu = menu;
    }

    public void setDbTreeEngine(DBTreeEngine dbTreeEngine) {
        this.dbTreeEngine = dbTreeEngine;
    }

    public void setClickedItem(Column clickedItem) {
        this.clickedItem = clickedItem;
    }

    public void setClickedItemParent(Table clickedItemParent) {
        this.clickedItemParent = clickedItemParent;
    }
}
