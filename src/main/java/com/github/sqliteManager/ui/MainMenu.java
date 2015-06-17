package com.github.sqliteManager.ui;

import com.github.sqliteManager.ui.dbTree.DBTreeEngine;
import com.github.sqliteManager.ui.errors.DatabaseNotOpenedError;
import com.github.sqliteManager.ui.errors.NothingToSaveError;
import com.github.sqliteManager.ui.fileChooser.FileChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;

/**
 * Created by alexander on 01/07/14.
 */
public class MainMenu extends JMenuBar {
    private static final int CMD_KEY = Toolkit.getDefaultToolkit().getMenuShortcutKeyMask();
    private static final String FILE_MENU_LABEL = "File";
    private static final String FILE_MENU_NEW_DATABASE_LABEL = "New Database";
    private static final String FILE_MENU_OPEN_DATABASE_LABEL = "Open Database";
    private static final String FILE_MENU_RELOAD_DATABASE_LABEL = "Refresh Database";
    private static final String FILE_MENU_SAVE_AS_LABEL = "Save As...";
    private static final String FILE_MENU_CLOSE_DATABASE_LABEL = "Close Database";
    private static final String FILE_MENU_EXIT_PROGRAM_LABEL = "Exit Program";
    private static final String EDIT_MENU_LABEL = "Edit";
    private static final String HELP_MENU_LABEL = "Help";
    private static final String MAIN_MENU_NAME = "mainMenu";

    private JFrame mainFrame;
    private static final FileChooser fileChooser = new FileChooser();
    private File selectedFile;
    private DBTreeEngine treeEngine;

    public MainMenu(JFrame mainFrame, DBTreeEngine treeEngine) {
        this.mainFrame = mainFrame;
        this.treeEngine = treeEngine;
        setName(MAIN_MENU_NAME);
        initMainMenu();
    }

    private void initMainMenu() {
        addMenu(this, FILE_MENU_LABEL);
        addMenuItem(this.getMenu(0), FILE_MENU_NEW_DATABASE_LABEL, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fileChooser.chooseFileForSaving();
                selectedFile = fileChooser.getSelectedFile();
                if (selectedFile != null) {
                    treeEngine.openDB(selectedFile);
                }
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_N, CMD_KEY));
        addMenuItem(this.getMenu(0), FILE_MENU_OPEN_DATABASE_LABEL, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fileChooser.chooseFile();
                selectedFile = fileChooser.getSelectedFile();
                if (selectedFile != null) {
                    treeEngine.openDB(selectedFile);
                }
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_O, CMD_KEY));
        addMenuItem(this.getMenu(0), FILE_MENU_RELOAD_DATABASE_LABEL, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedFile != null) {
                    treeEngine.refreshDBTree();
                } else {
                    new DatabaseNotOpenedError(mainFrame);
                }
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_R, CMD_KEY));
        addMenuItem(this.getMenu(0), FILE_MENU_SAVE_AS_LABEL, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedFile != null) {
                    fileChooser.chooseFileForSaving(selectedFile.getName());
                } else {
                    new NothingToSaveError(mainFrame);
                }
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_S, CMD_KEY));
        addMenuItem(this.getMenu(0), FILE_MENU_CLOSE_DATABASE_LABEL, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedFile != null) {
                    treeEngine.closeDB();
                }
            }
        }, null);
        addMenuItem(this.getMenu(0), FILE_MENU_EXIT_PROGRAM_LABEL, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_Q, CMD_KEY));
        addMenu(this, EDIT_MENU_LABEL);
        addMenu(this, HELP_MENU_LABEL);
        mainFrame.setJMenuBar(this);
    }

    private void addMenu(JMenuBar menuBar, String menuName) {
        JMenu menu = new JMenu(menuName);
        menuBar.add(menu);
    }

    private void addMenuItem(JMenu menu, String menuItemName, ActionListener action, KeyStroke keyStroke) {
        JMenuItem menuItem = new JMenuItem(menuItemName);
        menuItem.addActionListener(action);
        menuItem.setAccelerator(keyStroke);
        menu.add(menuItem);
    }

    public File getSelectedFile() {
        return selectedFile;
    }

}
