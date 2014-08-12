package com.github.sqliteManager.ui.errors;

import javax.swing.*;

/**
 * Created by alexander on 7/2/14.
 */
public class DatabaseNotOpenedError {

    private static final String DIALOG_TEXT = "Database not opened!";
    private static final String DIALOG_TITLE = "Error!";

    public DatabaseNotOpenedError(JFrame mainFrame) {
        JOptionPane.showMessageDialog(mainFrame, DIALOG_TEXT, DIALOG_TITLE, JOptionPane.ERROR_MESSAGE);
    }
}
