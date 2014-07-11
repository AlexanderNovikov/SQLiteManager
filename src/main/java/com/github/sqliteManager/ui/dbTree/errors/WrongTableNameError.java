package com.github.sqliteManager.ui.dbTree.errors;

import javax.swing.*;

/**
 * Created by alexander on 03/07/14.
 */
public class WrongTableNameError {

    private static final String DIALOG_TITLE = "Error!";
    private static final String DIALOG_TEXT = "Wrong name for table!";

    public WrongTableNameError() {
        new JOptionPane().showMessageDialog(null, DIALOG_TEXT, DIALOG_TITLE, JOptionPane.ERROR_MESSAGE);
    }
}
