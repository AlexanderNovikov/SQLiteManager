package com.github.sqliteManager.ui.dbTree;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by alexander on 01/07/14.
 */
public class TablePopupMenu extends DBTreePopupMenu {
    private JPopupMenu menu;

    public TablePopupMenu(JPopupMenu menu) {
        this.menu = menu;
        addMenuItem(menu, "item1", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
}
