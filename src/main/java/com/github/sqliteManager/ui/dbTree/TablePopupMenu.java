package com.github.sqliteManager.ui.dbTree;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by alexander on 01/07/14.
 */
public class TablePopupMenu extends DBTreePopupMenu {
    private static final String ADD_COLUMN_LABEL = "Add column";
    private static final String RENAME_TABLE_LABEL = "Rename table";
    private static final String VIEW_CONTENT_LABEL = "View content";
    private static final String DELETE_TABLE_LABEL = "Delete table";
    private JPopupMenu menu;

    public TablePopupMenu() {
        menu = new JPopupMenu();
        addMenuItem(menu, ADD_COLUMN_LABEL, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        addMenuItem(menu, RENAME_TABLE_LABEL, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        addMenuItem(menu, VIEW_CONTENT_LABEL, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        addMenuItem(menu, DELETE_TABLE_LABEL, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public JPopupMenu getMenu() {
        return menu;
    }
}
