package com.github.sqliteManager.ui.dbTree.dialogs;

import javax.swing.*;

/**
 * Created by alexander on 03/07/14.
 */
public class DeleteTableDialog {
    private static final String DIALOG_TEXT = "text";
    private static final String DIALOG_TITLE = "text";
    private int option;

    public DeleteTableDialog() {
        option = new JOptionPane().showConfirmDialog(null, DIALOG_TEXT, DIALOG_TITLE, JOptionPane.YES_NO_OPTION);
    }

    public int getOption() {
        return option;
    }
}
