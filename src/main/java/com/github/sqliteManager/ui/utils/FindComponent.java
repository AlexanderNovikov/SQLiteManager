package com.github.sqliteManager.ui.utils;

import javax.swing.*;
import java.awt.*;
import java.util.*;

/**
 * Created by alexander on 04/08/14.
 */
public class FindComponent {
    private Object rootComponent;
    private HashMap<Integer, Component> componentList = new HashMap<Integer, Component>();
    private int i = 0;

    public FindComponent(Object component) {
        Component comp = SwingUtilities.getRootPane((JComponent)component);
        this.rootComponent = comp;
        getAllComponents(rootComponent);
    }

    private void getAllComponents(Object rootComponent) {
        if (rootComponent instanceof JComponent) {
            for (Component component : ((JComponent) rootComponent).getComponents()) {
                componentList.put(i++, component);
                getAllComponents(component);
            }
        }
    }

    public Component getFirstComponentByName(String componentName) {
        Component result = null;
        for (Component component : componentList.values()) {
            if (component.getName() != null && component.getName().equals(componentName)) {
                result = component;
                break;
            } else {
                result = null;
            }
        }
        return result;
    }

    public HashMap<Integer, Component> getAllComponentsByName(String componentName) {
        HashMap<Integer, Component> result = new HashMap<Integer, Component>();
        int i = 0;
        for (Component component : componentList.values()) {
            if (component.getName() != null && component.getName().equals(componentName)) {
                result.put(i++, component);
            }
        }
        return result;
    }

    public HashMap<Integer, Component> getComponentList() {
        return componentList;
    }
}
