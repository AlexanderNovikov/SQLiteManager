package com.github.sqliteManager.ui.dbTree;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by alexander on 01/07/14.
 */
public class DBPopupMenu extends DBTreePopupMenu {
    private static final String ADD_TABLE_LABEL = "Add table";
    private JPopupMenu menu;

    public DBPopupMenu() {
        menu = new JPopupMenu();
        addMenuItem(menu, ADD_TABLE_LABEL, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public JPopupMenu getMenu() {
        return menu;
    }
}
