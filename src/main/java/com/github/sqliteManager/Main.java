package com.github.sqliteManager;

import com.github.sqliteManager.core.SQLiteEngine;
import com.github.sqliteManager.core.models.Column;
import com.github.sqliteManager.core.models.Table;
import com.github.sqliteManager.gui.MainWindow;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

/**
 * Created by alexander on 6/29/14.
 */
public class Main {
    public static void main(String[] args) {
        MainWindow mainWindow = new MainWindow();
        mainWindow.initMainWindow();
//        SQLiteEngine sqLiteEngine = new SQLiteEngine();
//        sqLiteEngine.openDB();
//        for (Table table : sqLiteEngine.getTableList().values()) {
//            System.out.println(table.getTableName());
//            for (Column column : sqLiteEngine.getColumnList(table).values()) {
//                System.out.println(column.getColumnName());
//            }
//        }
    }
}
