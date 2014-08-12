package com.github.sqliteManager.core.sqlite;

import javax.swing.plaf.DesktopPaneUI;

/**
 * Created by alexander on 31/07/14.
 */
public class SQLite {
    public static final String ALTER_TABLE = "ALTER TABLE";
    public static final String RENAME_TO = "RENAME TO";
    public static final String ADD_COLUMN = "ADD COLUMN";
    public static final String CREATE_INDEX = "CREATE INDEX";
    public static final String CREATE_TABLE = "CREATE TABLE";
    public static final String CREATE_TRIGGER = "CREATE TRIGGER";
    public static final String CREATE_VIEW = "CREATE VIEW";
    public static final String CREATE_VIRTUAL_TABLE = "CREATE VIRTUAL TABLE";
    public static final String DELETE = "DELETE";
    public static final String DROP_INDEX = "DROP INDEX";
    public static final String DROP_TABLE = "DROP TABLE";
    public static final String DROP_TRIGGER = "DROP TRIGGER";
    public static final String DROP_VIEW = "DROP VIEW";
    public static final String INSERT = "INSERT";
    public static final String INTO = "INTO";
    public static final String VALUES = "VALUES";
    public static final String PRAGMA = "PRAGMA";
    public static final String TABLE_INFO = "table_info";
    public static final String REPLACE = "REPLACE";
    public static final String SELECT = "SELECT";
    public static final String UPDATE = "UPDATE";
    public static final String WITH = "WITH";
    public static final String FROM = "FROM";
    public static final String WHERE = "WHERE";

    public static final String SPACE = " ";
    public static final String ASTERISK = "*";
    public static final String COMMA = ",";
    public static final String DOUBLE_QUOTES = "\"";
    public static final String LEFT_BRACE = "(";
    public static final String RIGHT_BRACE = ")";
    public static final String ROWID = "rowid";
    public static final String NOT_NULL = "NOT NULL";
    public static final String DEFAULT = "DEFAULT";
    public static final String MASTER_TABLE = "sqlite_master";

    public String alterTable(String tableName) {
        return ALTER_TABLE + SPACE + DOUBLE_QUOTES + tableName + DOUBLE_QUOTES;
    }

    public String alterTable(String tableName, String newTableName) {
        return alterTable(tableName) + SPACE + RENAME_TO + SPACE + DOUBLE_QUOTES + newTableName + DOUBLE_QUOTES;
    }

    public String alterTable(String tableName, String columnName, String columnType, Boolean isNotNull, String columnDefaultValue) {
        if (isNotNull) {
            return alterTable(tableName) + SPACE + ADD_COLUMN + SPACE + DOUBLE_QUOTES +
                    columnName + DOUBLE_QUOTES + SPACE +
                    columnType + SPACE +
                    NOT_NULL + SPACE + DEFAULT + SPACE + DOUBLE_QUOTES +
                    columnDefaultValue + DOUBLE_QUOTES;

        } else {
            return alterTable(tableName) + SPACE + ADD_COLUMN + SPACE + DOUBLE_QUOTES +
                    columnName + DOUBLE_QUOTES + SPACE +
                    columnType;
        }
    }

    public String createTable(String tableName) {
        return CREATE_TABLE + SPACE + DOUBLE_QUOTES + tableName + DOUBLE_QUOTES;
    }

    public String dropTable(String tableName) {
        return DROP_TABLE + SPACE + DOUBLE_QUOTES + tableName + DOUBLE_QUOTES;
    }

    public String removeRowByRowID(String tableName, int rowID) {
        return DELETE + SPACE + FROM + SPACE + DOUBLE_QUOTES + tableName + DOUBLE_QUOTES + SPACE + WHERE + SPACE + "ROWID = " + DOUBLE_QUOTES + rowID + DOUBLE_QUOTES;
    }

    public String insertInto(String tableName) {
        return INSERT + SPACE + INTO + SPACE + DOUBLE_QUOTES + tableName + DOUBLE_QUOTES;
    }

    public String pragmaTableInfo(String tableName) {
        return PRAGMA + SPACE + TABLE_INFO + LEFT_BRACE + DOUBLE_QUOTES + tableName + DOUBLE_QUOTES + RIGHT_BRACE;
    }
}
