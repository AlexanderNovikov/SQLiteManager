package com.github.sqliteManager.ui.dbTree.popupMenu;

import com.github.sqliteManager.core.SQLiteEngine;
import com.github.sqliteManager.ui.dbTree.DBTreeEngine;
import com.github.sqliteManager.ui.dbTree.dialogs.DeleteTableDialog;
import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by alexander on 01/07/14.
 */
public class TablePopupMenu extends DBTreePopupMenu {
    private static final String ADD_COLUMN_LABEL = "Add column";
    private static final String RENAME_TABLE_LABEL = "Rename table";
    private static final String VIEW_CONTENT_LABEL = "View content";
    private static final String DELETE_TABLE_LABEL = "Delete table";
    private JPopupMenu menu;
    private SQLiteEngine sqLiteEngine;
    private DBTreeEngine dbTreeEngine;
    private String clickedItem;
    private String clickedItemParent;

    public TablePopupMenu(JTree tree, SQLiteEngine sqLiteEngine, DBTreeEngine dbTreeEngine, String clickedItem, String clickedItemParent) {
        super(tree, sqLiteEngine, dbTreeEngine);
        this.menu = new JPopupMenu();
        this.sqLiteEngine = sqLiteEngine;
        this.dbTreeEngine = dbTreeEngine;
        this.clickedItem = clickedItem;
        this.clickedItem = clickedItemParent;
        addMenuItems();
    }

    public void addMenuItems() {
        addMenuItem(menu, ADD_COLUMN_LABEL, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        addMenuItem(menu, RENAME_TABLE_LABEL, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        addMenuItem(menu, VIEW_CONTENT_LABEL, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        addMenuItem(menu, DELETE_TABLE_LABEL, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int option = new DeleteTableDialog().getOption();
                if (option == JOptionPane.YES_OPTION) {
                    dbTreeEngine.removeTable(clickedItem);
                }
            }
        });
    }

    public JPopupMenu getMenu() {
        return menu;
    }
}
