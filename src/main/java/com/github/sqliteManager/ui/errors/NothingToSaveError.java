package com.github.sqliteManager.ui.errors;

import javax.swing.*;

/**
 * Created by alexander on 02/07/14.
 */
public class NothingToSaveError {

    public static final String ERROR_DIALOG_TEXT = "Nothing to Save!";
    public static final String ERROR_DIALOG_TITLE = "Error";

    public NothingToSaveError() {
        JOptionPane errorWindow = new JOptionPane();
        errorWindow.showMessageDialog(null, ERROR_DIALOG_TEXT, ERROR_DIALOG_TITLE, JOptionPane.WARNING_MESSAGE);
    }
}
