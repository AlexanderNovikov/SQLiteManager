package com.github.sqliteManager.ui.dbTree.errors;

import javax.swing.*;

/**
 * Created by alexander on 03/07/14.
 */
public class WrongNameError {

    private static final String ERROR_DIALOG_TEXT = "Wrong name for new table!";
    private static final String ERROR_DIALOG_TITLE = "Error!";

    public WrongNameError() {
        new JOptionPane().showMessageDialog(null, ERROR_DIALOG_TEXT, ERROR_DIALOG_TITLE, JOptionPane.ERROR_MESSAGE);
    }
}
