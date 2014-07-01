package com.github.sqliteManager.gui;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Created by alexander on 01/07/14.
 */
public class FileChooser {
    private JFileChooser fileChooser;
    private FileNameExtensionFilter fileNameExtensionFilter;
    private File selectedFile;

    public FileChooser() {
        fileChooser = new JFileChooser();
        fileNameExtensionFilter = new FileNameExtensionFilter("*.db, *.sqlite", "db", "sqlite");
        fileChooser.setFileFilter(fileNameExtensionFilter);
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int returnVal = fileChooser.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            selectedFile = fileChooser.getSelectedFile();
        }
    }

    public File getSelectedFile() {
        return selectedFile;
    }

    public static void main(String[] args) {
        new FileChooser();
    }
}
