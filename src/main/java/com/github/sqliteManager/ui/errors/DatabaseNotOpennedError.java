package com.github.sqliteManager.ui.errors;

import javax.swing.*;

/**
 * Created by alexander on 7/2/14.
 */
public class DatabaseNotOpennedError {

    private static final String DIALOG_TEXT = "Database not openned!";
    private static final String DIALOG_TITLE = "Error!";

    public DatabaseNotOpennedError() {
        JOptionPane errorWindow = new JOptionPane();
        errorWindow.showMessageDialog(null, DIALOG_TEXT, DIALOG_TITLE, JOptionPane.ERROR_MESSAGE);
    }
}
