package com.github.sqliteManager.ui.dbTree;

import com.github.sqliteManager.core.SQLiteEngine;
import com.github.sqliteManager.core.models.Column;
import com.github.sqliteManager.core.models.Table;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Collection;

/**
 * Created by alexander on 01/07/14.
 */
public class DBTreeEngine {
    public static final String DATA_BASE_NAME_STRING = "Database: ";
    public static final String TABLE_NAME_STRING = "Table: ";
    public static final String COLUMN_NAME_STRING = "Column: ";
    private SQLiteEngine sqLiteEngine;
    private DefaultMutableTreeNode root;
    private JTree tree;
    private DefaultTreeModel treeModel;

    public DBTreeEngine(SQLiteEngine sqLiteEngine, DefaultMutableTreeNode root, JTree tree, DefaultTreeModel treeModel) {
        this.sqLiteEngine = sqLiteEngine;
        this.root = root;
        this.tree = tree;
        this.treeModel = treeModel;
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

    public void cleanDBTree() {
        root.setUserObject("");
        root.removeAllChildren();
        treeModel.reload();
    }

    public void addPopupMenu() {
        tree.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    TreePath path = tree.getPathForLocation(e.getX(), e.getY());
                    if (path.getLastPathComponent().toString().split(":")[0].equals("Database")) {

                    } else if (path.getLastPathComponent().toString().split(":").equals("Table")) {
                        TablePopupMenu menu = new TablePopupMenu(new JPopupMenu());
                        menu.show(tree, e.getX(), e.getY());
                    } else if (path.getLastPathComponent().toString().split(":").equals("Column")) {

                    }
                }
            }
        });
    }
}
