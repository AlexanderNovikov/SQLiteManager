package com.github.sqliteManager.gui.dbTree;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by alexander on 01/07/14.
 */
public class DBTreePopupMenu {
    private JPopupMenu menu;

    public void addMenuItem(JPopupMenu menu, String menuItemName, ActionListener action) {
        JMenuItem menuItem = new JMenuItem(menuItemName);
        menuItem.addActionListener(action);
        menu.add(menuItem);
    }

    public void addMenuListener() {
        MouseListener mouseListener = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        };
    }
}
