package com.github.sqliteManager.ui.dbTree;

import com.github.sqliteManager.core.SQLiteEngine;
import com.github.sqliteManager.core.models.Column;
import com.github.sqliteManager.core.models.ColumnType;
import com.github.sqliteManager.core.models.Table;
import com.github.sqliteManager.ui.dbTree.popupMenu.DBTreePopupMenu;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

/**
 * Created by alexander on 01/07/14.
 */
public class DBTreeEngine {
    private static final String DATA_BASE_NAME_STRING = "Database: ";
    private static final String TABLE_NAME_STRING = "Table: ";
    private static final String COLUMN_NAME_STRING = "Column: ";
    private SQLiteEngine sqLiteEngine;
    private DefaultMutableTreeNode root;
    private JTree tree;
    private DefaultTreeModel treeModel;
    private JTable table;
    private DefaultTableModel tableModel;

    public DBTreeEngine(DefaultMutableTreeNode root, JTree tree, DefaultTreeModel treeModel, JTable table, DefaultTableModel tableModel) {
        this.root = root;
        this.tree = tree;
        this.treeModel = treeModel;
        this.table = table;
        this.tableModel = tableModel;
    }

    public void openDB(File selectedFile) {
        sqLiteEngine = new SQLiteEngine(selectedFile);
        sqLiteEngine.openDB();
        cleanDBTree();
        fillDBTree();
        new DBTreePopupMenu(tree, sqLiteEngine, this, table, tableModel);
    }

    public void closeDB() {
        sqLiteEngine.closeDB();
        cleanDBTree();
    }

    public void fillDBTree() {
        Collection<Table> tables = sqLiteEngine.getTableList().values();
        root.setUserObject(DATA_BASE_NAME_STRING + sqLiteEngine.getFile().getName() + " (" + tables.size() + ")");
        for (Table table : tables) {
            Collection<Column> columns = sqLiteEngine.getColumnList(table).values();
            DefaultMutableTreeNode tableNode = new DefaultMutableTreeNode(TABLE_NAME_STRING + table.getTableName() + " (" + columns.size() + ")");
            root.add(tableNode);
            for (Column column : columns) {
                DefaultMutableTreeNode columnNode = new DefaultMutableTreeNode(COLUMN_NAME_STRING + column.getColumnName());
                tableNode.add(columnNode);
            }
        }
        treeModel.reload();
    }

    public void createTable(Table table) {
        if (table != null) {
            sqLiteEngine.createTable(table);
            refreshDBTree();
        }
    }

    public void renameTable(String tableName, String newTableName) {
        sqLiteEngine.renameTable(tableName, newTableName);
        refreshDBTree();
    }

    public HashMap<Integer, Column> getHeaders(String tableName) {
        return sqLiteEngine.getColumnList(new Table(tableName));
    }

    public HashMap<Integer, ArrayList> getAllValues(String tableName) {
        return sqLiteEngine.getAllValues(tableName);
    }

    public HashMap<Integer, ArrayList> getValuesRange(String tableName, int start, int end) {
        HashMap<Integer, ArrayList> result = new HashMap<Integer, ArrayList>();
        if (start >= 0 && end >= 0) {
            result = sqLiteEngine.getValuesRange(tableName, start, end);
        }
        return result;
    }

    public void removeTable(String tableName) {
        sqLiteEngine.dropTable(tableName);
        refreshDBTree();
    }

    public void createColumn(String tableName, String columnName, String columnType, Boolean notNull, String defaultValue) {
        sqLiteEngine.createColumn(tableName, columnName, columnType, notNull, defaultValue);
        refreshDBTree();
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) {
        sqLiteEngine.renameColumn(tableName, columnName, newColumnName);
    }

    public void cleanDBTree() {
        root.setUserObject("");
        root.removeAllChildren();
        treeModel.reload();
    }

    public void refreshDBTree() {
        cleanDBTree();
        fillDBTree();
    }
}
