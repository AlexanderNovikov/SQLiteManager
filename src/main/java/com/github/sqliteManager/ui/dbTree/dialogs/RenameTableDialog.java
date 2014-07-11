package com.github.sqliteManager.ui.dbTree.dialogs;

import com.github.sqliteManager.ui.dbTree.errors.WrongTableNameError;

import javax.swing.*;

/**
 * Created by alexander on 07/07/14.
 */
public class RenameTableDialog {
    private static final String DIALOG_TEXT = "New name:";
    private static final String DIALOG_LABEL = "Rename table";
    private String input;

    public RenameTableDialog() {
        String resultVal = (String)JOptionPane.showInputDialog(null, DIALOG_TEXT, DIALOG_LABEL, JOptionPane.PLAIN_MESSAGE);
        if (resultVal.length() > 0) {
            this.input = resultVal;
        } else {
            new WrongTableNameError();
        }
    }

    public String getInput() {
        return input;
    }
}
