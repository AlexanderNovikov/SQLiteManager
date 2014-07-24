package com.github.sqliteManager.core;

import com.github.sqliteManager.core.models.Column;
import com.github.sqliteManager.core.models.ColumnType;
import com.github.sqliteManager.core.models.Table;
import javafx.scene.control.Tab;

import java.io.File;
import java.sql.*;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by alexander on 30/06/14.
 */
public class SQLiteEngine {
    private static final int QUERY_TIMEOUT = 30;
    private static final String TABLE_COLUMN_NAME = "name";
    private static final String TABLE_COLUMN_TYPE = "type";
    private static final String TABLE_COLUMN_NOTNULL = "notnull";
    private static final String TABLE_COLUMN_DEFAULT_VALUE = "dflt_value";
    private static final String TABLE_COLUMN_PRIMARY_KEY = "pk";
    private static final String TABLE_COLUMN_SQL = "sql";
    private static final String ORG_SQLITE_JDBC = "org.sqlite.JDBC";
    private static final String JDBC_SQLITE = "jdbc:sqlite:";
    private static final String DEFAULT_NEW_COLUMN_NAME = " ('New Column' ";
    private static final String SQL_CREATE_TABLE = "CREATE TABLE";
    private static final String SQL_SELECT_ALL_FROM = "SELECT * FROM";
    private static final String SQL_SELECT_ALL_ROWID_FROM = "SELECT rowid,* FROM";
    private static final String SQL_WHERE = "WHERE";
    private static final String SQL_PRAGMA = "PRAGMA";
    private static final String SQL_DROP_TABLE = "DROP TABLE";
    private static final String SQL_MASTER_TABLE_NAME = "sqlite_master";
    private static final String SQL_NOT_NULL = "NOT NULL";
    private static final String SQL_DEFAULT = "DEFAULT";
    private static final String SQL_ALTER_TABLE = "ALTER TABLE";
    private static final String SQL_ADD_COLUMN = "ADD COLUMN";
    private static final String SPACE = " ";
    private static final String SQL_RENAME_TO = "RENAME TO";
    private static final String SQL_ROWID = "rowid";
    private static final String SQL_TEMP_POSTFIX = "_temp";
    private static final String SQL_INSERT_INTO = "INSERT INTO";
    private static final String SQL_FROM = "FROM";
    private static final String SQL_SELECT = "SELECT";
    private static final String COMMA = ",";
    private static final String SQL_QUOTE = "\'";
    private static final String SQL_DOUBLE_QUOTE = "\"";
    private File file;
    private Connection connection;

    public static void main(String[] args) {
        SQLiteEngine db = new SQLiteEngine(new File("database.sqlite"));
        db.openDB();
        Table table = new Table("new table name");
        table.setColumns(db.getColumnList(table));
        db.renameColumn(table, new Column("not", ColumnType.INTEGER), "dd_temp");
    }

