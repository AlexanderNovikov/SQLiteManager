package com.github.sqliteManager.ui.errors;

import javax.swing.*;

/**
 * Created by alexander on 7/2/14.
 */
public class DatabaseNotOpennedError {

    private static final String ERROR_DIALOG_TEXT = "Database not openned!";
    private static final String ERROR_DIALOG_TITLE = "Error!";

    public DatabaseNotOpennedError() {
        JOptionPane errorWindow = new JOptionPane();
        errorWindow.showMessageDialog(null, ERROR_DIALOG_TEXT, ERROR_DIALOG_TITLE, JOptionPane.WARNING_MESSAGE);
    }
}
