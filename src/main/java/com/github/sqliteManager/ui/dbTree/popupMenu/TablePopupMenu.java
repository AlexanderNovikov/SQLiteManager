package com.github.sqliteManager.ui.dbTree.popupMenu;

import com.github.sqliteManager.core.models.Column;
import com.github.sqliteManager.core.models.JSubMenuItem;
import com.github.sqliteManager.core.models.Table;
import com.github.sqliteManager.ui.dbTree.DBTreeEngine;
import com.github.sqliteManager.ui.dbTree.dialogs.CreateColumnDialog;
import com.github.sqliteManager.ui.dbTree.dialogs.DeleteTableDialog;
import com.github.sqliteManager.ui.dbTree.dialogs.RenameTableDialog;
import com.github.sqliteManager.ui.dbTree.dialogs.ValuesRangeDialog;
import com.github.sqliteManager.ui.valuesList.ValuesList;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by alexander on 01/07/14.
 */
public class TablePopupMenu extends DBTreePopupMenu {
    private static final String ADD_COLUMN_LABEL = "Add column";
    private static final String RENAME_TABLE_LABEL = "Rename table";
    private static final String VIEW_CONTENT_LABEL = "View content";
    private static final String VIEW_CONTENT_10 = "Show first 10";
    private static final String VIEW_CONTENT_100 = "Show first 100";
    private static final String VIEW_CONTENT_1000 = "Show first 1000";
    private static final String VIEW_CONTENT_CUSTOM = "Show range of...";
    private static final String VIEW_CONTENT_ALL = "Show all";
    private static final String DELETE_TABLE_LABEL = "Delete table";

    private JPopupMenu menu;
    private DBTreeEngine dbTreeEngine;
    private Table clickedItem;
    private ValuesList valuesList;


    public TablePopupMenu() {
        this.menu = new JPopupMenu();
        addMenuItems();
    }

    public void addMenuItems() {
        addMenuItem(menu, ADD_COLUMN_LABEL, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Column column = new CreateColumnDialog().getColumn();
                dbTreeEngine.createColumn(clickedItem, column);
            }
        });
        addMenuItem(menu, RENAME_TABLE_LABEL, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newTableName = new RenameTableDialog(clickedItem.getTableName()).getNewName();
                dbTreeEngine.renameTable(clickedItem, newTableName);
            }
        });
        addSubMenuItem(menu, VIEW_CONTENT_LABEL, new ArrayList<JSubMenuItem>(Arrays.asList(new JSubMenuItem(VIEW_CONTENT_10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                valuesList.setHeaders(dbTreeEngine.getHeaders(clickedItem));
                valuesList.setData(dbTreeEngine.getValuesRange(clickedItem, 0, 10));
                valuesList.setName(clickedItem.getTableName());
                valuesList.fillTable();
            }
        }), new JSubMenuItem(VIEW_CONTENT_100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                valuesList.setHeaders(dbTreeEngine.getHeaders(clickedItem));
                valuesList.setData(dbTreeEngine.getValuesRange(clickedItem, 0, 100));
                valuesList.setName(clickedItem.getTableName());
                valuesList.fillTable();
            }
        }),new JSubMenuItem(VIEW_CONTENT_1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                valuesList.setHeaders(dbTreeEngine.getHeaders(clickedItem));
                valuesList.setData(dbTreeEngine.getValuesRange(clickedItem, 0, 1000));
                valuesList.setName(clickedItem.getTableName());
                valuesList.fillTable();
            }
        }), new JSubMenuItem(VIEW_CONTENT_CUSTOM, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ValuesRangeDialog resultVal = new ValuesRangeDialog();
                if (resultVal != null) {
                    valuesList.setHeaders(dbTreeEngine.getHeaders(clickedItem));
                    valuesList.setData(dbTreeEngine.getValuesRange(clickedItem, resultVal.getStart(), resultVal.getEnd()));
                    valuesList.setName(clickedItem.getTableName());
                    valuesList.fillTable();
                }
            }
        }), new JSubMenuItem(VIEW_CONTENT_ALL, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                valuesList.setHeaders(dbTreeEngine.getHeaders(clickedItem));
                valuesList.setData(dbTreeEngine.getAllValues(clickedItem));
                valuesList.setName(clickedItem.getTableName());
                valuesList.fillTable();
            }
        }))));
        addMenuItem(menu, DELETE_TABLE_LABEL, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int option = new DeleteTableDialog().getOption();
                if (option == JOptionPane.YES_OPTION) {
                    dbTreeEngine.removeTable(clickedItem);
                }
            }
        });
    }

    public JPopupMenu getMenu() {
        return menu;
    }

    public void setMenu(JPopupMenu menu) {
        this.menu = menu;
    }

    public void setDbTreeEngine(DBTreeEngine dbTreeEngine) {
        this.dbTreeEngine = dbTreeEngine;
    }

    public void setClickedItem(Table clickedItem) {
        this.clickedItem = clickedItem;
    }

    public ValuesList getValuesList() {
        return valuesList;
    }

    public void setValuesList(ValuesList valuesList) {
        this.valuesList = valuesList;
    }
}
