package com.github.sqliteManager.core.models;

import java.awt.event.ActionListener;
import javax.swing.JMenuItem;

/**
 * Created by alexander on 09/07/14.
 */
public class JSubMenuItem extends JMenuItem {
    private ActionListener actionListener;

    public JSubMenuItem(String text, ActionListener actionListener) {
        super(text);
        this.actionListener = actionListener;
        this.addActionListener(actionListener);
    }
}
