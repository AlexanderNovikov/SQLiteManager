package com.github.sqliteManager.ui.dbTree;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by alexander on 01/07/14.
 */
public class DBPopupMenu extends DBTreePopupMenu {
    private JPopupMenu menu;

    public DBPopupMenu(JPopupMenu menu) {
        this.menu = menu;
        addMenuItem(menu, "test", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
}
