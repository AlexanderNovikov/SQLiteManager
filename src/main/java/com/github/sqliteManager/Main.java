package com.github.sqliteManager;

import com.github.sqliteManager.ui.MainWindow;

import javax.swing.*;


/**
 * Created by alexander on 6/29/14.
 */
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainWindow();
            }
        });
    }
}
