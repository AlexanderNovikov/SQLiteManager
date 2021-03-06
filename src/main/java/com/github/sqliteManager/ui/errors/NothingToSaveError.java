package com.github.sqliteManager.ui.errors;

import javax.swing.*;

/**
 * Created by alexander on 02/07/14.
 */
public class NothingToSaveError {

    private static final String DIALOG_TEXT = "Nothing to Save!";
    private static final String DIALOG_TITLE = "Error!";

    public NothingToSaveError(JFrame mainFrame) {
        JOptionPane.showMessageDialog(mainFrame, DIALOG_TEXT, DIALOG_TITLE, JOptionPane.ERROR_MESSAGE);
    }
}