package com.github.sqliteManager.ui.fileChooser;

import com.github.sqliteManager.core.sqlite.SQLiteEngine;
import com.github.sqliteManager.ui.fileChooser.dialogs.OverwriteFileDialog;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

/**
 * Created by alexander on 01/07/14.
 */
public class FileChooser extends JFileChooser {
    private static final String FILE_EXTENSION_DB = "db";
    private static final String FILE_EXTENSION_SQLITE = "sqlite";
    private static final String FILE_EXTENSION_DOT = ".";
    private static final String DEFAULT_FILE_NAME = "Untitled";
    private Boolean accept = null;
    private JFileChooser fileChooser;
    private File selectedFile;
    private JFrame mainFrame;

    public FileChooser() {
        fileChooser = new JFileChooser();
        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter(FILE_EXTENSION_DOT + FILE_EXTENSION_DB, FILE_EXTENSION_DB));
        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter(FILE_EXTENSION_DOT + FILE_EXTENSION_SQLITE, FILE_EXTENSION_SQLITE));
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
    }

    public void chooseFile() {
        int returnVal = fileChooser.showOpenDialog(mainFrame);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            selectedFile = fileChooser.getSelectedFile();
        } else {
            return;
        }
    }

    public void chooseFileForSaving() {
        int returnVal = fileChooser.showSaveDialog(null);
        accept = false;
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            selectedFile = fileChooser.getSelectedFile();
            do {
                if (selectedFile != null && selectedFile.exists()) {
                    int option = new OverwriteFileDialog().getOption();
                    if (option == JOptionPane.YES_OPTION) {
                        accept = true;
                        overwriteFile();
                    } else if (option == JOptionPane.NO_OPTION) {
                        chooseFileForSaving();
                    } else if (option == JOptionPane.CLOSED_OPTION) {
                        chooseFileForSaving();
                    }
                } else {
                    accept = true;
                    createFile();
                }
            } while (!accept);
        } else if (returnVal == JFileChooser.CANCEL_OPTION) {
            accept = true;
        }
    }

    public void chooseFileForSaving(String fileName) {
        if (fileName.length() > 0) {
            fileChooser.setSelectedFile(new File(fileName));
        } else {
            fileChooser.setSelectedFile(new File(DEFAULT_FILE_NAME));
        }
        chooseFileForSaving();
    }

    public void createFile() {
        SQLiteEngine sqLiteEngine = new SQLiteEngine(selectedFile);
        sqLiteEngine.openDB();
        sqLiteEngine.closeDB();
    }

    public void overwriteFile() {
        selectedFile.delete();
        createFile();
    }

    public File getSelectedFile() {
        return selectedFile;
    }

    public void setMainFrame(JFrame mainFrame) {
        this.mainFrame = mainFrame;
    }
}
