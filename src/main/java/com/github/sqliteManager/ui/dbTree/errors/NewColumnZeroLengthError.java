package com.github.sqliteManager.ui.dbTree.errors;

import javax.swing.*;

/**
 * Created by alexander on 04/07/14.
 */
public class NewColumnZeroLengthError {

    private static final String DIALOG_TEXT = "Name for new column is too short!";
    private static final String DIALOG_TITLE = "Error!";

    public NewColumnZeroLengthError() {
        new JOptionPane().showMessageDialog(null, DIALOG_TEXT, DIALOG_TITLE, JOptionPane.PLAIN_MESSAGE);
    }
}
