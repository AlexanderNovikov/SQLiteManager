package com.github.sqliteManager.ui.dbTree.errors;

import javax.swing.*;

/**
 * Created by alexander on 03/07/14.
 */
public class WrongNameError {

    private static final String DIALOG_TEXT = "Wrong name for new table!";
    private static final String DIALOG_TITLE = "Error!";

    public WrongNameError() {
        new JOptionPane().showMessageDialog(null, DIALOG_TEXT, DIALOG_TITLE, JOptionPane.PLAIN_MESSAGE);
    }
}
