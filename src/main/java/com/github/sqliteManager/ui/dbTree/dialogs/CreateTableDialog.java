package com.github.sqliteManager.ui.dbTree.dialogs;

import javax.swing.*;

/**
 * Created by alexander on 03/07/14.
 */
public class CreateTableDialog {
    private static final String DIALOG_TEXT = "Table name:";
    private static final String DIALOG_LABEL = "New table";

    private String input;

    public CreateTableDialog() {
        input = (String) JOptionPane.showInputDialog(null, DIALOG_TEXT, DIALOG_LABEL, JOptionPane.PLAIN_MESSAGE);
    }

    public String getInput() {
        return input;
    }
}
