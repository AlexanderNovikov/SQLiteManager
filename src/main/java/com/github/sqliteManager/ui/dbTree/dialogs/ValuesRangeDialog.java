package com.github.sqliteManager.ui.dbTree.dialogs;

import com.github.sqliteManager.ui.dbTree.errors.RangeLengthError;

import javax.swing.*;

/**
 * Created by alexander on 11/07/14.
 */
public class ValuesRangeDialog {
    private static final String LABEL_START = "Start";
    private static final String LABEL_END = "End";
    private static final String DAILOG_TITLE = "label";
    private int start;
    private int end;
    private JPanel panel;
    private JOptionPane optionPane;
    private int result;
    private JTextField startField, endField;

    public ValuesRangeDialog() {
        panel = new JPanel();
        optionPane = new JOptionPane();
        startField = new JTextField(15);
        endField = new JTextField(15);
        panel.add(new JLabel(LABEL_START));
        panel.add(startField);
        panel.add(new JLabel(LABEL_END));
        panel.add(endField);
        result = optionPane.showConfirmDialog(null, panel, DAILOG_TITLE, JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null);
        if (result == JOptionPane.YES_OPTION) {
            if (startField.getText().length() == 0 || endField.getText().length() == 0) {
                new RangeLengthError();
            } else {
                this.start = Integer.parseInt(startField.getText());
                this.end = Integer.parseInt(endField.getText());
            }
        }
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }
}