    public SQLiteEngine(File file) {
        this.file = file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public File getFile() {
        return file;
    }

    public void openDB() {
        try {
            Class.forName(ORG_SQLITE_JDBC);
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        }
        try {
            connection = DriverManager.getConnection(JDBC_SQLITE + file.getAbsolutePath());
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void closeDB() {
        try {
            Class.forName(ORG_SQLITE_JDBC);
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        }
        try {
            connection.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public ResultSet executeSQLQuery(String sqlString) {
        ResultSet resultSet = null;
        try {
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(QUERY_TIMEOUT);
            resultSet = statement.executeQuery(sqlString);
        } catch (SQLException e) {
            System.out.println(e);
        }
        return resultSet;
    }

    public void executeSQLUpdate(String sqlString) {
        try {
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(QUERY_TIMEOUT);
            statement.executeUpdate(sqlString);
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void createTable(Table table) {
        String sqlString = SQL_CREATE_TABLE + SPACE + SQL_QUOTE + table.getTableName() + SQL_QUOTE + "(";
        for (Column column : table.getColumns().values()) {
            sqlString += SQL_QUOTE + column.getColumnName() + SQL_QUOTE + SPACE + column.getColumnType();
            if (column.isNotNull()) {
                sqlString += SPACE + SQL_NOT_NULL + SPACE + SQL_DEFAULT + SPACE + SQL_QUOTE + column.getColumnDefaultValue() + SQL_QUOTE;
            }
            if (table.getColumns().size() > 1 && (column != table.getColumns().get(table.getColumns().size() - 1))) {
                sqlString += ",";
            }
        }
        sqlString += ")";
        executeSQLUpdate(sqlString);
    }

    public void renameTable(Table table, String newTableName) {
        String sqlString = null;
        if (table.getTableName().length() > 0 && newTableName.length() > 0) {
            sqlString = SQL_ALTER_TABLE + SPACE + SQL_QUOTE + table.getTableName() + SQL_QUOTE + SPACE + SQL_RENAME_TO + SPACE + SQL_QUOTE + newTableName + SQL_QUOTE;

        }
        executeSQLUpdate(sqlString);
    }

    public void dropTable(Table table) {
        executeSQLUpdate(SQL_DROP_TABLE + SPACE + SQL_QUOTE + table.getTableName() + SQL_QUOTE);
    }

    public void createColumn(Table table, Column column) {
        String sqlString = null;
        if (column.isNotNull()) {
            sqlString = SQL_ALTER_TABLE + SPACE + SQL_QUOTE + table.getTableName() + SQL_QUOTE + SPACE +
                    SQL_ADD_COLUMN + SPACE + SQL_QUOTE + column.getColumnName() + SQL_QUOTE + SPACE + column.getColumnType() + SPACE +
                    SQL_NOT_NULL + SPACE +
                    SQL_DEFAULT + SPACE + SQL_QUOTE + column.getColumnDefaultValue() + SQL_QUOTE;
        } else {
            sqlString = SQL_ALTER_TABLE + SPACE + SQL_QUOTE + table.getTableName() + SQL_QUOTE + SPACE +
                    SQL_ADD_COLUMN + SPACE + SQL_QUOTE + column.getColumnName() + SQL_QUOTE + SPACE + column.getColumnType();
        }
        executeSQLUpdate(sqlString);
    }

    public void renameColumn(Table table, Column column, String newColumnName) {
        HashMap<Integer, Column> columnList = new HashMap<Integer, Column>();
        int i = 0;
        for (Column col : table.getColumns().values()) {
            if (col.getColumnName().equals(column.getColumnName())) {
                columnList.put(i++, new Column(newColumnName, col.getColumnType(), col.isNotNull(), col.getColumnDefaultValue(), true));
            } else {
                columnList.put(i++, col);
            }
        }
        String sqlString = SQL_INSERT_INTO + SPACE + SQL_QUOTE + table.getTableName() + SQL_QUOTE + "(";
        for (Column col : columnList.values()) {
            if (col.getColumnName().equals(newColumnName)) {
                sqlString += newColumnName;
            } else {
                sqlString += SQL_DOUBLE_QUOTE + col.getColumnName() + SQL_DOUBLE_QUOTE;
            }
            if (table.getColumns().size() > 1 && (col != table.getColumns().get(table.getColumns().size() - 1))) {
                sqlString += ",";
            }
        }
        sqlString += ")" + "\n" + SQL_SELECT + SPACE;
        for (Column col : table.getColumns().values()) {
            sqlString += SQL_DOUBLE_QUOTE + col.getColumnName() + SQL_DOUBLE_QUOTE;
            if (columnList.size() > 1 && (col != columnList.get(columnList.size() - 1))) {
                sqlString += ",";
            }
        }
        sqlString += "\n" + SQL_FROM + SPACE + SQL_QUOTE + table.getTableName() + SQL_TEMP_POSTFIX + SQL_QUOTE;
        //renameTable(table, table.getTableName() + SQL_TEMP_POSTFIX);
        //createTable(new Table(table.getTableName(), columnList));
        System.out.println(sqlString);
//        executeSQLUpdate(sqlString);
    }

    public HashMap<Integer, Table> getTableList() {
        if (connection == null) {
            this.openDB();
        }
        HashMap<Integer, Table> result = new HashMap<Integer, Table>();
        ResultSet resultSet = executeSQLQuery(SQL_SELECT_ALL_FROM + " " + SQL_MASTER_TABLE_NAME + " " + SQL_WHERE + " type = 'table'");
        try {
            int i = 0;
            while (resultSet.next()) {
                String tableName = resultSet.getString(TABLE_COLUMN_NAME);
                result.put(i++, new Table(tableName, getColumnList(new Table(tableName))));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return result;
    }

    public HashMap<Integer, Column> getColumnList(Table table) {
        if (connection == null) {
            openDB();
        }
        HashMap<Integer, Column> result = new HashMap<Integer, Column>();
        String sqlString = SQL_PRAGMA + " table_info(" + SQL_QUOTE + table.getTableName() + SQL_QUOTE + ")";
        ResultSet resultSet = executeSQLQuery(sqlString);
        try {
            int i = 0;
            while (resultSet.next()) {
                result.put(i++, new Column(resultSet.getString(TABLE_COLUMN_NAME),
                        resultSet.getString(TABLE_COLUMN_TYPE),
                        resultSet.getBoolean(TABLE_COLUMN_NOTNULL),
                        resultSet.getString(TABLE_COLUMN_DEFAULT_VALUE),
                        resultSet.getBoolean(TABLE_COLUMN_PRIMARY_KEY)));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return result;
    }

    public HashMap<Integer, ArrayList> getAllValues(String tableName) {
        String sqlString;
        HashMap<Integer, ArrayList> result = new HashMap<Integer, ArrayList>();
        sqlString = SQL_SELECT_ALL_ROWID_FROM + SPACE + SQL_QUOTE + tableName + SQL_QUOTE;
        HashMap<Integer, Column> columnList = this.getColumnList(new Table(tableName));
        ResultSet resultSet = this.executeSQLQuery(sqlString);
        try {
            int i = 1;
            while (resultSet.next()) {
                ArrayList row = new ArrayList();
                row.add(resultSet.getInt(SQL_ROWID));
                for (Column column : columnList.values()) {
                    row.add(resultSet.getString(column.getColumnName()));
                }
                result.put(i++, row);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return result;
    }

    public HashMap<Integer, ArrayList> getValuesRange(String tableName, int start, int end) {
        HashMap<Integer, ArrayList> allValues = getAllValues(tableName);
        HashMap<Integer, ArrayList> result = new HashMap<Integer, ArrayList>();
        for (int i = start; i <= end; i++) {
            if (allValues.get(i) != null) {
                result.put(i, allValues.get(i));
            }
        }
        return result;
    }
}
