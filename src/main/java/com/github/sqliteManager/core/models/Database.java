package com.github.sqliteManager.core.models;

import java.io.File;

/**
 * Created by alexander on 21/07/14.
 */
public class Database {
    private String databaseName;
    private File databaseFile;
    private int databaseSize;

    public Database() {
    }

    public Database(File databaseFile) {
        this.databaseName = databaseFile.getName();
        this.databaseFile = databaseFile;
    }

    public Database(File databaseFile, int databaseSize) {
        this.databaseName = databaseFile.getName();
        this.databaseFile = databaseFile;
        this.databaseSize = databaseSize;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public File getDatabaseFile() {
        return databaseFile;
    }

    public void setDatabaseFile(File databaseFile) {
        this.databaseFile = databaseFile;
    }

    public int getDatabaseSize() {
        return databaseSize;
    }

    public void setDatabaseSize(int databaseSize) {
        this.databaseSize = databaseSize;
    }
}
