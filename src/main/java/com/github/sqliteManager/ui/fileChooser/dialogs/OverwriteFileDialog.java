package com.github.sqliteManager.ui.fileChooser.dialogs;

import javax.swing.*;

/**
 * Created by alexander on 04/07/14.
 */
public class OverwriteFileDialog {
    private static final String DIALOG_TEXT = "Do you really want to overwrite file?";
    private static final String DIALOG_TITLE = "Save file";
    private int option;

    public OverwriteFileDialog() {
        option = new JOptionPane().showConfirmDialog(null, DIALOG_TEXT, DIALOG_TITLE, JOptionPane.YES_NO_OPTION);
    }

    public int getOption() {
        return option;
    }
}
