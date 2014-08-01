package com.github.sqliteManager.core.models;

import javax.swing.*;
import javax.swing.table.TableModel;
import javax.swing.text.StringContent;
import java.util.HashMap;

/**
 * Created by alexander on 01/08/14.
 */
public class MyJTable extends JTable {

    public MyJTable() {
    }

    public MyJTable(TableModel dm) {
        super(dm);
    }

    public String getSelectedCellValue() {
        Object cellValue = getValueAt(getSelectedRow(), getSelectedColumn());
        if (cellValue != null) {
            return cellValue.toString();
        } else {
            return "";
        }
    }

    public HashMap<Integer, HashMap> getSelectedRowValue() {
        HashMap<Integer, HashMap> rows = new HashMap<Integer, HashMap>();
        int rcount = 0;
        for (int r : getSelectedRows()) {
            HashMap<Integer, String> rowValues = new HashMap<Integer, String>();
            int ccount = 0;
            for (int c = 0; c < getColumnCount(); c++) {
                if (getValueAt(r, c) != null) {
                    rowValues.put(ccount++, getValueAt(r, c).toString());
                } else {
                    rowValues.put(ccount++, "");
                }
            }
            rows.put(rcount++, rowValues);
        }
        return rows;
    }

    public HashMap<Integer, Integer> getSelectedRowID() {
        HashMap<Integer, Integer> result = new HashMap<Integer, Integer>();
        int i = 0;
        for (HashMap r : getSelectedRowValue().values()) {
            result.put(i++, Integer.parseInt(r.get(0).toString()));
        }
        return result;
    }

}
