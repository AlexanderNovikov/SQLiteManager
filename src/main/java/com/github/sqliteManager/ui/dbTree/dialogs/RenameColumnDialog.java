package com.github.sqliteManager.ui.dbTree.dialogs;

import com.github.sqliteManager.ui.dbTree.errors.WrongColumnNameError;
import com.github.sqliteManager.ui.dbTree.errors.WrongTableNameError;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by alexander on 11/07/14.
 */
public class RenameColumnDialog {
    private static final String DIALOG_TITLE = "Rename Column";
    private static final String LABEL_RENAME_COLUMN = "Rename Column";
    private static final String LABEL_CANCEL = "Cancel";
    private static final String LABEL_TEXT_FIELD = "New Name:";
    private JPanel mainPanel;
    private String oldName, newName;
    private JTextField columnNewName;
    private JButton renameButton, cancelButton;
    private final ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);

    public RenameColumnDialog(String oldName) {
        mainPanel = new JPanel();
        this.oldName = oldName;

        addNameField();
        addButtons();
        addScheduler();

        int result = JOptionPane.showOptionDialog(
                null,
                mainPanel,
                DIALOG_TITLE,
                JOptionPane.YES_NO_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                new JButton[]{renameButton, cancelButton},
                null
        );
        if (result == JOptionPane.YES_OPTION) {
            this.newName = columnNewName.getText();
        }
    }

    private void addScheduler() {
        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                if (checkColumnNameField()) {
                    renameButton.setEnabled(true);
                } else {
                    renameButton.setEnabled(false);
                }
            }
        }, 0, 50, TimeUnit.MILLISECONDS);
    }

    private Boolean checkColumnNameField() {
        if (columnNewName.getText().length() > 0 && !columnNewName.getText().equals(oldName)) {
            return true;
        } else {
            return false;
        }
    }

    private void addNameField() {
        mainPanel.add(new JLabel(LABEL_TEXT_FIELD));
        columnNewName = new JTextField(15);
        columnNewName.setText(oldName);
        mainPanel.add(columnNewName);
    }

    private JOptionPane getOptionPane(JComponent parent) {
        JOptionPane pane;
        if (!(parent instanceof JOptionPane)) {
            pane = getOptionPane((JComponent)parent.getParent());
        } else {
            pane = (JOptionPane) parent;
        }
        return pane;
    }

    private void addButtons() {
        renameButton = new JButton(LABEL_RENAME_COLUMN);
        renameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane pane = getOptionPane((JComponent)e.getSource());
                pane.setValue(renameButton);
                scheduledExecutorService.shutdown();
            }
        });
        renameButton.setEnabled(false);
        cancelButton = new JButton(LABEL_CANCEL);
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane pane = getOptionPane((JComponent)e.getSource());
                pane.setValue(cancelButton);
                scheduledExecutorService.shutdown();
            }
        });
    }

    public String getNewName() {
        return newName;
    }
}
