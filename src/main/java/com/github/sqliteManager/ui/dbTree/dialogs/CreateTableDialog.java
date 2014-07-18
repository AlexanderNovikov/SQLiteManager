package com.github.sqliteManager.ui.dbTree.dialogs;

import com.github.sqliteManager.core.models.Column;
import com.github.sqliteManager.core.models.ColumnType;
import com.github.sqliteManager.core.models.Table;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by alexander on 03/07/14.
 */
public class CreateTableDialog extends JDialog {
    private static final String DIALOG_LABEL = "New table";
    private static final String LABEL_DEFAULT_VALUE = "Default Value";
    private static final String LABEL_COLUMN_NAME = "Column Name";
    private static final String LABEL_TYPE = "Type";
    private static final String LABEL_REMOVE = "Remove";
    private static final String LABEL_NOT_NULL = "Not Null";
    private static final String DEFAULT_VALUE = "null";
    private static final String LABEL_TABLE_NAME = "Table Name";
    private static final String LABEL_ADD_COLUMN = "Add column";
    private static final String LABEL_CREATE_TABLE = "Create table";
    private static final String LABEL_CANCEL = "Cancel";

    private JPanel mainPanel, tablePanel, columnPanel/*, columnListPanel*/;
    private JTextField tableName, columnName, columnDefaultValue;
    private JComboBox<String> columnType;
    private JCheckBox isNotNull;
    private JButton addColumnButton, removeColumnButton, createButton, cancelButton;
    private JScrollPane scrollPane;
    private final ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
    private Table table;
    private int result;

    private JTable columnListPanel;

    public static void main(String[] args) {
        new CreateTableDialog();
    }

    public CreateTableDialog() {
        mainPanel = new JPanel();
        mainPanel.setPreferredSize(new Dimension(850, 350));

        addTablePanel();
        addColumnListPanel();
        addButtons();
        addScheduler();

        result = JOptionPane.showOptionDialog(
                null,
                mainPanel,
                DIALOG_LABEL,
                JOptionPane.YES_NO_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                new JButton[]{cancelButton, createButton},
                null
        );
        if (result == JOptionPane.YES_OPTION) {
            getData();
        }
    }

    private void addScheduler() {
        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                getData();
                if (checkColumnNameFields() && checkTableNameField() && columnListPanel.getComponents().length > 0) {
                    createButton.setEnabled(true);
                } else {
                    createButton.setEnabled(false);
                }
            }
        }, 0, 50, TimeUnit.MILLISECONDS);
    }

    protected JOptionPane getOptionPane(JComponent parent) {
        JOptionPane pane = null;
        if (!(parent instanceof JOptionPane)) {
            pane = getOptionPane((JComponent)parent.getParent());
        } else {
            pane = (JOptionPane) parent;
        }
        return pane;
    }

    private void addTablePanel() {
        tablePanel = new JPanel();
        JLabel label = new JLabel(LABEL_TABLE_NAME);
        tableName = new JTextField(15);
        tableName.setName(label.getText());
        addColumnButton = new JButton(LABEL_ADD_COLUMN);
        addColumnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addColumn();
            }
        });
        tablePanel.add(label);
        tablePanel.add(tableName);
        tablePanel.add(addColumnButton);
        mainPanel.add(tablePanel);
    }

    private void addColumnListPanel() {
//        columnListPanel = new JPanel();
//        columnListPanel.setLayout(new BoxLayout(columnListPanel, BoxLayout.Y_AXIS));
//        columnListPanel.setPreferredSize(new Dimension(830, 290));
//        scrollPane = new JScrollPane(columnListPanel);
//        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
//        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
//        mainPanel.add(scrollPane);
        DefaultTableModel tableModel = new DefaultTableModel();
        columnListPanel = new JTable(tableModel);
        scrollPane = new JScrollPane(columnListPanel);
        tableModel.addRow(new String[]{"Blah"});
    }

    private void addButtons() {
        createButton = new JButton(LABEL_CREATE_TABLE);
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
                JOptionPane pane = getOptionPane((JComponent)e.getSource());
                pane.setValue(createButton);
                scheduledExecutorService.shutdown();
            }
        });
    }

    private void addColumn() {
        columnPanel = new JPanel();
        columnName = new JTextField(10);
        columnName.setName(LABEL_COLUMN_NAME);
        columnType = new JComboBox<String>(ColumnType.ALL_TYPES);
        columnType.setName(LABEL_TYPE);
        columnDefaultValue = new JTextField(DEFAULT_VALUE, 10);
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
        columnPanel.add(new JLabel(LABEL_NOT_NULL));
        columnPanel.add(isNotNull);
        columnPanel.add(new JLabel(LABEL_DEFAULT_VALUE));
        columnPanel.add(columnDefaultValue);
        columnPanel.add(removeColumnButton);
        columnListPanel.add(columnPanel/*, BorderLayout.SOUTH*/);
        columnListPanel.validate();
        createButton.setEnabled(false);
    }

    private Boolean checkTableNameField() {
        Boolean result = null;
        if (tableName.getText().length() > 0) {
            result = true;
        } else {
            result = false;
        }
        return result;
    }

    private Boolean checkColumnNameFields() {
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

    private void getData() {
        table = new Table();
        HashMap<Integer, Column> columns = new HashMap<Integer, Column>();
        Column column = null;
        int i = 0;
        for (Component jPanel : columnListPanel.getComponents()) {
            if (jPanel instanceof JPanel) {
                column = new Column();
                for (Component component : ((JPanel) jPanel).getComponents()) {
                    if (component instanceof JTextField) {
                        if (component.getName().equals(LABEL_COLUMN_NAME)) {
                            column.setColumnName(((JTextField) component).getText());
                        } else if (component.getName().equals(LABEL_DEFAULT_VALUE)) {
                            column.setColumnDefaultValue(((JTextField) component).getText());
                        }
                    } else if (component instanceof JComboBox) {
                        column.setColumnType(((JComboBox) component).getSelectedItem().toString());
                    } else if (component instanceof JCheckBox) {
                        column.setNotNull(((JCheckBox) component).isSelected());
                    }
                }
                columns.put(i++, column);
            }
        }
        table.setTableName(tableName.getText());
        table.setColumns(columns);
    }

    public Table getTable() {
        return table;
    }
}
