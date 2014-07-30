package com.github.sqliteManager.ui.dbTree.dialogs;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by alexander on 07/07/14.
 */
public class RenameTableDialog {
    private static final String LABEL_TEXT_FIELD = "New Name:";
    private static final String DIALOG_LABEL = "Rename Table";
    private static final String LABEL_RENAME_TABLE = "Rename Table";
    private static final String LABEL_CANCEL = "Cancel";
    private JPanel mainPanel;
    private JTextField tableNewName;
    private JButton renameButton, cancelButton;
    private String oldName, newName;
    private final ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);

    public RenameTableDialog(String oldName) {
        mainPanel = new JPanel();
        this.oldName = oldName;

        addNameField();
        addButtons();
        addScheduler();

        int result = JOptionPane.showOptionDialog(
                null,
                mainPanel,
                DIALOG_LABEL,
                JOptionPane.YES_NO_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                new JButton[]{renameButton, cancelButton},
                null
        );
        if (result == JOptionPane.YES_OPTION) {
           this.newName = tableNewName.getText();
        }
    }

    private void addScheduler() {
        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                if (checkTableNameField()) {
                    renameButton.setEnabled(true);
                } else {
                    renameButton.setEnabled(false);
                }
            }
        }, 0, 50, TimeUnit.MILLISECONDS);
    }

    private Boolean checkTableNameField() {
        if (tableNewName.getText().length() > 0 && !tableNewName.getText().equals(oldName)) {
            return true;
        } else {
            return false;
        }
    }

    private void addNameField() {
        mainPanel.add(new JLabel(LABEL_TEXT_FIELD));
        tableNewName = new JTextField(15);
        tableNewName.setText(oldName);
        mainPanel.add(tableNewName);
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
        renameButton = new JButton(LABEL_RENAME_TABLE);
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
