package com.github.sqliteManager.ui.dbTree;

import com.github.sqliteManager.core.SQLiteEngine;
import com.github.sqliteManager.core.models.*;
import com.github.sqliteManager.ui.dbTree.popupMenu.DBTreePopupMenu;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
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
    private MyDefaultMutableTreeNode root;
    private JTree tree;
    private DefaultTreeModel treeModel;
    private JTable valuesList;
    private DefaultTableModel tableModel;

    public DBTreeEngine(MyDefaultMutableTreeNode root, JTree tree, DefaultTreeModel treeModel, JTable table, DefaultTableModel tableModel) {
        this.root = root;
        this.tree = tree;
        this.treeModel = treeModel;
        this.valuesList = table;
        this.tableModel = tableModel;
    }

    public void openDB(File selectedFile) {
        sqLiteEngine = new SQLiteEngine(selectedFile);
        sqLiteEngine.openDB();
        cleanDBTree();
        fillDBTree();
        new DBTreePopupMenu(tree, sqLiteEngine, this, valuesList, tableModel);
    }

    public void closeDB() {
        sqLiteEngine.closeDB();
        cleanDBTree();
    }

    public void fillDBTree() {
        Collection<Table> tables = sqLiteEngine.getTableList().values();
        root.setUserObject(new Database(sqLiteEngine.getFile(), tables.size()));
        for (Table table : tables) {
            Collection<Column> columns = sqLiteEngine.getColumnList(table).values();
            MyDefaultMutableTreeNode tableNode = new MyDefaultMutableTreeNode(table);
            root.add(tableNode);
            for (Column column : columns) {
                tableNode.add(new MyDefaultMutableTreeNode(column));
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

    public void renameTable(Table table, String newTableName) {
        sqLiteEngine.renameTable(table, newTableName);
        refreshDBTree();
    }

    public HashMap<Integer, Column> getHeaders(Table table) {
        return sqLiteEngine.getColumnList(new Table(table.getTableName()));
    }

    public HashMap<Integer, ArrayList> getAllValues(Table table) {
        return sqLiteEngine.getAllValues(table.getTableName());
    }

    public HashMap<Integer, ArrayList> getValuesRange(Table table, int start, int end) {
        HashMap<Integer, ArrayList> result = new HashMap<Integer, ArrayList>();
        if (start >= 0 && end >= 0) {
            result = sqLiteEngine.getValuesRange(table.getTableName(), start, end);
        }
        return result;
    }

    public void removeTable(Table table) {
        sqLiteEngine.dropTable(table);
        refreshDBTree();
    }

    public void createColumn(Table table, Column column) {
        sqLiteEngine.createColumn(table, column);
        refreshDBTree();
    }

    public void renameColumn(Table table, Column column, String newColumnName) {
        sqLiteEngine.renameColumn(table, column, newColumnName);
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
