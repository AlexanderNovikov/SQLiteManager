package com.github.sqliteManager.ui.dbTree.dialogs;

import com.github.sqliteManager.ui.dbTree.errors.WrongColumnNameError;
import com.github.sqliteManager.ui.dbTree.errors.WrongTableNameError;

import javax.swing.*;

/**
 * Created by alexander on 11/07/14.
 */
public class RenameColumnDialog {
    private static final String DIALOG_TITLE = "Rename column";
    private static final String DIALOG_TEXT = "Enter new name";
    private String input;

    public RenameColumnDialog() {
        String resultVal = (String)JOptionPane.showInputDialog(null, DIALOG_TEXT, DIALOG_TITLE, JOptionPane.PLAIN_MESSAGE);
        if (resultVal.length() > 0) {
            this.input = resultVal;
        } else {
            new WrongColumnNameError();
        }
    }

    public String getInput() {
        return input;
    }

    public static void main(String[] args) {
        new RenameColumnDialog();
    }
}
