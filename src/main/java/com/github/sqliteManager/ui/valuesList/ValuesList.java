package com.github.sqliteManager.ui.valuesList;

import com.github.sqliteManager.core.SQLiteEngine;
import com.github.sqliteManager.core.models.Column;
import com.github.sqliteManager.core.models.Table;
import com.sun.deploy.util.ArrayUtil;
import com.sun.tools.javac.util.ArrayUtils;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.text.Element;
import java.awt.*;
import java.io.File;
import java.sql.Array;
import java.util.*;

/**
 * Created by alexander on 09/07/14.
 */
public class ValuesList {
    private JTable table;
    private DefaultTableModel tableModel;
    private HashMap<Integer, Column> headers;
    private HashMap<Integer, ArrayList> data;

    public ValuesList() {
    }

    public ValuesList(JTable table, DefaultTableModel tableModel) {
        this.table = table;
        this.tableModel = tableModel;
    }

    public void setData(HashMap<Integer, ArrayList> data) {
        this.data = data;
    }

    public HashMap<Integer, ArrayList> getData() {
        return data;
    }

    public void setHeaders(HashMap<Integer, Column> headers) {
        this.headers = headers;
    }

    public HashMap<Integer, Column> getHeaders() {
        return headers;
    }

    public void cleanTable() {
        tableModel.setRowCount(0);
        tableModel.setColumnCount(0);
    }

    public void fillTable() {
        cleanTable();
        if (headers != null) {
            tableModel.addColumn("ROWID");
            for (Column column : headers.values()) {
                tableModel.addColumn(column.getColumnName());
            }
        }
        if (data != null) {
            for (ArrayList<Object> arrayList : data.values()) {
                tableModel.addRow(arrayList.toArray());
            }
        }
    }
}
