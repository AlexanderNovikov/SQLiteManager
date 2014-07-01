package com.github.sqliteManager.ui.dbTree;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by alexander on 01/07/14.
 */
public class DBTreePopupMenu extends JPopupMenu {
    public void addMenuItem(JPopupMenu menu, String menuItemName, ActionListener action) {
        JMenuItem menuItem = new JMenuItem(menuItemName);
        menuItem.addActionListener(action);
        menu.add(menuItem);
    }
}
