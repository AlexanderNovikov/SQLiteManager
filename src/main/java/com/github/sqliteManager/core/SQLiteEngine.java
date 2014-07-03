package com.github.sqliteManager.core;

import com.github.sqliteManager.core.models.Column;
import com.github.sqliteManager.core.models.ColumnType;
import com.github.sqliteManager.core.models.Table;

import java.io.File;
import java.sql.*;
import java.sql.Statement;
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
    private static final String SQL_SELECT_FROM = "SELECT * FROM";
    private static final String SQL_WHERE = "WHERE";
    private static final String SQL_PRAGMA = "PRAGMA";
    private static final String SQL_DROP_TABLE = "DROP TABLE";
    private static final String SQL_MASTER_TABLE_NAME = "sqlite_master";
    private File file;
    private Connection connection;

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

    public void createTable(String tableName) {
        executeSQLUpdate(SQL_CREATE_TABLE  + " " + tableName + DEFAULT_NEW_COLUMN_NAME + ColumnType.INTEGER + ")");
    }

    public void dropTable(String tableName) {
        executeSQLUpdate(SQL_DROP_TABLE + " " + tableName);
    }

    public void createColumn(String tableName, String columnName, ColumnType columnType, Boolean notNull, String defaultValue) {
        executeSQLUpdate("ALTER TABLE " + tableName + " ADD COLUMN " + columnName + " " + columnType);
    }

    public HashMap<Integer, Table> getTableList() {
        if (connection == null) {
            this.openDB();
        }
        HashMap<Integer, Table> result = new HashMap<Integer, Table>();
        ResultSet resultSet = this.executeSQLQuery(SQL_SELECT_FROM + " " + SQL_MASTER_TABLE_NAME + " " + SQL_WHERE + " type = 'table'");
        try {
            int i = 0;
            while (resultSet.next()) {
                result.put(i++, new Table(resultSet.getString(TABLE_COLUMN_NAME), resultSet.getString(TABLE_COLUMN_SQL)));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return result;
    }

    public HashMap<Integer, Column> getColumnList(Table table) {
        if (connection == null) {
            this.openDB();
        }
        HashMap<Integer, Column> result = new HashMap<Integer, Column>();
        ResultSet resultSet = this.executeSQLQuery(SQL_PRAGMA + " table_info(" + table.getTableName() + ")");
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
}
