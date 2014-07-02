package com.github.sqliteManager.ui.dbTree;

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

    public ColumnPopupMenu() {
        menu = new JPopupMenu();
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
