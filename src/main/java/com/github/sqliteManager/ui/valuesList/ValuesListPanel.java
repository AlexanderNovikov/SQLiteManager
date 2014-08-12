package com.github.sqliteManager.ui.valuesList;

import com.github.sqliteManager.core.models.MyJTable;
import com.github.sqliteManager.core.models.Table;
import com.github.sqliteManager.ui.dbTree.DBTreeEngine;
import com.github.sqliteManager.ui.utils.FindComponent;
import com.github.sqliteManager.ui.valuesList.dialogs.AddRowDialog;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
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
    private static final String ERROR_TITLE = "Error!";
    private static final String NO_ROWS_SELECTED = "No rows selected!";
    private static final String DATABASE_NOT_FOUND = "Database not found!";
    private static final String DATABASE_OR_TABLE_NOT_FOUND = "Database or table not found!";
    private static final String TREE_NAME = "tree";
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
        buttonAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkDBAvailability() && getSelectedTable() != null) {
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            AddRowDialog addRowDialog = new AddRowDialog(SwingUtilities.getRoot(table), treeEngine.getSqLiteEngine().getColumnList(getSelectedTable()));
                            treeEngine.getSqLiteEngine().insertValue(getSelectedTable(), addRowDialog.getData());
                        }
                    });
                } else {
                    showError(DATABASE_OR_TABLE_NOT_FOUND, ERROR_TITLE);
                }
            }
        });
        rightButtonPanel.add(buttonAdd);
        JButton buttonCopy = new JButton(LABEL_COPY);
        rightButtonPanel.add(buttonCopy);
        JButton buttonChange = new JButton(LABEL_CHANGE);
        rightButtonPanel.add(buttonChange);
        JButton buttonDelete = new JButton(LABEL_DELETE);
        buttonDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkDBAvailability()) {
                    if (table.getSelectedRow() < 0) {
                        showError(NO_ROWS_SELECTED, ERROR_TITLE);
                    } else {
                        treeEngine.getSqLiteEngine().removeRowsByRowID(getSelectedTable(), table.getSelectedRowID());
                    }
                } else {
                    showError(DATABASE_NOT_FOUND, ERROR_TITLE);
                }
            }
        });
        rightButtonPanel.add(buttonDelete);
        return rightButtonPanel;
    }

    private void showError(final String errorMessage, final String errorTitle) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JOptionPane.showMessageDialog(SwingUtilities.getRoot(table), errorMessage, errorTitle, JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    private Boolean checkDBAvailability() {
        if (table != null && treeEngine != null && treeEngine.getSqLiteEngine() != null) {
            return true;
        } else {
            return false;
        }
    }

    private Table getSelectedTable() {
        FindComponent findComponent = new FindComponent(table);
        JTree tree = (JTree)findComponent.getFirstComponentByName(TREE_NAME);
        Object component = tree.getLastSelectedPathComponent();
        if (component instanceof DefaultMutableTreeNode) {
            return ((Table)((DefaultMutableTreeNode) component).getUserObject());
        } else {
            return null;
        }
    }

    private JScrollPane getValuesTable() {
        table.getModel().addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                System.out.println(e.getType());
            }
        });
        return new JScrollPane(table);
    }
}
