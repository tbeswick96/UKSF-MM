package com.uksf.tim.gui.components;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

class GenericPanel extends JPanel {

    /**
     * Creates a generic panel with miglayout constraints
     * @param layout layout constraints
     * @param columns column constraints
     * @param rows row constraints
     */
    GenericPanel(String layout, String columns, String rows, boolean opaque, Color background) {
        setOpaque(opaque);
        setBackground(background);
        setLayout(new MigLayout(layout, columns, rows));
    }
}
