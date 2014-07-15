package com.github.sqliteManager.core.models;

/**
 * Created by alexander on 30/06/14.
 */
public class Column {
    private int columnID;
    private String columnName;
    private String columnType;
    private boolean notNull;
    private String columnDefaultValue;
    private boolean primaryKey;

    public Column() {
    }

    public Column(String columnName, String columnType, boolean notNull, String columnDefaultValue, boolean primaryKey) {
        this.columnName = columnName;
        this.columnType = columnType;
        this.notNull = notNull;
        this.columnDefaultValue = columnDefaultValue;
        this.primaryKey = primaryKey;
    }

    public int getColumnID() {
        return columnID;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnType() {
        return columnType;
    }

    public void setColumnType(String columnType) {
        this.columnType = columnType;
    }

    public boolean isNotNull() {
        return notNull;
    }

    public void setNotNull(boolean notNull) {
        this.notNull = notNull;
    }

    public String getColumnDefaultValue() {
        return columnDefaultValue;
    }

    public void setColumnDefaultValue(String columnDefaultValue) {
        this.columnDefaultValue = columnDefaultValue;
    }

    public boolean isPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(boolean primaryKey) {
        this.primaryKey = primaryKey;
    }
}
