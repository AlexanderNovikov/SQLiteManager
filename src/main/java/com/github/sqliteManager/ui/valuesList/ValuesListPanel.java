package com.github.sqliteManager.ui.valuesList;

import com.github.sqliteManager.core.models.MyJTable;
import com.github.sqliteManager.core.models.Table;
import com.github.sqliteManager.core.sqlite.SQLiteEngine;
import com.github.sqliteManager.ui.dbTree.DBTreeEngine;
import javafx.scene.control.Tab;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by alexander on 30/07/14.
 */
public class ValuesListPanel {
    private static final String LABEL_ADD = "Add";
    private static final String LABEL_COPY = "Copy";
    private static final String LABEL_CHANGE = "Change";
    private static final String LABEL_DELETE = "Delete";
    private static final String LABEL_SEARCH = "Search";
    private MyJTable table;
    private DBTreeEngine treeEngine;

    public ValuesListPanel(MyJTable table, DBTreeEngine treeEngine) {
        this.table = table;
        this.treeEngine = treeEngine;
    }

    public JPanel getValuesListPanel() {
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(new MatteBorder(1,0,0,0, Color.GRAY));
        GridBagConstraints constraints = new GridBagConstraints();

        constraints.weightx = 1.0;
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.weighty = 0.0;
        constraints.anchor = GridBagConstraints.EAST;

        mainPanel.add(getRightButtonPanel(), constraints);

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weighty = 0.0;
        constraints.anchor = GridBagConstraints.WEST;

        mainPanel.add(getLeftButtonPanel(), constraints);

        constraints.gridy = 1;
        constraints.weighty = 0.9;
        constraints.gridwidth = 2;
        constraints.fill = GridBagConstraints.BOTH;

        mainPanel.add(getValuesTable(), constraints);
        return mainPanel;
    }

    private JPanel getLeftButtonPanel() {
        JPanel leftButtonPanel = new JPanel();
        JButton buttonSearch = new JButton(LABEL_SEARCH);
        leftButtonPanel.add(buttonSearch);
        return leftButtonPanel;
    }

    private JPanel getRightButtonPanel() {
        JPanel rightButtonPanel = new JPanel();
        rightButtonPanel.setAlignmentX(JPanel.LEFT_ALIGNMENT);
        JButton buttonAdd = new JButton(LABEL_ADD);
        rightButtonPanel.add(buttonAdd);
        JButton buttonCopy = new JButton(LABEL_COPY);
        rightButtonPanel.add(buttonCopy);
        JButton buttonChange = new JButton(LABEL_CHANGE);
        rightButtonPanel.add(buttonChange);
        JButton buttonDelete = new JButton(LABEL_DELETE);
        buttonDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (table != null) {
                    treeEngine.getSqLiteEngine().removeRowsByRowID(new Table(table.getName()), table.getSelectedRowID());
                }
            }
        });
        rightButtonPanel.add(buttonDelete);
        return rightButtonPanel;
    }

    private JScrollPane getValuesTable() {
        return new JScrollPane(table);
    }
}
