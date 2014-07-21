package com.github.sqliteManager.core.models;

import java.util.HashMap;

/**
 * Created by alexander on 30/06/14.
 */
public class Table {
    private String tableName;
    private HashMap<Integer, Column> columns;
    private String tableSQL;

    public Table() {
    }

    public Table(String tableName) {
        this.tableName = tableName;
    }

    public Table(String tableName, String tableSQL) {
        this.tableName = tableName;
        this.tableSQL = tableSQL;
    }

    public Table(String tableName, HashMap<Integer, Column> columns) {
        this.tableName = tableName;
        this.columns = columns;
    }

    public Table(String tableName, HashMap<Integer, Column> columns, String tableSQL) {
        this.tableName = tableName;
        this.columns = columns;
        this.tableSQL = tableSQL;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public HashMap<Integer, Column> getColumns() {
        return columns;
    }

    public void setColumns(HashMap<Integer, Column> columns) {
        this.columns = columns;
    }

    public String getTableSQL() {
        return tableSQL;
    }

    public void setTableSQL(String tableSQL) {
        this.tableSQL = tableSQL;
    }
}
