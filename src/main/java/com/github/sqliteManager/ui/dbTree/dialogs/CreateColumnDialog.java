package com.github.sqliteManager.ui.dbTree.dialogs;

import com.github.sqliteManager.core.models.Column;
import com.github.sqliteManager.core.models.ColumnType;
import com.github.sqliteManager.core.models.MyBox;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by alexander on 03/07/14.
 */
public class CreateColumnDialog {
    private static final String LABEL_NAME = "Column name";
    private static final String LABEL_TYPE = "Type";
    private static final String LABEL_NOT_NULL = "Not Null";
    private static final String LABEL_DEFAULT_VALUE = "Default Value";
    private static final String DIALOG_LABEL = "New column";
    private static final int TEXT_FIELD_LENGTH = 15;
    private static final String LABEL_CREATE_COLUMN = "Create column";
    private static final String LABEL_CANCEL = "Cancel";
    private Column column;
    private JPanel mainPanel;
    private JTextField columnName, columnDefaultValue;
    private JComboBox columnType;
    private JCheckBox isNull;
    private JButton createButton, cancelButton;
    private final ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);

    public CreateColumnDialog() {
        mainPanel = new JPanel();

        addColumnPanel();
        addButtons();
        addScheduler();

        int result = JOptionPane.showOptionDialog(
                null,
                mainPanel,
                DIALOG_LABEL,
                JOptionPane.YES_NO_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                new JButton[]{createButton, cancelButton},
                null
        );
        if (result == JOptionPane.YES_OPTION) {
            column = new Column(columnName.getText(), columnType.getSelectedItem().toString(), isNull.isSelected(), columnDefaultValue.getText(), false);
        }
    }

    private void addScheduler() {
        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                if (checkColumnNameField()) {
                    createButton.setEnabled(true);
                } else {
                    createButton.setEnabled(false);
                }
            }
        }, 0, 50, TimeUnit.MILLISECONDS);
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

    private void addColumnPanel() {
        JPanel columnPanel = new JPanel();
        columnPanel.setLayout(new BoxLayout(columnPanel, BoxLayout.Y_AXIS));
        columnName = new JTextField(TEXT_FIELD_LENGTH);
        columnType = new JComboBox<String>(ColumnType.ALL_TYPES);
        isNull = new JCheckBox();
        isNull.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (isNull.isSelected()) {
                    columnDefaultValue.setEditable(true);
                    mainPanel.updateUI();
                } else {
                    columnDefaultValue.setEditable(false);
                    mainPanel.updateUI();
                }
            }
        });
        columnDefaultValue = new JTextField(TEXT_FIELD_LENGTH);
        columnDefaultValue.setEditable(false);
        columnPanel.add(
                new MyBox(
                        new JComponent[]{
                                new JLabel(LABEL_NAME),
                                columnName},
                        Component.LEFT_ALIGNMENT
                ));
        columnPanel.add(
                new MyBox(
                        new JComponent[]{
                                new JLabel(LABEL_TYPE),
                                columnType},
                        Component.LEFT_ALIGNMENT
                ));
        columnPanel.add(
                new MyBox(
                        new JComponent[]{
                                new JLabel(LABEL_NOT_NULL),
                                isNull},
                        Component.LEFT_ALIGNMENT
                ));
        columnPanel.add(
                new MyBox(
                        new JComponent[]{
                                new JLabel(LABEL_DEFAULT_VALUE),
                                columnDefaultValue},
                        Component.LEFT_ALIGNMENT
                ));
        mainPanel.add(columnPanel);
    }

    private void addButtons() {
        createButton = new JButton(LABEL_CREATE_COLUMN);
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane pane = getOptionPane((JComponent) e.getSource());
                pane.setValue(createButton);
                scheduledExecutorService.shutdown();
            }
        });
        createButton.setEnabled(false);
        cancelButton = new JButton(LABEL_CANCEL);
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane pane = getOptionPane((JComponent) e.getSource());
                pane.setValue(cancelButton);
                scheduledExecutorService.shutdown();
            }
        });
    }

    private Boolean checkColumnNameField() {
        if (columnName.getText().length() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public Column getColumn() {
        return column;
    }

}
