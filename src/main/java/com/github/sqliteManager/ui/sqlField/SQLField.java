package com.github.sqliteManager.ui.sqlField;

import com.github.sqliteManager.ui.dbTree.DBTreeEngine;
import com.github.sqliteManager.ui.valuesList.ValuesList;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by alexander on 29/07/14.
 */
public class SQLField {
    public static final String EXECUTE_BUTTON_LABEL = "Execute SQL";
    public static final String CLEAN_BUTTON_LABEL = "Clean";
    private static final String ERROR_MSG = "error";
    private static final String ERROR_TITLE = "error";
    private DBTreeEngine treeEngine;
    private JTextPane textPane;

    public SQLField(DBTreeEngine treeEngine) {
        this.treeEngine = treeEngine;
    }

    public JPanel getSQLField() {
        final JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(new MatteBorder(0,0,1,0, Color.GRAY));
        textPane = new JTextPane();
        JScrollPane scrollTextPane = new JScrollPane(textPane);
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        JButton buttonExecute = new JButton(EXECUTE_BUTTON_LABEL);
        buttonExecute.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (treeEngine.getSqLiteEngine() != null) {

                } else if (treeEngine.getSqLiteEngine() == null) {
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            JOptionPane.showMessageDialog(SwingUtilities.getRoot(mainPanel), ERROR_MSG, ERROR_TITLE, JOptionPane.ERROR_MESSAGE);
                        }
                    });
                }
            }
        });
        JButton buttonClean = new JButton(CLEAN_BUTTON_LABEL);
        buttonClean.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textPane.setText("");
            }
        });
        buttonPanel.add(buttonExecute);
        buttonPanel.add(buttonClean);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 1.0;
        constraints.weighty = 0.9;
        constraints.gridy = 0;
        mainPanel.add(scrollTextPane, constraints);
        constraints.weighty = 0.0;
        constraints.gridy = 1;
        constraints.fill = 0;
        constraints.anchor = GridBagConstraints.WEST;
        mainPanel.add(buttonPanel, constraints);

        return mainPanel;
    }

    private Boolean checkSQLString(String sqlString) {
        String firstWord = sqlString.split(" ")[0];
//        if (firstWord.equals())
        return true;
    }

    private void execSQLStatement() {
        ValuesList valuesList = new ValuesList(treeEngine.getValuesList(), treeEngine.getTableModel());
        valuesList.setHeaders(treeEngine.getHeaders(treeEngine.getTableByName("jj")));
        valuesList.fillTable();
    }

}
