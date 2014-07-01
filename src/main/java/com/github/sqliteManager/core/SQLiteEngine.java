package com.github.sqliteManager.core;

import com.github.sqliteManager.core.models.Column;
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
    public static final String TABLE_COLUMN_NAME = "name";
    public static final String TABLE_COLUMN_TYPE = "type";
    public static final String TABLE_COLUMN_NOTNULL = "notnull";
    public static final String TABLE_COLUMN_DEFAULT_VALUE = "dflt_value";
    public static final String TABLE_COLUMN_PRIMARY_KEY = "pk";
    public static final String TABLE_COLUMN_SQL = "sql";
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
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        }
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:" + file.getAbsolutePath());
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void closeDB() {
        try {
            Class.forName("org.sqlite.JDBC");
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

    public HashMap<Integer, Table> getTableList() {
        if (connection == null) {
            this.openDB();
        }
        HashMap<Integer, Table> result = new HashMap<Integer, Table>();
        ResultSet resultSet = this.executeSQLQuery("SELECT * FROM sqlite_master WHERE type = 'table'");
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
        ResultSet resultSet = this.executeSQLQuery("PRAGMA table_info(" + table.getTableName() + ")");
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