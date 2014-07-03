package com.github.sqliteManager.ui.dbTree.dialogs;

import javax.swing.*;

/**
 * Created by alexander on 03/07/14.
 */
public class DeleteTableDialog {
    private int option;

    public DeleteTableDialog() {
        option = new JOptionPane().showConfirmDialog(null, "text", "text", JOptionPane.YES_NO_OPTION);
    }

    public int getOption() {
        return option;
    }
}
