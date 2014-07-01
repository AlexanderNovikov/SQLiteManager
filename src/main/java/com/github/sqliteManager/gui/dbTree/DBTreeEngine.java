package com.github.sqliteManager.gui.dbTree;

import com.github.sqliteManager.core.SQLiteEngine;
import com.github.sqliteManager.core.models.Column;
import com.github.sqliteManager.core.models.Table;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.util.Collection;

/**
 * Created by alexander on 01/07/14.
 */
public class DBTreeEngine {
    public static final String DATA_BASE_NAME_STRING = "DataBase: ";
    private SQLiteEngine sqLiteEngine;
    private DefaultMutableTreeNode root;
    private DefaultTreeModel treeModel;

    public DBTreeEngine(SQLiteEngine sqLiteEngine, DefaultMutableTreeNode root, DefaultTreeModel treeModel) {
        this.sqLiteEngine = sqLiteEngine;
        this.root = root;
        this.treeModel = treeModel;
        System.out.println(root);
    }

    public void fillDBTree() {
        Collection<Table> tables = sqLiteEngine.getTableList().values();
        root.setUserObject(DATA_BASE_NAME_STRING + sqLiteEngine.getFile().getName() + " (" + tables.size() + ")");
        for (Table table : tables) {
            Collection<Column> columns = sqLiteEngine.getColumnList(table).values();
            DefaultMutableTreeNode tableNode = new DefaultMutableTreeNode(table.getTableName() + " (" + columns.size() + ")");
            root.add(tableNode);
            for (Column column : columns) {
                DefaultMutableTreeNode columnNode = new DefaultMutableTreeNode(column.getColumnName());
                tableNode.add(columnNode);
            }
        }
        treeModel.reload();
    }

    public void cleanDBTree() {
        root.setUserObject("");
        root.removeAllChildren();
        treeModel.reload();
    }
}
