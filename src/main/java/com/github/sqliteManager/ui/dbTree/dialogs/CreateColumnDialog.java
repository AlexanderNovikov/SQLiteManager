package com.github.sqliteManager.ui.dbTree.dialogs;

import com.github.sqliteManager.core.models.Column;
import com.github.sqliteManager.core.models.ColumnType;
import com.github.sqliteManager.ui.dbTree.errors.NewColumnZeroLengthError;
import com.github.sqliteManager.ui.dbTree.errors.WrongNameError;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * Created by alexander on 03/07/14.
 */
public class CreateColumnDialog {
    private static final String DEFAULT_VALUE = "null";
    private static final String LABEL_NAME = "Name:";
    private static final String LABEL_TYPE = "Type:";
    private static final String LABEL_NOT_NULL = "Not Null";
    private static final String LABEL_DEFAULT_VALUE = "Default Value";
    private static final String DIALOG_LABEL = "title";
    private JOptionPane option;
    private int result;
    private Column column;
    private JPanel panel;
    private JTextField columnName;
    private JComboBox columnType;
    private JCheckBox notNull;
    private JTextField columnDefaultValue;

    public CreateColumnDialog() {
        panel = new JPanel();
        option = new JOptionPane();
        columnName = new JTextField(5);
        columnType = new JComboBox(ColumnType.ALL_TYPES);
        notNull = new JCheckBox();
        notNull.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (notNull.isSelected() == true) {
                    columnDefaultValue.setEditable(true);
                    panel.updateUI();
                } else if (notNull.isSelected() == false) {
                    columnDefaultValue.setEditable(false);
                    panel.updateUI();
                }
            }
        });
        columnDefaultValue = new JTextField(5);
        columnDefaultValue.setText(DEFAULT_VALUE);
        columnDefaultValue.setEditable(false);
        panel.add(new JLabel(LABEL_NAME));
        panel.add(columnName);
        panel.add(new JLabel(LABEL_TYPE));
        panel.add(columnType);
        panel.add(new JLabel(LABEL_NOT_NULL));
        panel.add(notNull);
        panel.add(new JLabel(LABEL_DEFAULT_VALUE));
        panel.add(columnDefaultValue);
        result = option.showConfirmDialog(null, panel, DIALOG_LABEL, JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null);
        if (result == JOptionPane.YES_OPTION) {
            if (columnName.getText().length() > 0 && columnDefaultValue.getText().length() > 0) {
                column = new Column(columnName.getText(), columnType.getSelectedItem().toString(), notNull.isSelected(), columnDefaultValue.getText(), false);
            } else {
                error();
            }
        }
    }

    public void error() {
        new NewColumnZeroLengthError();
        new CreateColumnDialog();
    }

    public Column getColumn() {
        return column;
    }

}
