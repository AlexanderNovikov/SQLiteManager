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
import java.io.File;
import java.util.Collection;

/**
 * Created by alexander on 01/07/14.
 */
public class DBTreeEngine {
    private static final String DATA_BASE_NAME_STRING = "Database: ";
    private static final String TABLE_NAME_STRING = "Table: ";
    private static final String COLUMN_NAME_STRING = "Column: ";
    private static final String DATABASE_LABEL = "Database";
    private static final String TABLE_LABEL = "Table";
    private static final String COLUMN_LABEL = "Column";
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
        addPopupMenu();
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

    public void cleanDBTree() {
        root.setUserObject("");
        root.removeAllChildren();
        treeModel.reload();
    }

    public void refreshDBTree() {
        treeModel.reload();
    }

    public void addNode(String nodeName) {
        System.out.println(sqLiteEngine);
//        sqLiteEngine.addTable(nodeName);
    }

    public void addPopupMenu() {
        tree.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    TreePath path = tree.getPathForLocation(e.getX(), e.getY());
                    if (path != null) {
                        String label = path.getLastPathComponent().toString().split(":")[0];
                        if (label.equals(DATABASE_LABEL)) {
                            DBPopupMenu menu = new DBPopupMenu(root, tree, treeModel);
                            menu.getMenu().show(tree, e.getX(), e.getY());
                        } else if (label.equals(TABLE_LABEL)) {
                            TablePopupMenu menu = new TablePopupMenu();
                            menu.getMenu().show(tree, e.getX(), e.getY());
                        } else if (label.equals(COLUMN_LABEL)) {
                            ColumnPopupMenu menu = new ColumnPopupMenu();
                            menu.getMenu().show(tree, e.getX(), e.getY());
                        }
                    }
                }
            }
        });
    }
}
