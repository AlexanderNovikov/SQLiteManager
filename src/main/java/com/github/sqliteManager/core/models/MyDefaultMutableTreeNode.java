package com.github.sqliteManager.core.models;

import javax.swing.tree.DefaultMutableTreeNode;

/**
 * Created by alexander on 21/07/14.
 */
public class MyDefaultMutableTreeNode extends DefaultMutableTreeNode {

    private static final String SPACE = " ";
    private static final String RIGHT_BRACE = "(";
    private static final String LEFT_BRACE = ")";

    public MyDefaultMutableTreeNode() {
        super();
    }

    public MyDefaultMutableTreeNode(Object userObject) {
        super(userObject);
    }

    public MyDefaultMutableTreeNode(Object userObject, boolean allowsChildren) {
        super(userObject, allowsChildren);
    }

    @Override
    public String toString() {
        if (super.userObject == null) {
            return "";
        } else {
            if (super.userObject instanceof Database) {
                return ((Database) super.userObject).getDatabaseName() + SPACE + RIGHT_BRACE + ((Database) super.userObject).getDatabaseSize() + LEFT_BRACE;
            } else if (super.userObject instanceof Table) {
                return ((Table) super.userObject).getTableName() + SPACE + RIGHT_BRACE + ((Table) super.userObject).getColumns().size() + LEFT_BRACE;
            } else if (super.userObject instanceof Column) {
                return ((Column) super.userObject).getColumnName() + SPACE + RIGHT_BRACE + ((Column) super.userObject).getColumnType() + LEFT_BRACE;
            } else {
                return super.userObject.toString();
            }
        }
    }
}
