package com.github.sqliteManager.ui.valuesList;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import java.awt.*;

/**
 * Created by alexander on 30/07/14.
 */
public class ValuesListPanel {
    private static final String LABEL_ADD = "Add";
    private static final String LABEL_COPY = "Copy";
    private static final String LABEL_CHANGE = "Change";
    private static final String LABEL_DELETE = "Delete";
    private static final String LABEL_SEARCH = "Search";
    private JTable table;

    public ValuesListPanel(JTable table) {
        this.table = table;
    }

    public JPanel getValuesListPanel() {
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(new MatteBorder(1,0,0,0, Color.GRAY));
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.weightx = 1.0;
        JPanel rightButtonPanel = new JPanel();
        rightButtonPanel.setAlignmentX(JPanel.LEFT_ALIGNMENT);
        JButton buttonAdd = new JButton(LABEL_ADD);
        rightButtonPanel.add(buttonAdd);
        JButton buttonCopy = new JButton(LABEL_COPY);
        rightButtonPanel.add(buttonCopy);
        JButton buttonChange = new JButton(LABEL_CHANGE);
        rightButtonPanel.add(buttonChange);
        JButton buttonDelete = new JButton(LABEL_DELETE);
        rightButtonPanel.add(buttonDelete);
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.weighty = 0.0;
        constraints.anchor = GridBagConstraints.EAST;
        mainPanel.add(rightButtonPanel, constraints);
        JPanel leftButtonPanel = new JPanel();
        JButton buttonSearch = new JButton(LABEL_SEARCH);
        leftButtonPanel.add(buttonSearch);
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weighty = 0.0;
        constraints.anchor = GridBagConstraints.WEST;
        mainPanel.add(leftButtonPanel, constraints);
        constraints.gridy = 1;
        constraints.weighty = 0.9;
        constraints.gridwidth = 2;
        constraints.fill = GridBagConstraints.BOTH;
        mainPanel.add(new JScrollPane(table), constraints);
        return mainPanel;
    }
}
