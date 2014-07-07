package com.github.sqliteManager.ui.dbTree.dialogs;

import javax.swing.*;

/**
 * Created by alexander on 07/07/14.
 */
public class RenameTableDialog {
    private static final String DIALOG_TEXT = "New name:";
    private static final String DIALOG_LABEL = "Rename table";
    private String input;

    public RenameTableDialog() {
        input = (String)JOptionPane.showInputDialog(null, DIALOG_TEXT, DIALOG_LABEL, JOptionPane.PLAIN_MESSAGE, null, null, null);
    }

    public String getInput() {
        return input;
    }
}
