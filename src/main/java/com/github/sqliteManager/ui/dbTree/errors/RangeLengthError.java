package com.github.sqliteManager.ui.dbTree.errors;

import javax.swing.*;

/**
 * Created by alexander on 11/07/14.
 */
public class RangeLengthError {

    private static final String DIALOG_TITLE = "Error";
    private static final String DIALOG_TEXT = "Wrong start or end number!";

    public RangeLengthError() {
        JOptionPane.showMessageDialog(null, DIALOG_TEXT, DIALOG_TITLE, JOptionPane.PLAIN_MESSAGE);
    }
}
