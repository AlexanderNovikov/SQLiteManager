package com.github.sqliteManager.core.models;

import javax.swing.*;

/**
 * Created by alexander on 22/07/14.
 */
public class MyBox extends Box {

    public MyBox(JComponent[] components, float alignment) {
        super(BoxLayout.X_AXIS);
        for (JComponent component : components) {
            super.add(component);
        }
        super.setAlignmentX(alignment);
    }

}
