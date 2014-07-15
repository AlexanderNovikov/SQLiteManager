package com.github.sqliteManager.ui.dbTree.dialogs;

import com.github.sqliteManager.core.models.Column;
import com.github.sqliteManager.core.models.ColumnType;
import com.sun.org.apache.xpath.internal.operations.Bool;

import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by alexander on 03/07/14.
 */
public class CreateTableDialog {
    private static final String DIALOG_TEXT = "Table name:";
    private static final String DIALOG_LABEL = "New table";
    private static final String LABEL_DEFAULT_VALUE = "Default Value";
    private static final String LABEL_COLUMN_NAME = "Column Name";
    private static final String LABEL_TYPE = "Type";
    private static final String LABEL_REMOVE = "Remove";

    private JFrame frame;
    private JPanel mainPanel, columnPanel, columnListPanel;
    private JTextField tableName, columnName, columnDefaultValue;
    private JComboBox<String> columnType;
    private JCheckBox isNotNull;
    private JButton addColumnButton, removeColumnButton, createButton, cancelButton;
    private JOptionPane optionPane;
    private JScrollPane scrollPane;
    private int input;

    public CreateTableDialog() {
        frame = new JFrame();
        mainPanel = new JPanel();
        mainPanel.setPreferredSize(new Dimension(800, 350));
        optionPane = new JOptionPane();

        addTablePanel();
        addColumnListPanel();
        addButtonPanel();

        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().add(mainPanel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public void addTablePanel() {
        JPanel panel = new JPanel();
        tableName = new JTextField(15);
        addColumnButton = new JButton("Add column");
        addColumnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addColumn();
            }
        });
        panel.add(new JLabel("Table Name"));
        panel.add(tableName);
        panel.add(addColumnButton);
        mainPanel.add(panel);
    }

    public void addColumnListPanel() {
        columnListPanel = new JPanel();
        columnListPanel.setLayout(new GridLayout(0,1,2,2));
        scrollPane = new JScrollPane(columnListPanel);
        scrollPane.setPreferredSize(new Dimension(800, 250));
        mainPanel.add(scrollPane);
    }

    public void addButtonPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1,2));
        createButton = new JButton("Create table");
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (Component jPanel : columnListPanel.getComponents()) {
                    if (jPanel instanceof JPanel) {
                        for (Component component : ((JPanel) jPanel).getComponents()) {
                            if (component instanceof JTextField) {
                                if (component.getName().equals(LABEL_COLUMN_NAME)) {
                                    if (((JTextField) component).getText().length() > 0) {
                                        createButton.setEnabled(true);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        });
        createButton.setEnabled(false);
        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
        panel.add(createButton, BorderLayout.EAST);
        panel.add(cancelButton, BorderLayout.WEST);
        mainPanel.add(panel);
    }

    public void addColumn() {
        columnPanel = new JPanel();
        columnName = new JTextField(10);
        columnName.setName(LABEL_COLUMN_NAME);
        columnName.addCaretListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                if (checkColumnNameFields() == true) {
                    createButton.setEnabled(true);
                } else {
                    createButton.setEnabled(false);
                }
            }
        });
        columnType = new JComboBox<String>(ColumnType.ALL_TYPES);
        columnType.setName(LABEL_TYPE);
        columnDefaultValue = new JTextField("null", 10);
        columnDefaultValue.setEditable(false);
        columnDefaultValue.setName(LABEL_DEFAULT_VALUE);
        isNotNull = new JCheckBox();
        isNotNull.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object component = e.getSource();
                if (component instanceof JCheckBox) {
                    if (((JCheckBox) component).isSelected()) {
                        for (Component parent : ((JCheckBox) component).getParent().getComponents()) {
                            if (parent instanceof JTextField && parent.getName().equals(LABEL_DEFAULT_VALUE)) {
                                ((JTextField) parent).setEditable(true);
                            }
                        }
                    } else {
                        for (Component parent : ((JCheckBox) component).getParent().getComponents()) {
                            if (parent instanceof JTextField && parent.getName().equals(LABEL_DEFAULT_VALUE)) {
                                ((JTextField) parent).setEditable(false);
                            }
                        }
                    }
                }
            }
        });
        removeColumnButton = new JButton(LABEL_REMOVE);
        removeColumnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object component = e.getSource();
                columnListPanel.remove(((JButton) component).getParent());
                columnListPanel.updateUI();
            }
        });
        columnPanel.add(new JLabel(LABEL_COLUMN_NAME));
        columnPanel.add(columnName);
        columnPanel.add(new JLabel(LABEL_TYPE));
        columnPanel.add(columnType);
        columnPanel.add(new JLabel("Not Null"));
        columnPanel.add(isNotNull);
        columnPanel.add(new JLabel(LABEL_DEFAULT_VALUE));
        columnPanel.add(columnDefaultValue);
        columnPanel.add(removeColumnButton);
        columnListPanel.add(columnPanel, BorderLayout.AFTER_LAST_LINE); //TODO Add remove column button
        columnListPanel.updateUI();
        scrollPane.updateUI();
        mainPanel.updateUI();
        createButton.setEnabled(false);
    }

    public Boolean checkColumnNameFields() {
        ArrayList<Boolean> booleans = new ArrayList<Boolean>();
        Boolean result = null;
        for (Component jPanel : columnListPanel.getComponents()) {
            if (jPanel instanceof JPanel) {
                for (Component component : ((JPanel) jPanel).getComponents()) {
                    if (component instanceof JTextField) {
                        if (component.getName().equals(LABEL_COLUMN_NAME) && ((JTextField) component).getText().length() > 0) {
                            booleans.add(true);
                        } else if (component.getName().equals(LABEL_COLUMN_NAME) && ((JTextField) component).getText().length() == 0) {
                            booleans.add(false);
                        }
                    }
                }
            }
        }
        if (booleans.contains(false)) {
            result = false;
        } else {
            result = true;
        }
        return result;
    }

    public HashMap<Integer, Column> getData() {
        HashMap<Integer, Column> result = new HashMap<Integer, Column>();
        Column column = null;
        int i = 0;
        for (Component jPanel : columnListPanel.getComponents()) {
            if (jPanel instanceof JPanel) {
                column = new Column();
                for (Component component : ((JPanel) jPanel).getComponents()) {
                    if (component instanceof JTextField) {
                        if (component.getName().equals(LABEL_COLUMN_NAME)) {
                            column.setColumnName(((JTextField) component).getText()); //TODO add check for input length if less then 1 symbol - throw error
                        } else if (component.getName().equals(LABEL_DEFAULT_VALUE)) {
                            column.setColumnDefaultValue(((JTextField) component).getText());
                        }
                    } else if (component instanceof JComboBox) {
                        column.setColumnType(((JComboBox) component).getSelectedItem().toString());
                    } else if (component instanceof JCheckBox) {
                        column.setNotNull(((JCheckBox) component).isSelected());
                    }
                }
                result.put(i++, column);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        new CreateTableDialog();
    }
}
