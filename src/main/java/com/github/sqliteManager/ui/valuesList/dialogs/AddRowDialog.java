package com.github.sqliteManager.ui.valuesList.dialogs;

import com.github.sqliteManager.core.models.Column;
import com.github.sqliteManager.core.models.ColumnType;
import com.github.sqliteManager.core.models.MyBox;
import com.github.sqliteManager.core.models.Table;
import com.github.sqliteManager.ui.utils.FindComponent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

/**
 * Created by alexander on 01/08/14.
 */
public class AddRowDialog {
    private static final String DIALOG_TITLE = "title";
    private static final String VAL_FIELD = "valField";
    private JButton createButton, cancelButton;
    private HashMap<Integer, Column> columnList;
    private JPanel mainPanel = new JPanel();
    private HashMap<Integer, String> data = new HashMap<Integer, String>();

    public static void main(String[] args) {
        Table table = new Table();
        table.setTableName("tableName");
        HashMap<Integer, Column> columnList = new HashMap<Integer, Column>();
        columnList.put(0, new Column("columnName1", ColumnType.TEXT));
        columnList.put(1, new Column("columnName2", ColumnType.INTEGER, true, "dv", true));
        table.setColumns(columnList);
        new AddRowDialog(null, table.getColumns());
    }

    public AddRowDialog(Component rootComponent, HashMap<Integer, Column> columnList) {
        this.columnList = columnList;
        addColumnPanel();
        addButtons();
        int result = JOptionPane.showOptionDialog(
                rootComponent,
                mainPanel,
                DIALOG_TITLE,
                JOptionPane.YES_NO_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                new JButton[]{createButton, cancelButton},
                null
        );
        if (result == JOptionPane.YES_OPTION) {
            data = setData();
        }
    }

    private void addColumnPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(panel);
        for (Column column : columnList.values()) {
            panel.add(addField(column));
        }
        mainPanel.add(scrollPane);
    }

    private MyBox addField(Column column) {
        JTextField textField = new JTextField(15);
        textField.setName(VAL_FIELD);
        if (column.getColumnDefaultValue() != null) {
            textField.setText(column.getColumnDefaultValue());
        } else {
            textField.setText("null");
        }
        return new MyBox(new JComponent[]{new JLabel(column.getColumnName() + " (" + column.getColumnType() + ") "),textField}, Component.LEFT_ALIGNMENT);
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
        createButton = new JButton("Create");
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane pane = getOptionPane((JComponent)e.getSource());
                pane.setValue(createButton);
            }
        });
        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane pane = getOptionPane((JComponent)e.getSource());
                pane.setValue(cancelButton);
            }
        });
    }

    private HashMap<Integer, String> setData() {
        FindComponent findComponent = new FindComponent(mainPanel);
        HashMap<Integer, String> result = new HashMap<Integer, String>();
        int i = 0;
        for (Component component : findComponent.getAllComponentsByName(VAL_FIELD).values()) {
            if (component instanceof JTextField) {
                result.put(i++, ((JTextField) component).getText());
            }
        }
        return result;
    }

    public HashMap<Integer, String> getData() {
        return data;
    }
}
