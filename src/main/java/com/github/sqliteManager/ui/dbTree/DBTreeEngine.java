package com.github.sqliteManager.ui.dbTree;

import com.github.sqliteManager.core.SQLiteEngine;
import com.github.sqliteManager.core.models.Column;
import com.github.sqliteManager.core.models.ColumnType;
import com.github.sqliteManager.core.models.Table;
import com.github.sqliteManager.ui.dbTree.popupMenu.DBTreePopupMenu;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.io.File;
import java.util.Collection;

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

    public DBTreeEngine(DefaultMutableTreeNode root, JTree tree, DefaultTreeModel treeModel) {
        this.root = root;
        this.tree = tree;
        this.treeModel = treeModel;
    }

    public void openDB(File selectedFile) {
        sqLiteEngine = new SQLiteEngine(selectedFile);
        sqLiteEngine.openDB();
        cleanDBTree();
        fillDBTree();
        new DBTreePopupMenu(tree, sqLiteEngine, this);
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

    public void createTable(String tableName) {
        sqLiteEngine.createTable(tableName);
        refreshDBTree();
    }

    public void renameTable(String tableName, String newTableName) {
        sqLiteEngine.renameTable(tableName, newTableName);
        refreshDBTree();
    }

    public void removeTable(String tableName) {
        sqLiteEngine.dropTable(tableName);
        refreshDBTree();
    }

    public void createColumn(String tableName, String columnName, String columnType, Boolean notNull, String defaultValue) {
        sqLiteEngine.createColumn(tableName, columnName, columnType, notNull, defaultValue);
        refreshDBTree();
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
