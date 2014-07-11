package com.github.sqliteManager.core.models;

/**
 * Created by alexander on 30/06/14.
 */
public class Table {
    private String tableName;
    private String tableSQL;

    public Table(String tableName) {
        this.tableName = tableName;
    }

    public Table(String tableName, String tableSQL) {
        this.tableName = tableName;
        this.tableSQL = tableSQL;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableSQL() {
        return tableSQL;
    }

    public void setTableSQL(String tableSQL) {
        this.tableSQL = tableSQL;
    }
}
